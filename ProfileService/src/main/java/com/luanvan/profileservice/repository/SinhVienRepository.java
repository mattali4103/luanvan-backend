package com.luanvan.profileservice.repository;

import com.luanvan.profileservice.entity.Lop;
import com.luanvan.profileservice.entity.SinhVien;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SinhVienRepository extends JpaRepository<SinhVien, String> {
    List<SinhVien> findSinhViensByLop(Lop lop);
}
