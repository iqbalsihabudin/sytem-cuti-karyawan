package uas.kel2.sytemcutikaryawan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uas.kel2.sytemcutikaryawan.models.Employee;
import uas.kel2.sytemcutikaryawan.service.EmployeeService;

@Controller
public class DashboardControllers {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/Dashboard")
    String Dashboard(Model model){
        Iterable<Employee> Karyawan = employeeService.findALl(false);
        model.addAttribute("Employee",Karyawan);
        return "Dashboard";
    }

}
