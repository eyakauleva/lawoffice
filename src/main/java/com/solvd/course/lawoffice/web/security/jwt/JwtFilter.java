package com.solvd.course.lawoffice.web.security.jwt;

import com.solvd.course.lawoffice.domain.security.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final static String URL_REFRESH = "refresh";
    private final static String ATTRIBUTE_AUTHORIZATION = "Authorization";
    private final static String ATTRIBUTE_TOKEN_BEGINNING_IN_HEADER = "Bearer ";
    public final static String ATTRIBUTE_CLAIMS = "claims";

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        Optional<String> tokenOptional = getTokenFromRequest(request);
        if (tokenOptional.isPresent()) {
            String token = tokenOptional.get();
            String requestURL = request.getRequestURL().toString();
            try {
                if (StringUtils.hasText(token) && jwtProvider.validateToken(token)) {

                    if (requestURL.contains(URL_REFRESH)) {
                        throw new JwtException("Token is not expired yet");
                    }

                    UserDetails userDetails =
                            new UserDetailsImpl(
                                    jwtProvider.getIdFromToken(token),
                                    jwtProvider.getLoginFromToken(token),
                                    Strings.EMPTY,
                                    jwtProvider.getRolesFromToken(token),
                                    jwtProvider.getStatusFromToken(token));

                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails, null, userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (ExpiredJwtException e) {
                if (requestURL.contains(URL_REFRESH)) {
                    if (jwtProvider.isRefreshAvailable(e)) {
                        allowForRefreshToken(e, request);
                        filterChain.doFilter(request, response);
                    } else {
                        throw new JwtException("Token is not available for refresh yet");
                    }
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    private void allowForRefreshToken(ExpiredJwtException ex, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(null, null, null);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        request.setAttribute(ATTRIBUTE_CLAIMS, ex.getClaims());
    }

    private Optional<String> getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(ATTRIBUTE_AUTHORIZATION);
        if (StringUtils.hasText(bearerToken)
                && bearerToken.startsWith(ATTRIBUTE_TOKEN_BEGINNING_IN_HEADER)) {
            return Optional.of(bearerToken.replace(ATTRIBUTE_TOKEN_BEGINNING_IN_HEADER, Strings.EMPTY));
        } else {
            return Optional.empty();
        }
    }

}
