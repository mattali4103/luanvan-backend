package com.luanvan.kehoachhoctapservice.repository;

import com.luanvan.kehoachhoctapservice.entity.KeHoachHocTap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KeHoachHocTapRepository extends JpaRepository<KeHoachHocTap, Long> {
    Optional<KeHoachHocTap> findByMaSoAndMaHocPhan(String maSo, String maHocPhan);

    //Lấy mã học kỳ gần nhất theo mã số sinh viên
    @Query("SELECT DISTINCT k.maHocKy FROM KeHoachHocTap k WHERE k.maSo = :maSo ORDER BY k.maHocKy DESC LIMIT 1")
    Optional<Long> findLatestMaHocKyByMaSo(@Param("maSo") String maSo);

    @Query("SELECT DISTINCT k.maHocKy FROM KeHoachHocTap k WHERE k.maSo = :maSo ORDER BY k.maHocKy")
    List<Long> findMaHocKyByMaSo(@Param("maSo") String maSo);
    //    Tìm khht theo mã số sv
    List<KeHoachHocTap> findKeHoachHocTapsByMaSo(String maSo);
    List<KeHoachHocTap> findKeHoachHocTapsByMaSoAndMaHocKy(String maSo, Long maHocKy);

    //Tìm kế hoạch học tập theo mã số và học kỳ gần nhất
    @Query("SELECT k FROM KeHoachHocTap k WHERE k.maSo = :maSo AND k.maHocKy = (SELECT MAX(kh.maHocKy) FROM KeHoachHocTap kh WHERE kh.maSo = :maSo)")
    List<KeHoachHocTap> findLatestKeHoachHocTapByMaSo(@Param("maSo") String maSo);

    @Query("SELECT k.maHocPhan FROM KeHoachHocTap k WHERE k.maSo = :maSo")
    List<String> findMaHocPhanByMaSo(@Param("maSo") String maSo);

    Page<KeHoachHocTap> findKeHoachHocTapsByMaSo(String maSo, Pageable pageable);
    Long countKeHoachHocTapByMaSoAndHocPhanCaiThien(String maSo, boolean hocPhanCaiThien);
}
