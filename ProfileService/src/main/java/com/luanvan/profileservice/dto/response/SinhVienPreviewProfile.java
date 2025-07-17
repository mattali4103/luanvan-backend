package com.luanvan.profileservice.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import com.fasterxml.jackson.annotation.JsonInclude;

/*
    Dùng để preview thông tin sinh viên trong profile.
    Chứa các thông tin cơ bản như tên, mã số sinh viên, lớp học, chuyên ngành, v.v.
    Thông tin điểm số trung bình tích luỹ, học phần tích luỹ, xếp loại học lực, v.v. sẽ được lấy từ các service khác.
    Thông tin học phần đăng ký
    Không chứa thông tin chi tiết điểm

*/


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SinhVienPreviewProfile {
    String avatarUrl;
    String maSo;
    String hoTen;
    String ngaySinh;
    boolean gioiTinh;
    String khoaHoc;
    String maLop;
    String tenNganh;
    String xepLoaiHocLuc;
    Double diemTrungBinhTichLuy;
    Long soTinChiTichLuy;
    Long soTinChiCaiThien;
    CanhBaoHocVu canhBaoHocVu;
    // Số tín chỉ đăng ký ở học kỳ hiện tại
    Long soTinChiDangKyHienTai;
}
