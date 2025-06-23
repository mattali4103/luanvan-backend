package com.luanvan.hocphanservice.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNAUTHENTICATED(4001,"Xác thực thất bại" ),
    USER_NOTFOUND(4002, "Người dùng không tồn tại"),
    INVALID_PASSWORD(4003, "Sai mật khẩu" ),
    USER_EXISTED(4004, "Không thể tạo, người dùng đã tồn tại" ),
    INVALID_REQUEST(4004, "Yêu cầu không hợp lệ" ),
    NOTFOUND(4005, "Không tồn tại dữ liệu đã yêu cầu" ),
    EXISTED(4006, "Dữ liệu đã tồn tại" ),
    HOCKY_EXISTED(4010, "Học kỳ đã tồn tại trong năm học này"),
    INVALID_INPUT(4011,"Lỗi khi đọc file excel" ),
    KE_HOACH_HOC_TAP_EMPTY(4013,"Kế hoạch học tập rỗng, liên hệ quản trị viên để được hỗ trợ"),
    ;
    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}


