package com.luanvan.hocphanservice.repository;

import com.luanvan.hocphanservice.entity.NamHoc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NamHocRepository extends JpaRepository<NamHoc,Long> {
    Optional<NamHoc> findByNamBatDau(String namBatDau);

}
