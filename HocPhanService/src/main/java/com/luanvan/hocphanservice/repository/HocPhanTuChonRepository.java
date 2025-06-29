package com.luanvan.hocphanservice.repository;


import com.luanvan.hocphanservice.entity.HocPhanTuChon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface HocPhanTuChonRepository extends JpaRepository<HocPhanTuChon, Long> {
    @Query("SELECT hptc FROM HocPhanTuChon hptc " +
            "WHERE LOWER(hptc.tenNhom) LIKE LOWER(:tenNhom) " +
            "AND hptc.chuongTrinhDaoTao.khoaHoc = :khoaHoc")
    Optional<HocPhanTuChon> findByTenNhomLikeAndChuongTrinhDaoTao_KhoaHoc(
            @Param("tenNhom") String tenNhom,
            @Param("khoaHoc") String khoaHoc);
}
