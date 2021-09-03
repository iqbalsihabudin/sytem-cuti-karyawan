package uas.kel2.sytemcutikaryawan.service;


import org.hibernate.Filter;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import uas.kel2.sytemcutikaryawan.models.Employee;
import uas.kel2.sytemcutikaryawan.repo.EmployeeRepo;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;

@Service
@Transactional
public class EmployeeService implements UserDetailsService {

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private EntityManager entityManager;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return employeeRepo.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format("user with email '%s' not found", username)));
    }

    public Employee registerEmployee(Employee user){

        boolean userExists = employeeRepo.findByUsername(user.getUsername()).isPresent();
        if(user.getEmployee_id() != null){
            System.out.println("update");
            Employee currentEmployee = employeeRepo.findById(user.getEmployee_id()).get();
            currentEmployee.setUsername(user.getUsername());
            currentEmployee.setEmail(user.getEmail());
            currentEmployee.setNamaLengkap(user.getNamaLengkap());
            currentEmployee.setDivisi(user.getDivisi());
            user = currentEmployee;
        }else {
            if (userExists){
                throw new RuntimeException(
                        String.format("user with username '%s' already exists",user.getUsername())
                );
            }
            String encondedPassword = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encondedPassword);
        }

        return employeeRepo.save(user);
    }

    public Employee changePassword(Employee user){

            String encondedPassword = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encondedPassword);

        return employeeRepo.save(user);
    }


    public Iterable<Employee> findALl(boolean isDeleted){
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedEmployeeFilter");
        filter.setParameter("isDeleted", isDeleted);
        Iterable<Employee> employees = employeeRepo.findAll();
        session.disableFilter("deletedEmployeeFilter");
        return employees;
    }

    public void remove(Integer id){
        employeeRepo.deleteById(id);
    }

    public Integer employeeCount(){
        return employeeRepo.employeeCount();
    }

    public Employee userLogin(Principal p){
        return employeeRepo.findByUsername(p.getName()).get();
    }

    public Iterable<Employee> findAllByLimit(int start, int limit){return employeeRepo.findAllByLimit(start,limit);}

    public List<String> emailHRD(){
        return employeeRepo.emailHRD();
    }
}
