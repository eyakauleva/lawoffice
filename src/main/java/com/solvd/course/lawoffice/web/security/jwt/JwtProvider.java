package com.solvd.course.lawoffice.web.security.jwt;

import com.solvd.course.lawoffice.domain.user.Role;
import com.solvd.course.lawoffice.domain.user.UserStatus;
import com.solvd.course.lawoffice.domain.security.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JwtProvider {

    private String jwtSecret;
    private int jwtExpirationInMs;
    private int ableForRefreshTimeInMs;
    private final String PROPERTY_JWT_SECRET = "${jwt.secret}";
    private final String PROPERTY_JWT_EXPIRATION_TIME_IN_SEC = "${jwt.expirationTimeInSec}";
    private final String PROPERTY_JWT_ABLE_FOR_REFRESH_TIME_IN_SEC = "${jwt.ableForRefreshTimeInSec}";
    private final String ATTRIBUTE_ROLE = "role";
    private final String ATTRIBUTE_STATUS = "status";
    private final String ATTRIBUTE_ID = "id";
    public final static String ATTRIBUTE_SUB = "sub";

    @Value(PROPERTY_JWT_SECRET)
    public void setJwtSecret(String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    @Value(PROPERTY_JWT_EXPIRATION_TIME_IN_SEC)
    public void setJwtExpirationInMs(int jwtExpirationInSec) {
        this.jwtExpirationInMs = jwtExpirationInSec * 1000;
    }

    @Value(PROPERTY_JWT_ABLE_FOR_REFRESH_TIME_IN_SEC)
    public void setAbleForRefreshTimeInMs(int ableForRefreshTimeInSec) {
        this.ableForRefreshTimeInMs = ableForRefreshTimeInSec * 1000;
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();

        for (Role role : Role.values()) {
            if (roles.contains(new SimpleGrantedAuthority(role.toString()))) {
                claims.put(ATTRIBUTE_ROLE, role.toString());
            }
        }

        claims.put(ATTRIBUTE_ID, ((UserDetailsImpl) userDetails).getId());
        claims.put(ATTRIBUTE_STATUS, ((UserDetailsImpl) userDetails).getStatus());

        return doGenerateToken(claims, userDetails.getUsername(), jwtExpirationInMs);
    }

    public String generateRefreshedToken(Map<String, Object> claims, String subject) {
        return doGenerateToken(claims, subject, jwtExpirationInMs);
    }

    private String doGenerateToken(Map<String, Object> claims, String subject, int expirationMs) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException
                 | MalformedJwtException
                 | UnsupportedJwtException
                 | IllegalArgumentException ex) {
            return false;
        }
    }

    public String getLoginFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public List<SimpleGrantedAuthority> getRolesFromToken(String token) {
        String authority = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .get(ATTRIBUTE_ROLE, String.class);
        return Collections.singletonList(new SimpleGrantedAuthority(authority));
    }

    public UserStatus getStatusFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return UserStatus.valueOf(claims.get(ATTRIBUTE_STATUS, String.class));
    }

    public Long getIdFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .get(ATTRIBUTE_ID, Long.class);
    }

    public Boolean isRefreshAvailable(ExpiredJwtException ex) {
        long creationTimeInMs = ex.getClaims().getIssuedAt().getTime();
        long timePastSinceCreationInMs = System.currentTimeMillis() - creationTimeInMs;
        return timePastSinceCreationInMs > ableForRefreshTimeInMs;
    }

}
