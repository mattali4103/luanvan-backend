package com.luanvan.kehoachhoctapservice.repository;
import com.luanvan.kehoachhoctapservice.entity.KeHoachHocTap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KeHoachHocTapRepository extends JpaRepository<KeHoachHocTap, Long> {
    Optional<KeHoachHocTap> findByMaSoAndMaHocPhan(String maSo, String maHocPhan);

//    Tìm khht theo mã số sv
    List<KeHoachHocTap> findKeHoachHocTapsByMaSo(String maSo);

    List<KeHoachHocTap> findKeHoachHocTapsByMaSoAndHocPhanCaiThien(String maSo, boolean hocPhanCaiThien);
    List<KeHoachHocTap> findByMaSoAndMaHocKy(String maSo, Long maHocKy);



    Long countByMaSoAndHocPhanCaiThien(String maSo, Boolean hocPhanCaiThien);
    Long countKeHoachHocTapsByMaSo(String maSo);
    Long countKeHoachHocTapByMaSoAndMaHocKy(String maSo, Long maHocKy);
    Long countKeHoachHocTapByMaSoAndMaHocKyAndHocPhanCaiThien(String maSo, Long maHocKy, boolean b);
    @Query("SELECT k.maHocPhan FROM KeHoachHocTap k WHERE k.maSo = :maSo")
    List<String> findMaHocPhanByMaSo(@Param("maSo") String maSo);
}
