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

    @Query("SELECT DISTINCT k.maHocKy FROM KeHoachHocTap k WHERE k.maSo = :maSo")
    List<Long> findMaHocKyByMaSo(@Param("maSo") String maSo);
    //    Tìm khht theo mã số sv
    List<KeHoachHocTap> findKeHoachHocTapsByMaSo(String maSo);
    List<KeHoachHocTap> findKeHoachHocTapsByMaSoAndMaHocKy(String maSo, Long maHocKy);

    @Query("SELECT k.maHocPhan FROM KeHoachHocTap k WHERE k.maSo = :maSo")
    List<String> findMaHocPhanByMaSo(@Param("maSo") String maSo);

    Page<KeHoachHocTap> findKeHoachHocTapsByMaSo(String maSo, Pageable pageable);


}
