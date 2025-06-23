package com.luanvan.hocphanservice.repository;

import com.luanvan.hocphanservice.entity.NhomHP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface NhomHPRepository extends JpaRepository<NhomHP, String> {
    Optional<NhomHP> findById(String maNhomHP);
}
