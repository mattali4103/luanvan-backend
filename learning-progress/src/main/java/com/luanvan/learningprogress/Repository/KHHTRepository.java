package com.luanvan.learningprogress.Repository;

import com.luanvan.learningprogress.entity.KeHoachHocTap;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KHHTRepository extends JpaRepository<KeHoachHocTap, Long>{

}
