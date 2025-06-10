package com.luanvan.hocphanservice.repository;

import com.luanvan.hocphanservice.entity.HocPhan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HocPhanRepository extends JpaRepository<HocPhan, String> {
    Optional<HocPhan> findByTenHp(String tenHp);
    List<HocPhan> findByMaHpIn(List<String> maHocPhanList);

}
