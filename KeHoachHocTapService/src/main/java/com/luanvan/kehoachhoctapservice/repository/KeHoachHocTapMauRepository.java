package com.luanvan.kehoachhoctapservice.repository;

import com.luanvan.kehoachhoctapservice.entity.KeHoachHocTapMau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeHoachHocTapMauRepository extends JpaRepository<KeHoachHocTapMau, Long> {
    boolean existsByKhoaHocAndMaNganh(String khoaHoc, Long maNganh);

    List<KeHoachHocTapMau> findByKhoaHocAndMaNganh(String khoaHoc, Long maNganh);
    List<KeHoachHocTapMau> findByKhoaHocAndMaNganhAndMaHocKy(String khoaHoc, Long maNganh, Long maHocKy);
}
