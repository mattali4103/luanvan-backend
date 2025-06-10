package com.luanvan.kehoachhoctapservice.repository;

import com.luanvan.kehoachhoctapservice.entity.KeHoachHocTap;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KeHoachHocTapRepository extends JpaRepository<KeHoachHocTap, Long> {
    Optional<KeHoachHocTap> findByMaSoAndMaHocPhan(String maSo, String maHocPhan);
    KeHoachHocTap findByMaSo(String maSo);
//    Tìm khht theo mã số sv
    List<KeHoachHocTap> findKeHoachHocTapsByMaSo(String maSo);
    List<KeHoachHocTap> findKeHoachHocTapsByMaSoAndHocPhanCaiThien(String maSo, boolean hocPhanCaiThien);
    List<KeHoachHocTap> findByMaSoAndMaHocKy(String maSo, String maHocKy);
    Long countByMaSoAndHocPhanCaiThien(String maSo, Boolean hocPhanCaiThien);
    Long countKeHoachHocTapsByMaSo(String maSo);
    Long countKeHoachHocTapByMaSoAndMaHocKy(String maSo, String maHocKy);
    Long countKeHoachHocTapByMaSoAndMaHocKyAndHocPhanCaiThien(String maSo, String maHocKy, boolean b);
}
