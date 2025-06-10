package com.luanvan.ketquahoctapservice.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    KET_QUA_HOC_TAP_EMPTY(4001, "Lỗi kết quả học tập không tồn tại"),;
    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}


