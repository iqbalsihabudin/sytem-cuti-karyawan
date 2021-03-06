package uas.kel2.sytemcutikaryawan.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import uas.kel2.sytemcutikaryawan.dto.EmployeeDto;
import uas.kel2.sytemcutikaryawan.dto.ResponseData;
import uas.kel2.sytemcutikaryawan.dto.SandiDto;
import uas.kel2.sytemcutikaryawan.models.Employee;
import uas.kel2.sytemcutikaryawan.models.HakCuti;
import uas.kel2.sytemcutikaryawan.models.JenisCuti;
import uas.kel2.sytemcutikaryawan.service.EmailService;
import uas.kel2.sytemcutikaryawan.service.EmployeeService;
import uas.kel2.sytemcutikaryawan.service.HakCutiService;
import uas.kel2.sytemcutikaryawan.service.JenisCutiService;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    EmailService emailService;

    @Autowired
    private HakCutiService hakCutiService;

    @Autowired
    private JenisCutiService jenisCutiService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/employeeCount")
    public Integer jumlah(){
        return employeeService.employeeCount();
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseData<Employee>> register(@RequestBody EmployeeDto employeeDto) throws MessagingException, UnsupportedEncodingException {
        String pass = "123";
        ResponseData<Employee> response = new ResponseData<>();
        Employee user = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        employee.setPassword(pass);
        String[] email = new String[1];
        email[0] = employee.getEmail();
        response.setPayLoad(employeeService.registerEmployee(employee));
        String text = "Selamat bergabung di perusahaan kami. \n" +
                "Akun anda telah Berhasil di buat \n" +
                "Dengan rincian data sebagai berikut: \n" +
                "Nama       : "+employee.getNamaLengkap() + "\n" +
                "NIP        : "+employee.getNip() + "\n" +
                "Jabatan    : Karyawan\n" +
                "Divisi     : "+employee.getDivisi() + "\n" +
                "username   : "+employee.getUsername() +"\n" +
                "password   : "+pass + "\n" +
                "Berikut link untuk login http://localhost:8083/login \n" +
                "Semoga kita dapat bekerjasama dengan baik.\n" +
                "Hormat saya, \n" +
                " \n" +
                "HRD UAS JAVA \n" ;

       emailService.sendEmail(user.getEmail(), email,"succes create email", text);
        for(int a = 1 ; a <= 2 ; a++){
            HakCuti hakCuti = new HakCuti();
            if(a == 1){
                JenisCuti jenisCuti = jenisCutiService.findById(a);
                hakCuti.setJenisCuti(jenisCuti);
                hakCuti.setEmployee(employee);
                hakCuti.setSisaCuti(12);
            }else if(a == 2){
                JenisCuti jenisCuti = jenisCutiService.findById(a);
                hakCuti.setJenisCuti(jenisCuti);
                hakCuti.setEmployee(employee);
                hakCuti.setSisaCuti(0);
            }
            hakCutiService.save(hakCuti);
        }
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

    @GetMapping("/findAllByLimit")
    public  Iterable<Employee> findAllByLimit(@RequestParam(value = "start",defaultValue = "0") int start, @RequestParam(value = "limit",defaultValue = "10") int limit){
        return employeeService.findAllByLimit(start,limit);
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

    @GetMapping("/coba")
    public String user(){
        Employee a = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String text = "Akun anda telah Berhasil di buat \n" +
                "username : "+a.getUsername() +"\n" +
                "password : "+a.getPassword();
//        String text = "";
        return text;
    }


    @PostMapping("/changePassword")
    public Map<String, Object> changePassword(@RequestBody SandiDto sandi){
        HashMap<String, Object> response= new HashMap<>();
        try {
            Employee a = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (bCryptPasswordEncoder.matches(sandi.getOldSandi(), a.getPassword())){
                a.setPassword(sandi.getNewSandi());
                employeeService.changePassword(a);
                response.put("message","sukses mengganti password");
                response.put("success",true);
                return response;
            }else {
                response.put("message","sandi salah");
                response.put("success",false);
                return response;
            }
        }catch (Exception e){
            response.put("message ",e.getMessage());
            response.put("success",false);
            return response;

        }
    }
}
