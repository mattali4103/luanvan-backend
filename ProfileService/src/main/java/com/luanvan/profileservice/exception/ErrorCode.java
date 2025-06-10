package com.luanvan.profileservice.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    UNAUTHENTICATED(4001,"Xác thực thất bại" ),
    USER_NOTFOUND(4002, "Người dùng không tồn tại"),
    INVALID_PASSWORD(4003, "Sai mật khẩu" ),
    INVALID_REQUEST(4003, "Yêu cầu không hợp lệ" ),
    INVALID_INPUT(4004,"DiemDTO cannot be null" ),
    NOTFOUND(4005, "Không tồn tại dữ liệu đã yêu cầu" ),
    EXISTED(4006, "Dữ liệu đã tồn tại" ),
    NGANH_NOTNULL(4007, "Ngành không được để trống"),
    ;
    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}


