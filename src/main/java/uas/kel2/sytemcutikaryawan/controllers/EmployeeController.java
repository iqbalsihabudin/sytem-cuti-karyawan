package uas.kel2.sytemcutikaryawan.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uas.kel2.sytemcutikaryawan.dto.EmployeeDto;
import uas.kel2.sytemcutikaryawan.dto.ResponseData;
import uas.kel2.sytemcutikaryawan.models.Employee;
import uas.kel2.sytemcutikaryawan.service.EmployeeService;

@RestController
@RequestMapping
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
}
