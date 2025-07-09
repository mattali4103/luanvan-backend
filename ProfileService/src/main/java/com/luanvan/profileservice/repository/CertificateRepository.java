package com.luanvan.profileservice.repository;

import com.luanvan.profileservice.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    @Query("SELECT c FROM Certificate c JOIN c.sinhVien s WHERE s.maSo = :maSo")
    List<Certificate> findBySinhVienMaSo(String maSo);

}
