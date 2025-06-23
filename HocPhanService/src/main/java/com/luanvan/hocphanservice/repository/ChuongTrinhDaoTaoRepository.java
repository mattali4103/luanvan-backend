package com.luanvan.hocphanservice.repository;

import com.luanvan.hocphanservice.entity.ChuongTrinhDaoTao;
import com.luanvan.hocphanservice.entity.HocPhan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Optional;

public interface ChuongTrinhDaoTaoRepository extends JpaRepository<ChuongTrinhDaoTao, String> {
    Optional<ChuongTrinhDaoTao> findByMaNganh(Long maNganh);
    @Query("SELECT COALESCE(SUM(hp.tinChi), 0) FROM ChuongTrinhDaoTao ctdt JOIN ctdt.hocPhanList hp WHERE ctdt.khoaHoc = :khoaHoc")
    Long tongTinChi(@Param("khoaHoc") String khoaHoc);

}
