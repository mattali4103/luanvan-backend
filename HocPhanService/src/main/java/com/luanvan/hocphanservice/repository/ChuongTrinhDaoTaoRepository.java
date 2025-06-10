package com.luanvan.hocphanservice.repository;

import com.luanvan.hocphanservice.entity.ChuongTrinhDaoTao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ChuongTrinhDaoTaoRepository extends JpaRepository<ChuongTrinhDaoTao, Long> {
    Optional<ChuongTrinhDaoTao> findByMaNganh(Long maNganh);
}
