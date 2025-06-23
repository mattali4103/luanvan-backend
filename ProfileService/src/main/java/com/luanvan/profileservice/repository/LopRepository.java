package com.luanvan.profileservice.repository;

import com.luanvan.profileservice.entity.Lop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LopRepository extends JpaRepository<Lop, String> {

}
