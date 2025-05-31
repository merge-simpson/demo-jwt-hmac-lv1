package me.letsdev.core.jwt;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import me.letsdev.core.jwt.exception.JwtErrorCode;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.crypto.SecretKey;

@ConfigurationProperties("app.jwt")
public record JwtProperties(
        String secret,
        Long maxAge
) {
    public JwtProperties {
        if (secret == null || secret.isBlank()) {
            throw JwtErrorCode.BLANK_JWT_SECRET.exception();
        }

        if (maxAge == null || maxAge <= 0) {
            maxAge = 3600L;
        }
    }

    public SecretKey secretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
