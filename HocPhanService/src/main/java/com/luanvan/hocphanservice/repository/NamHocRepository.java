package com.luanvan.hocphanservice.repository;

import com.luanvan.hocphanservice.entity.HocKy;
import com.luanvan.hocphanservice.entity.NamHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface NamHocRepository extends JpaRepository<NamHoc,Long> {
    Optional<NamHoc> findByNamBatDau(String namBatDau);
    Optional<NamHoc> findByNamBatDauAndNamBatDau(String namBatDau, String namKetThuc);
    List<NamHoc> findByIdIn(List<Long> ids);
}
