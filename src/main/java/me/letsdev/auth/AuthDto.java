package me.letsdev.auth;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

public final class AuthDto {
    private AuthDto() {}

    @Builder
    public record SignInRequest(
            @NotBlank(message = "Username must not be blank.")
            @Size(min = 3, message = "Username must be at least 3 characters long.")
            @Size(max = 30, message = "Username must be no more than 30 characters long.")
            String username,

            @NotBlank(message = "Password must not be blank.")
            @Size(min = 3, message = "Password must be at least 3 characters long.")
            @Size(max = 100, message = "Password must be no more than 100 characters long.")
            String password
    ) {}
}
