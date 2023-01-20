package com.solvd.course.lawoffice.web.security.jwt;

import com.solvd.course.lawoffice.domain.security.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtProvider jwtProvider;
    private final static String URL_REFRESH = "refresh";
    private final static String ATTRIBUTE_AUTHORIZATION = "Authorization";
    private final static String ATTRIBUTE_TOKEN_BEGINNING_IN_HEADER = "Bearer ";
    public final static String ATTRIBUTE_CLAIMS = "claims";

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getRequestURI().equals("/api/v1/auth");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = getTokenFromRequest(request);
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

                filterChain.doFilter(request, response);
                return;
            }
        } catch (ExpiredJwtException e) {
            if (requestURL.contains(URL_REFRESH)) {
                if (jwtProvider.isRefreshAvailable(e)) {
                    allowForRefreshToken(e, request);
                } else {
                    throw new JwtException("Token is not available for refresh yet");
                }
            }
        }

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }

    private void allowForRefreshToken(ExpiredJwtException ex, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(null, null, null);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        request.setAttribute(ATTRIBUTE_CLAIMS, ex.getClaims());
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(ATTRIBUTE_AUTHORIZATION);
        if (StringUtils.hasText(bearerToken)
                && bearerToken.startsWith(ATTRIBUTE_TOKEN_BEGINNING_IN_HEADER)) {
            return bearerToken.replace(ATTRIBUTE_TOKEN_BEGINNING_IN_HEADER, Strings.EMPTY);
        }
        return Strings.EMPTY;
    }

}
