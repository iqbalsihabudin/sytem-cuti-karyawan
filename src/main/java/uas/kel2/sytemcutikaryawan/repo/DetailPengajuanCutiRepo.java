package uas.kel2.sytemcutikaryawan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uas.kel2.sytemcutikaryawan.models.DetailPengajuanCuti;
import uas.kel2.sytemcutikaryawan.models.PengajuanCuti;

public interface DetailPengajuanCutiRepo extends JpaRepository<DetailPengajuanCuti, Integer> {
    public DetailPengajuanCuti findByDetailPengajuanCutiId(Integer id);

    @Query(value = "select * from tbl_detail_pengajuan_cuti  LIMIT :limit OFFSET :start" , nativeQuery = true)
    public Iterable<DetailPengajuanCuti> findAllByLimit(@Param("start") int start, @Param("limit") int limit);
}
