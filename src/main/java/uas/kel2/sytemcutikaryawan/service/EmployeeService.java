package uas.kel2.sytemcutikaryawan.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import uas.kel2.sytemcutikaryawan.models.Employee;
import uas.kel2.sytemcutikaryawan.repo.EmployeeRepo;
import uas.kel2.sytemcutikaryawan.utis.PasswordEncoder;

import javax.transaction.Transactional;

@Service
@Transactional
public class EmployeeService implements UserDetailsService {

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return employeeRepo.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format("user with email '%s' not found", username)));
    }

    public Employee registerEmployee(Employee user){
        boolean userExists = employeeRepo.findByUsername(user.getUsername()).isPresent();
        if (userExists){
            throw new RuntimeException(
                    String.format("user with username '%s' already exists",user.getUsername())
            );
        }

        String encondedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encondedPassword);
        return employeeRepo.save(user);
    }
}
