package uas.kel2.sytemcutikaryawan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uas.kel2.sytemcutikaryawan.models.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByUsername(String username);

    @Query("SELECT COUNT(u)  FROM Employee u WHERE u.deleted = false AND u.role.roleId = 2")
    public Integer employeeCount();

    @Query("SELECT u.email FROM Employee u WHERE u.deleted = false AND u.role.roleId = 1")
    public List<String> emailHRD();

    @Query(value = "select * from tbl_employee  LIMIT :limit OFFSET :start" , nativeQuery = true)
    public Iterable<Employee> findAllByLimit(@Param("start") int start,@Param("limit") int limit);


}
