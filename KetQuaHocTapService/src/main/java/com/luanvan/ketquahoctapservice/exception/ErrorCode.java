package com.luanvan.ketquahoctapservice.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNAUTHENTICATED(4001,"Xác thực thất bại" ),
    USER_NOTFOUND(4002, "Người dùng không tồn tại"),
    INVALID_PASSWORD(4003, "Sai mật khẩu" ),
    INVALID_REQUEST(4004, "Yêu cầu không hợp lệ" ),
    NOTFOUND(4005, "Không tồn tại dữ liệu đã yêu cầu" ),
    EXISTED(4006, "Dữ liệu đã tồn tại" ),
    KHHT_EXISTED(4007, "Sinh viên đã nhập học phần này vào kế hoạch học tập rồi"),
    KHHT_NOTFOUND(4008,"Không thể thêm học phần cải thiện, do học phần chưa có trong kế hoạch học tập" ),
    HOCPHAN_NOTFOUND(4009, "Không tìm thấy học phần"), INVALID_INPUT(4016, "Lỗi khi nhập file"),
    KET_QUA_HOC_TAP_EMPTY(4020, "Kết quả học tập");
    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}


