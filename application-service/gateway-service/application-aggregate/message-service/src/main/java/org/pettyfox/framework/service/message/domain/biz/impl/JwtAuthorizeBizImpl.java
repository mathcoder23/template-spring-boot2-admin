package org.pettyfox.framework.service.message.domain.biz.impl;

import io.jsonwebtoken.*;
import org.pettyfox.framework.service.message.domain.biz.AuthorizeBiz;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

@Component
public class JwtAuthorizeBizImpl implements AuthorizeBiz {

    private JwtParser parser;
    private JwtVerificationHandler jwtVerificationHandler;

    @Value("config.jwt.signingKey:YXNkZmFzZmFzZGYyMTQzNDU0NTd0Z2dkCiA=")
    private String signingKey;

    @PostConstruct
    public void init() {
        if (null == signingKey || signingKey.trim().isEmpty()) {
            throw new RuntimeException("Signing Key is not allowed to be null.");
        }
        parser = Jwts.parser().setSigningKey(signingKey);
        jwtVerificationHandler = new JwtVerificationHandler();
    }

    @Override
    public String createToken(Long userId, String username) {
        return Jwts.builder()
                .setSubject("jwt")
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 3650L))
                .signWith(SignatureAlgorithm.HS256, signingKey)
                .compact();
    }

    @Override
    public boolean checkToken(String token) {
        Jws<Claims> jwt = parser.parseClaimsJws(token);
        try {
            jwtVerificationHandler.assertJwtAvailable(jwt);
        } catch (TokenAuthenticationRejectException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static class JwtVerificationHandler {

        public void assertJwtAvailable(Jws<Claims> jwt) throws TokenAuthenticationRejectException {
            String exceptionMsg = null;
            Claims claims = jwt.getBody();
            if (null == claims.getExpiration()) {
                exceptionMsg = "jwt expiration not allow is null";
            } else if (System.currentTimeMillis() > claims.getExpiration().getTime()) {
                exceptionMsg = "jwt is expiration(" + claims.getExpiration().getTime() + ").";
            }
            if (null != exceptionMsg) {
                throw new TokenAuthenticationRejectException(exceptionMsg);
            }
        }
    }

    public static class TokenAuthenticationRejectException extends Exception {
        public TokenAuthenticationRejectException(String message) {
            super(message);
        }
    }
}
