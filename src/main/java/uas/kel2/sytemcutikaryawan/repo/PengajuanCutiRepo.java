package uas.kel2.sytemcutikaryawan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uas.kel2.sytemcutikaryawan.models.Employee;
import uas.kel2.sytemcutikaryawan.models.PengajuanCuti;

public interface PengajuanCutiRepo extends JpaRepository<PengajuanCuti, Integer> {

    @Query("SELECT COUNT(e) from PengajuanCuti e WHERE e.deleted=FALSE AND e.statusCuti.statusCutiId=2")
    public Integer pengajuanCutiCountOpen();

    @Query("SELECT COUNT(e) from PengajuanCuti e WHERE e.deleted=FALSE AND e.statusCuti.statusCutiId<>1")
    public Integer pengajuanCutiCountAll();

    public PengajuanCuti findByPengajuanCutiId(Integer id);

    @Query(value = "select * from tbl_pengajuan_cuti  LIMIT :limit OFFSET :start" , nativeQuery = true)
    public Iterable<PengajuanCuti> findAllByLimit(@Param("start") int start, @Param("limit") int limit);

    @Query(value = "SELECT * FROM tbl_pengajuan_cuti  WHERE employee_id =:id" , nativeQuery = true)
    public PengajuanCuti pengajuanCutiById(@Param("id") int id);
}
