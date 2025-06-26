package com.luanvan.hocphanservice.repository;

import com.luanvan.hocphanservice.entity.ChuongTrinhDaoTao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ChuongTrinhDaoTaoRepository extends JpaRepository<ChuongTrinhDaoTao, Long> {
    Optional<ChuongTrinhDaoTao> findByMaNganh(Long maNganh);

    @Query("SELECT SUM(hp.tinChi) FROM ChuongTrinhDaoTao ctdt JOIN ctdt.hocPhanList hp WHERE ctdt.khoaHoc = :khoaHoc AND ctdt.maNganh = :maNganh")
    Long tongTinChi(@Param("khoaHoc") String khoaHoc, @Param("maNganh") Long maNganh);
    Optional<ChuongTrinhDaoTao> findByKhoaHocAndMaNganh(String khoaHoc, Long maNganh);

    Boolean existsChuongTrinhDaoTaoByKhoaHocAndMaNganh(String khoaHoc, Long maNganh);
}
