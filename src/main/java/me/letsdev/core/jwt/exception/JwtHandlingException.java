package me.letsdev.core.jwt.exception;

import me.letsdev.common.exception.CustomException;
import me.letsdev.common.exception.ErrorCode;

public class JwtHandlingException extends CustomException {
    public JwtHandlingException(ErrorCode errorCode) {
        super(errorCode);
    }

    public JwtHandlingException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
