package me.letsdev.common.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    static ErrorCode defaultInstance() {
        return DefaultErrorCodeHolder.DEFAULT_ERROR_CODE;
    }

    HttpStatus httpStatus();
    String message();
    String messageKey();
    RuntimeException exception();
    RuntimeException exception(Throwable cause);

    // ... runnable, supplier 등을 받는 exception() 함수들 추가

    default int getCode() {
        return httpStatus().value();
    }

    class DefaultErrorCodeHolder {
        private static final ErrorCode DEFAULT_ERROR_CODE = new ErrorCode() {
            @Override
            public HttpStatus httpStatus() {
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }

            @Override
            public String message() {
                return "Server Error";
            }

            @Override
            public String messageKey() {
                return "";
            }

            @Override
            public RuntimeException exception() {
                return new CustomException(this);
            }

            @Override
            public RuntimeException exception(Throwable cause) {
                return new CustomException(this, cause);
            }
        };
    }
}
