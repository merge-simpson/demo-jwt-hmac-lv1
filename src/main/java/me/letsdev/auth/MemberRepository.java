package me.letsdev.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MemberRepository {
    private final Map<String, Member> cachedByUsername = new ConcurrentHashMap<>();

    public MemberRepository(PasswordEncoder passwordEncoder) {
        cachedByUsername.computeIfAbsent(
                "abc123",
                (username) -> new Member(username, passwordEncoder.encode("1234")));
        cachedByUsername.computeIfAbsent(
                "abc124",
                (username) -> new Member(username, passwordEncoder.encode("1234")));
    }

    public Optional<Member> findByUsername(String username) {
        var dbMember = cachedByUsername.get(username);
        return Optional.ofNullable(dbMember);
    }
}
