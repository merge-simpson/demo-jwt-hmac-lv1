package me.letsdev.core.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.function.BiFunction;

@Component("jwtProvider")
public class Hs512JwtProvider implements BiFunction<String, Map<String, ?>, String> {

    private final JwtBuilder jwtBuilder;
    private final Long maxAge;

    public Hs512JwtProvider(JwtProperties jwtProperties) {
        this.jwtBuilder = Jwts.builder()
                .signWith(jwtProperties.secretKey(), SIG.HS512);
        this.maxAge = jwtProperties.maxAge();
    }

    @Override
    public String apply(String subject, Map<String, ?> extendedClaims) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + maxAge * 1000);

        /* ╔══════════════════════════════════════════════════╗
           ║                      HEADER                      ║
           ║ .signWith(jwtProperties.secretKey(), SIG.HS512)  ║
           ╠══════════════════════════════════════════════════╣
           ║                      PAYLOAD                     ║
           ║                       here                       ║ */
        return jwtBuilder.claims()
                .subject(subject)
                .add(extendedClaims)
                .issuedAt(now)
                .expiration(expiration)
                .and()
        /* ╠══════════════════════════════════════════════════╣ */
                .compact();
        /* ║             SIGNATURE (auto-generated)           ║
           ╚══════════════════════════════════════════════════╝ */
    }
}
