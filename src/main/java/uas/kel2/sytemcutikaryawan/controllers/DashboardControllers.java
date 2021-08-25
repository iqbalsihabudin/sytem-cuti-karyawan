package uas.kel2.sytemcutikaryawan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uas.kel2.sytemcutikaryawan.models.Employee;
import uas.kel2.sytemcutikaryawan.models.PengajuanCuti;
import uas.kel2.sytemcutikaryawan.service.EmployeeService;
import uas.kel2.sytemcutikaryawan.service.PengajuanCutiService;

@Controller
public class DashboardControllers {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PengajuanCutiService pengajuanCutiService;

    @GetMapping("/Dashboard")
    String Dashboard(Model model){
        Iterable<PengajuanCuti> cuti = pengajuanCutiService.findALl(false);
        Iterable<Employee> Karyawan = employeeService.findALl(false);
        model.addAttribute("Employee",Karyawan);
        model.addAttribute("cuti",cuti);
        return "Dashboard";
    }

    @GetMapping("/Profile")
    String user(Model model){

        return "user";
    }

}
