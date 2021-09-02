package uas.kel2.sytemcutikaryawan.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uas.kel2.sytemcutikaryawan.dto.HakCutiDto;
import uas.kel2.sytemcutikaryawan.dto.PengajuanCutiDto;
import uas.kel2.sytemcutikaryawan.dto.ResponseData;
import uas.kel2.sytemcutikaryawan.models.Employee;
import uas.kel2.sytemcutikaryawan.models.HakCuti;
import uas.kel2.sytemcutikaryawan.models.Libur;
import uas.kel2.sytemcutikaryawan.models.PengajuanCuti;
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

    @GetMapping("/pengajuanCutiById")
    public PengajuanCuti pengajuanCutiById(@RequestParam(value = "id",required = false,defaultValue = "false")int id){
        return pengajuanCutiService.pengajuanCutiById(id);
    }

    @GetMapping("/findAll")
    public Iterable<PengajuanCuti> findAll(@RequestParam(value = "isDeleted", required = false, defaultValue = "false") boolean isDeleted){
        return pengajuanCutiService.findALl(isDeleted);
    }

    @GetMapping("/findAllByLimit")
    public  Iterable<PengajuanCuti> findAllByLimit(@RequestParam(value = "start",defaultValue = "0") int start, @RequestParam(value = "limit",defaultValue = "10") int limit){
        return pengajuanCutiService.findAllByLimit(start,limit);
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
            response.put("message","Approved pengajuan berhasil");
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
