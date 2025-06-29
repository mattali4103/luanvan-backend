package com.luanvan.profileservice.repository;

import com.luanvan.profileservice.entity.Nganh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NganhRepository extends JpaRepository<Nganh, Long> {
    @Query("SELECT n FROM Nganh n JOIN n.khoa k WHERE k.maKhoa = :maKhoa")
    List<Nganh> findNganhsByMaKhoa(@Param("maKhoa")String maKhoa);
}
