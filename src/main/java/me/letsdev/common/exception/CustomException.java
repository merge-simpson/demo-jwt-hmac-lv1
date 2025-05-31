package me.letsdev.common.exception;

public class CustomException extends RuntimeException {
    protected final ErrorCode errorCode;

    public CustomException() {
        super(ErrorCode.defaultInstance().message());
        this.errorCode = ErrorCode.defaultInstance();
    }

    public CustomException(ErrorCode errorCode) {
        super(errorCode.message());
        this.errorCode = errorCode;
    }

    public CustomException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.message(), cause);
        this.errorCode = errorCode;
    }
}
