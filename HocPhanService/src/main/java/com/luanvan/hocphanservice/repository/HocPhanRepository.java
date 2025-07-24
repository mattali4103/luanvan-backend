package com.luanvan.hocphanservice.repository;

import com.luanvan.hocphanservice.entity.HocPhan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HocPhanRepository extends JpaRepository<HocPhan, String> {
    Optional<HocPhan> findByTenHp(String tenHp);

    List<HocPhan> findByMaHpIn(List<String> maHocPhanList);

    List<HocPhan> findByMaHpNotIn(List<String> maHocPhanList);

    @Query("SELECT COALESCE(SUM(hp.tinChi), 0) FROM HocPhan hp WHERE hp.maHp IN :maHocPhanList")
    Long countTinChiIn(@Param("maHocPhanList") List<String> maHocPhanList);

    @SuppressWarnings("unused")
    @Query("SELECT hp from HocPhan hp JOIN hp.chuongTrinhDaoTaoList")
    List<HocPhan> findHocPhanInChuongTrinhDaoTao();

    @Query("SELECT hp FROM HocPhan hp JOIN hp.chuongTrinhDaoTaoList ctdt WHERE hp.loaiHp LIKE :loaiHp AND ctdt.khoaHoc = :khoaHoc AND ctdt.maNganh = :maNganh")
    List<HocPhan> findHocPhanByLoaiHpInChuongTrinhDaoTao(@Param("loaiHp") String loaiHp, @Param("khoaHoc") String khoaHoc, @Param("maNganh") Long maNganh);


    List<HocPhan> findByTenHpLike(String tenHp);
    @Query("SELECT hp FROM HocPhan hp JOIN hp.chuongTrinhDaoTaoList ctdt WHERE hp.loaiHp LIKE :loaiHp")
    List<HocPhan> findByLoaiHpLike(@Param("loaiHp") String loaiHp);
}


