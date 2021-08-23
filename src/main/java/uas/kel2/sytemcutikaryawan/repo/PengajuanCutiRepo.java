package uas.kel2.sytemcutikaryawan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uas.kel2.sytemcutikaryawan.models.PengajuanCuti;

public interface PengajuanCutiRepo extends JpaRepository<PengajuanCuti, Integer> {

    @Query("SELECT COUNT(e) from PengajuanCuti e WHERE e.deleted=FALSE AND e.statusCuti.statusCutiId=2")
    public Integer pengajuanCutiCountOpen();

    @Query("SELECT COUNT(e) from PengajuanCuti e WHERE e.deleted=FALSE AND e.statusCuti.statusCutiId<>1")
    public Integer pengajuanCutiCountAll();

}
