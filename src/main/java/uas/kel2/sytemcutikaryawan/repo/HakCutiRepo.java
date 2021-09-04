package uas.kel2.sytemcutikaryawan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uas.kel2.sytemcutikaryawan.models.HakCuti;
import uas.kel2.sytemcutikaryawan.models.PengajuanCuti;

public interface HakCutiRepo extends JpaRepository<HakCuti, Integer> {
    public HakCuti findByHakCutiId(Integer integer);
//    public HakCuti findHakCutiByJenisCutiJenisCutiIdAndEmployeeEmployee_id(Integer idJen, Integer idEmp);

    @Query("SELECT u FROM HakCuti u WHERE u.jenisCuti.jenisCutiId= :idJen AND u.employee.employee_id= :idEmp")
    public HakCuti findByIdJenAndIdEmp(Integer idJen, Integer idEmp);
}
