package uas.kel2.sytemcutikaryawan.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uas.kel2.sytemcutikaryawan.dto.EmployeeDto;
import uas.kel2.sytemcutikaryawan.dto.ResponseData;
import uas.kel2.sytemcutikaryawan.models.Employee;
import uas.kel2.sytemcutikaryawan.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/register")
    public ResponseEntity<ResponseData<Employee>> register(@RequestBody EmployeeDto employeeDto){
        ResponseData<Employee> response = new ResponseData<>();
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        response.setPayLoad(employeeService.registerEmployee(employee));
        response.setStatus(true);
        response.getMessages().add("employee saved!!");
        return ResponseEntity.ok(response);

    }

    @GetMapping("/cek")
    public String cek(){
        return "selamat";
    }

    @GetMapping("/findAll")
    public Iterable<Employee> findAll(){
        return employeeService.findALl();

    }
}
