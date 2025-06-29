package com.luanvan.kehoachhoctapservice.repository;

import com.luanvan.kehoachhoctapservice.entity.KeHoachHocTapMau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KeHoachHocTapMauRepository extends JpaRepository<KeHoachHocTapMau, Long> {
    boolean existsByKhoaHocAndMaNganh(String khoaHoc, Long maNganh);
    List<KeHoachHocTapMau> findByKhoaHocAndMaNganh(String khoaHoc, Long maNganh);
    List<KeHoachHocTapMau> findByKhoaHocAndMaNganhAndMaHocKy(String khoaHoc, Long maNganh, Long maHocKy);

    /**
     * Tìm mã học kỳ gần nhất (nhỏ nhất) có sẵn trong kế hoạch học tập mẫu
     * cho khóa học và mã ngành cụ thể, bắt đầu từ mã học kỳ được chỉ định
     */
    @Query("SELECT MIN(k.maHocKy) FROM KeHoachHocTapMau k " +
           "WHERE k.khoaHoc = :khoaHoc AND k.maNganh = :maNganh " +
           "AND k.maHocKy >= :startHocKy")
    Optional<Long> findNearestMaHocKy(@Param("khoaHoc") String khoaHoc,
                                      @Param("maNganh") Long maNganh,
                                      @Param("startHocKy") Long startHocKy);

    /**
     * Tìm danh sách kế hoạch học tập mẫu theo khóa học, mã ngành và mã học kỳ gần nhất
     */
    @Query("SELECT k FROM KeHoachHocTapMau k " +
           "WHERE k.khoaHoc = :khoaHoc AND k.maNganh = :maNganh " +
           "AND k.maHocKy = (" +
           "    SELECT MIN(k2.maHocKy) FROM KeHoachHocTapMau k2 " +
           "    WHERE k2.khoaHoc = :khoaHoc AND k2.maNganh = :maNganh " +
           "    AND k2.maHocKy >= :startHocKy" +
           ")")
    List<KeHoachHocTapMau> findByKhoaHocAndMaNganhAndNearestMaHocKy(@Param("khoaHoc") String khoaHoc,
                                                                     @Param("maNganh") Long maNganh,
                                                                     @Param("startHocKy") Long startHocKy);

    List<KeHoachHocTapMau> findByMaNganh(Long maNganh);

    @Query("SELECT DISTINCT k.khoaHoc FROM KeHoachHocTapMau k WHERE k.maNganh = :maNganh")
    List<String> findKhoaHocByMaNganh(Long maNganh);

    List<KeHoachHocTapMau> findKeHoachHocTapMauByKhoaHocAndMaNganh(String khoaHoc, Long maNganh);

}
