package uas.kel2.sytemcutikaryawan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uas.kel2.sytemcutikaryawan.models.Employee;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByUsername(String username);

    @Query("SELECT COUNT(u)  FROM Employee u WHERE u.deleted = false AND u.role.roleId = 2")
    public Integer employeeCount();


}
