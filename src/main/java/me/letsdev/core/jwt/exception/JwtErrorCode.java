package me.letsdev.core.jwt.exception;

import me.letsdev.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum JwtErrorCode implements ErrorCode {
    BLANK_JWT_SECRET("JWT secret is required.", HttpStatus.INTERNAL_SERVER_ERROR),
    ;

    private final String message;
    private final HttpStatus status;

    JwtErrorCode(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    @Override
    public HttpStatus httpStatus() {
        return status;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public String messageKey() {
        return "";
    }

    @Override
    public JwtHandlingException exception() {
        return new JwtHandlingException(this);
    }

    @Override
    public JwtHandlingException exception(Throwable cause) {
        return new JwtHandlingException(this, cause);
    }
}
