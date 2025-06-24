package com.luanvan.hocphanservice.repository;

import com.luanvan.hocphanservice.entity.HocKy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HocKyRepository extends JpaRepository<HocKy,Long> {

    boolean existsByNamHoc_Id(Long id);

    List<HocKy> findByMaHocKyIn(List<Long> hocPhanList);
    @Query("SELECT h FROM HocKy h " +
            "JOIN NamHoc n " +
            "ON h.namHoc.id = n.id " +
            "WHERE n.namBatDau = :namBatDau " +
            "AND n.namKetThuc = :namKetThuc " +
            "AND h.tenHocKy LIKE %:tenHocKy%")
    HocKy findNamHocByName(String namBatDau, String namKetThuc, String tenHocKy);

}
