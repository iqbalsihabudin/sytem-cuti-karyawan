package uas.kel2.sytemcutikaryawan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uas.kel2.sytemcutikaryawan.models.Employee;
import uas.kel2.sytemcutikaryawan.models.PengajuanCuti;
import uas.kel2.sytemcutikaryawan.service.EmployeeService;
import uas.kel2.sytemcutikaryawan.service.PengajuanCutiService;

import java.security.Principal;
import java.util.Optional;

@Controller
public class TemplateControllers {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PengajuanCutiService pengajuanCutiService;

    @Autowired
    private EmployeeController employeeController;

    @GetMapping("/Dashboard")
    String Dashboard(Model model, Principal principal){
        Optional<Employee> userlogin = employeeController.userLogin(principal);
        Iterable<PengajuanCuti> cuti = pengajuanCutiService.findALl(false);
        Iterable<Employee> Karyawan = employeeService.findALl(false);
        model.addAttribute("Employee",Karyawan);
        model.addAttribute("cuti",cuti);
        model.addAttribute("user",userlogin);
        return "Dashboard";
    }

    @GetMapping("/Profile")
    String user(Model model){
        return "user";
    }

    @GetMapping("/AboutUs")
    String AboutUs(Model model){
        return "AboutUs";
    }

    @GetMapping("/RequestLeave")
    String RequestLeave(Model model){

        return "RequestLeave";
    }

}
