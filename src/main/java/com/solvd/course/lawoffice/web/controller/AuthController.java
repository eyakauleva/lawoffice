package com.solvd.course.lawoffice.web.controller;

import com.solvd.course.lawoffice.domain.security.AuthRequest;
import com.solvd.course.lawoffice.domain.security.AuthResponse;
import com.solvd.course.lawoffice.web.security.jwt.JwtFilter;
import com.solvd.course.lawoffice.web.security.jwt.JwtProvider;
import io.jsonwebtoken.impl.DefaultClaims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtProvider jwtProvider;

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getLogin(), authRequest.getPassword()));
        String token = jwtProvider.generateToken(userDetailsService.loadUserByUsername(authRequest.getLogin()));
        return new AuthResponse(token);
    }

    @PostMapping("/refresh")
    public AuthResponse refreshToken(HttpServletRequest request) {
        DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute(JwtFilter.ATTRIBUTE_CLAIMS);
        String token = null;
        if (Objects.nonNull(claims)) {
            Map<String, Object> expectedMap = new HashMap<>(claims);
            token = jwtProvider.generateRefreshedToken(expectedMap, expectedMap.get(JwtProvider.ATTRIBUTE_SUB).toString());
        }
        return new AuthResponse(token);
    }

}
