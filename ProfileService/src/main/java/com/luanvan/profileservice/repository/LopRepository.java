package com.luanvan.profileservice.repository;

import com.luanvan.profileservice.entity.Lop;
import com.luanvan.profileservice.entity.Nganh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LopRepository extends JpaRepository<Lop, String> {
    List<Lop> findAllByNganhIn(List<Nganh> nganhs);

    @Query("SELECT COUNT(sv) FROM SinhVien sv WHERE sv.lop.maLop = :maLop")
    Long countSinhVienByMaLop(@Param("maLop") String maLop);

    @Modifying
    @Transactional
    @Query("UPDATE Lop l SET l.siSoCon = :siSoCon WHERE l.maLop = :maLop")
    void updateSiSoCon(@Param("maLop") String maLop, @Param("siSoCon") Long siSoCon);
    List<Lop> findByNganhMaNganh(Long maNganh);

    List<Lop> findByChuNhiem_MaSo(String maGiangVien);
}
