package me.letsdev.auth;

import lombok.RequiredArgsConstructor;
import me.letsdev.core.jwt.Hs512JwtProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final Hs512JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    public String signIn(String username, String password) {
        // 1: Validate the password
        Member dbMember = memberRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Username or password is incorrect."));

        // passwordEncoder.matches(inputted password, hashed password)
        if (!passwordEncoder.matches(password, dbMember.password)) {
            throw new RuntimeException("Username or password is incorrect.");
        };

        // 2: Prepare claims
        Map<String, Object> claims = Map.of("role", "MEMBER");

        // 3: Token with claims.
        return jwtProvider.apply(username, claims);
    }
}
