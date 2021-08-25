package uas.kel2.sytemcutikaryawan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uas.kel2.sytemcutikaryawan.models.DetailPengajuanCuti;

public interface DetailPengajuanCutiRepo extends JpaRepository<DetailPengajuanCuti, Integer> {
    public DetailPengajuanCuti findByDetailPengajuanCutiId(Integer id);
}
