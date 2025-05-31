package me.letsdev.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.letsdev.auth.AuthDto.SignInRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthApi {
    private final AuthService authService;

    @PostMapping("/sign-in")
    public String signIn(@RequestBody @Valid SignInRequest requestBody) {
        var username = requestBody.username();
        var password = requestBody.password();

        return authService.signIn(username, password);
    }
}
