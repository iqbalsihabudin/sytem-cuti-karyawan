package uas.kel2.sytemcutikaryawan.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import uas.kel2.sytemcutikaryawan.dto.HakCutiDto;
import uas.kel2.sytemcutikaryawan.dto.PengajuanCutiDto;
import uas.kel2.sytemcutikaryawan.dto.ResponseData;
import uas.kel2.sytemcutikaryawan.models.Employee;
import uas.kel2.sytemcutikaryawan.models.HakCuti;
import uas.kel2.sytemcutikaryawan.models.Libur;
import uas.kel2.sytemcutikaryawan.models.PengajuanCuti;
import uas.kel2.sytemcutikaryawan.service.EmailService;
import uas.kel2.sytemcutikaryawan.service.HakCutiService;
import uas.kel2.sytemcutikaryawan.service.PengajuanCutiService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/pengajuanCuti")
public class PengajuanCutiController {
    @Autowired
    private PengajuanCutiService pengajuanCutiService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    EmailService emailService;

    @GetMapping("/findAll")
    public Iterable<PengajuanCuti> findAll(@RequestParam(value = "isDeleted", required = false, defaultValue = "false") boolean isDeleted){
        return pengajuanCutiService.findALl(isDeleted);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Integer id){
        pengajuanCutiService.remove(id);
    }

    @PutMapping("/acc/{id}")
    public Map<String, Object> statusAcc(@PathVariable("id") Integer id){
        HashMap<String, Object> response= new HashMap<>();
        try {
            pengajuanCutiService.updateStatusAcc(id);
            Employee user = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            PengajuanCuti pengajuanCuti = pengajuanCutiService.findById(id);
            String[] email = new String[1];
            email[0] = pengajuanCuti.getEmployee().getEmail();
            String text = "pengajuanmu telah di acc oleh hrd " +user.getNamaLengkap() +" ";
            emailService.sendEmail(user.getEmail(), email,"acc", text);
            response.put("message","Approved pengajuan berhasil");
            response.put("success",true);
            return response;
        }catch (Exception e){
            response.put("message ",e.getMessage());
            response.put("success",false);
            return response;
        }
    }



    @PutMapping("/reject/{id}")
    public Map<String, Object> statusReject(@PathVariable("id") Integer id){
        HashMap<String, Object> response= new HashMap<>();
        try {
            pengajuanCutiService.updateStatusReject(id);
            Employee user = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            PengajuanCuti pengajuanCuti = pengajuanCutiService.findById(id);
            String[] email = new String[1];
            email[0] = pengajuanCuti.getEmployee().getEmail();
            String text = "pengajuanmu di tolak oleh hrd " +user.getNamaLengkap() +" ";
            emailService.sendEmail(user.getEmail(), email,"tolak", text);
            response.put("message","Pengajuan reject");
            response.put("success",true);
            return response;
        }catch (Exception e){
            response.put("message ",e.getMessage());
            response.put("success",false);
            return response;
        }
    }

    @PostMapping("/insertPengajuanCuti")
    public ResponseEntity<ResponseData<PengajuanCuti>> create(@RequestBody PengajuanCutiDto pengajuanCutiDto){
        ResponseData<PengajuanCuti> responseData = new ResponseData<>();

        PengajuanCuti pengajuanCuti = modelMapper.map(pengajuanCutiDto, PengajuanCuti.class);

        responseData.setStatus(true);
        responseData.getMessages().add("insert sukses");
        responseData.setPayLoad(pengajuanCutiService.save(pengajuanCuti));
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/updatePengajuanCuti")
    public ResponseEntity<ResponseData<PengajuanCuti>> update(@RequestBody PengajuanCutiDto pengajuanCutiDto){
        ResponseData<PengajuanCuti> responseData = new ResponseData<>();
        PengajuanCuti tamp = pengajuanCutiService.findById(pengajuanCutiDto.getPengajuanCutiId());
        PengajuanCuti pengajuanCuti = modelMapper.map(pengajuanCutiDto, PengajuanCuti.class);
        pengajuanCuti.setCreatedBy(tamp.getCreatedBy());
        pengajuanCuti.setCreatedDate(tamp.getCreatedDate());
        responseData.setStatus(true);
        responseData.getMessages().add("update sukses");
        responseData.setPayLoad(pengajuanCutiService.save(pengajuanCuti));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/countOpenpc")
    public Integer countOpenpc(){
        return pengajuanCutiService.PengajuanCutiCountOpen();
    }

    @GetMapping("/countAllpc")
    public Integer countAllpc(){
        return pengajuanCutiService.PengajuanCutiCountAll();
    }
}
