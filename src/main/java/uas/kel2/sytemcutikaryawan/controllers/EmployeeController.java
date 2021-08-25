package uas.kel2.sytemcutikaryawan.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uas.kel2.sytemcutikaryawan.dto.EmployeeDto;
import uas.kel2.sytemcutikaryawan.dto.ResponseData;
import uas.kel2.sytemcutikaryawan.models.Employee;
import uas.kel2.sytemcutikaryawan.models.Libur;
import uas.kel2.sytemcutikaryawan.service.EmployeeService;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/employeeCount")
    public Integer jumlah(){
        return employeeService.employeeCount();
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseData<Employee>> register(@RequestBody EmployeeDto employeeDto){
        ResponseData<Employee> response = new ResponseData<>();
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        response.setPayLoad(employeeService.registerEmployee(employee));
        System.out.println(employeeDto.getDivisi());
        response.setStatus(true);
        response.getMessages().add("employee saved!!");
        return ResponseEntity.ok(response);

    }

    @PutMapping("/updateEmployee")
    public ResponseEntity<ResponseData<Employee>> update(@RequestBody EmployeeDto employeeDto){
        ResponseData<Employee> response = new ResponseData<>();
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        response.setPayLoad(employeeService.registerEmployee(employee));
        System.out.println(employeeDto.getDivisi());
        response.setStatus(true);
        response.getMessages().add("employee update!!");
        return ResponseEntity.ok(response);

    }

    @GetMapping("/findAll")
    public Iterable<Employee> findAll(@RequestParam(value = "isDeleted", required = false, defaultValue = "false") boolean isDeleted){
        return employeeService.findALl(isDeleted);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Integer id){
        employeeService.remove(id);
    }

    @GetMapping("/userLogin")
    public Optional<Employee> userLogin(Principal p){
        Employee employee = employeeService.userLogin(p);
        return Optional.of(employee);
    }
}
