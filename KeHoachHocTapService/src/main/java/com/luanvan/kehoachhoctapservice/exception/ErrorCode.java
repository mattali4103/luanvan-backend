package com.luanvan.kehoachhoctapservice.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    UNAUTHENTICATED(4001,"Xác thực thất bại" ),
    USER_NOTFOUND(4002, "Người dùng không tồn tại"),
    INVALID_PASSWORD(4003, "Sai mật khẩu" ),
    NOTFOUND(4005, "Không tồn tại dữ liệu đã yêu cầu" ),
    EXISTED(4006, "Dữ liệu đã tồn tại" ),
    KHHT_EXISTED(4007, "Sinh viên đã nhập học phần này vào kế hoạch học tập rồi"),
    INVALID_REQUEST(4004, "Yêu cầu không hợp lệ" ),
    KHHT_NOTFOUND(4008,"Không thể thêm học phần cải thiện, do học phần chưa có trong kế hoạch học tập" ),
    HOCPHAN_NOTFOUND(4009, "Không tìm thấy học phần"),
    KHHT_FOUND_BUT_HOCPHAN_NOTFOUND(4011, "Kế hoạch học tập đã tồn tại nhưng học phần không tồn tại trong hệ thống"),
    KHHT_EXISTED_NOTFOUND(4012, "Không tìm thấy kế hoạch học tập đã yêu cầu"),
    ;
    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}


