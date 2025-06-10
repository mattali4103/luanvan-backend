package com.luanvan.hocphanservice.repository;

import com.luanvan.hocphanservice.entity.HocKy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HocKyRepository extends JpaRepository<HocKy,Long> {

    boolean existsByNamHoc_Id(Long id);
}
