package com.luanvan.profileservice.repository;

import com.luanvan.profileservice.entity.Lop;
import com.luanvan.profileservice.entity.Nganh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LopRepository extends JpaRepository<Lop, String> {
    List<Lop> findAllByNganhIn(List<Nganh> nganhs);

}
