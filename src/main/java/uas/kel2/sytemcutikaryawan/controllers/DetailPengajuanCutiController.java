package uas.kel2.sytemcutikaryawan.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import uas.kel2.sytemcutikaryawan.dto.DetailPengajuanCutiDto;
import uas.kel2.sytemcutikaryawan.dto.ResponseData;
import uas.kel2.sytemcutikaryawan.models.DetailPengajuanCuti;
import uas.kel2.sytemcutikaryawan.models.Employee;
import uas.kel2.sytemcutikaryawan.models.PengajuanCuti;
import uas.kel2.sytemcutikaryawan.service.DetailPengajuanCutiService;
import uas.kel2.sytemcutikaryawan.service.EmailService;
import uas.kel2.sytemcutikaryawan.service.PengajuanCutiService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/detailPengajuanCuti")
public class DetailPengajuanCutiController {


    @Autowired
    private DetailPengajuanCutiService detailPengajuanCutiService;

    @Autowired
    private PengajuanCutiService pengajuanCutiService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    EmailService emailService;

    @GetMapping("/findAll")
    public Iterable<DetailPengajuanCuti> findAll(@RequestParam(value = "isDeleted", required = false, defaultValue = "false") boolean isDeleted){
        return detailPengajuanCutiService.findALl(isDeleted);
    }

    @PostMapping("/insertDetailPengajuanCuti")
    public ResponseEntity<ResponseData<DetailPengajuanCuti>> create(@RequestBody DetailPengajuanCutiDto detailPengajuanCutiDto){
        ResponseData<DetailPengajuanCuti> responseData = new ResponseData<>();

        DetailPengajuanCuti detailPengajuanCuti = modelMapper.map(detailPengajuanCutiDto, DetailPengajuanCuti.class);

        responseData.setStatus(true);
        responseData.setPayLoad(detailPengajuanCutiService.save(detailPengajuanCuti));
        responseData.getMessages().add("insert sukses");
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/acc/{id}")
    public Map<String, Object> statusAcc(@PathVariable("id") Integer id){
        HashMap<String, Object> response= new HashMap<>();
        try {
            Integer idPeng = detailPengajuanCutiService.findById(id).getPengajuanCuti().getPengajuanCutiId();
            pengajuanCutiService.updateStatusAcc(idPeng);
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
            Integer idPeng = detailPengajuanCutiService.findById(id).getPengajuanCuti().getPengajuanCutiId();
            pengajuanCutiService.updateStatusReject(idPeng);
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

    @PutMapping("/updateDetailPengajuanCuti")
    public ResponseEntity<ResponseData<DetailPengajuanCuti>> update(@RequestBody DetailPengajuanCutiDto detailPengajuanCutiDto){
        ResponseData<DetailPengajuanCuti> responseData = new ResponseData<>();
        DetailPengajuanCuti tamp = detailPengajuanCutiService.findById(detailPengajuanCutiDto.getDetailPengajuanCutiId());
        DetailPengajuanCuti detailPengajuanCuti = modelMapper.map(detailPengajuanCutiDto, DetailPengajuanCuti.class);
        detailPengajuanCuti.setCreatedBy(tamp.getCreatedBy());
        detailPengajuanCuti.setCreatedDate(tamp.getCreatedDate());
        responseData.setStatus(true);
        detailPengajuanCutiService.save(detailPengajuanCuti);
        responseData.getMessages().add("update sukses");
        return ResponseEntity.ok(responseData);
    }
    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Integer id){
        detailPengajuanCutiService.remove(id);
    }

}
