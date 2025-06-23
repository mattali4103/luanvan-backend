package com.luanvan.ketquahoctapservice.Repository;

import com.luanvan.ketquahoctapservice.entity.KetQuaHocTap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KetQuaHocTapRepository extends JpaRepository<KetQuaHocTap, Long> {
    List<KetQuaHocTap> findByMaSo(String maSo);
    Page<KetQuaHocTap> findByMaSo(String maSo, Pageable pageable);
    List<KetQuaHocTap> findByMaSoAndMaHocKy(String maSo, Long maHocKy);
    List<KetQuaHocTap> findByMaSoAndMaHocKyIsLessThanEqual(String maSo, Long maHocKy);
    @Query("SELECT DISTINCT k.maHocKy FROM KetQuaHocTap k WHERE k.maSo = :maSo")
    List<Long> findMaHocKyByMaSo(@Param("maSo") String maSo);
    @Query("SELECT k " +
            "FROM KetQuaHocTap k " +
            "WHERE k.maSo = :maSo AND k.diemSo <= 7.0 " +
            "ORDER BY k.diemSo ASC")
    List<KetQuaHocTap> findMaHpByMaSoAndDiemSoLessThan7(@Param("maSo") String maSo);
    @Query("SELECT k " +
            "FROM KetQuaHocTap k " +
            "WHERE k.maSo = :maSo AND k.diemChu = 'F'"
    )
    List<KetQuaHocTap> findKetQuaHocTapsByDiemChuEqualsIgnoreCase(@Param("maSo") String maSo);
}
