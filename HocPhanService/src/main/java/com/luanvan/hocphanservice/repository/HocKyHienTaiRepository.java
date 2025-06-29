package com.luanvan.hocphanservice.repository;
import com.luanvan.hocphanservice.entity.HocKyHienTai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HocKyHienTaiRepository extends JpaRepository<HocKyHienTai,Long> {
    @Query("SELECT maHocKy FROM HocKyHienTai")
    Long findMaHocKyHienTai();
}
