package uas.kel2.sytemcutikaryawan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uas.kel2.sytemcutikaryawan.models.JenisCuti;
import uas.kel2.sytemcutikaryawan.models.StatusCuti;

public interface StatusCutiRepo extends JpaRepository<StatusCuti, Integer> {

}
