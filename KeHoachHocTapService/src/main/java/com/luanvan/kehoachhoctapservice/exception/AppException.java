package com.luanvan.kehoachhoctapservice.exception;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AppException extends RuntimeException {
    private ErrorCode errorCode;

    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    // Constructor để hỗ trợ message tùy chỉnh
    public AppException(ErrorCode errorCode, String customMessage) {
        super(customMessage);
        this.errorCode = errorCode;
    }
}
