package com.luanvan.profileservice.repository;

import com.luanvan.profileservice.entity.Diem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiemRepository extends JpaRepository<Diem, Long> {
    List<Diem> findDiemsBySinhVienMaSo(String maSo);
    Optional<Diem> findDiemByMaHocKy(Long maHocKy);
}
