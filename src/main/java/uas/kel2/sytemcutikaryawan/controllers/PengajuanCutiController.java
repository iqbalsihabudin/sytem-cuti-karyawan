package uas.kel2.sytemcutikaryawan.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uas.kel2.sytemcutikaryawan.dto.HakCutiDto;
import uas.kel2.sytemcutikaryawan.dto.PengajuanCutiDto;
import uas.kel2.sytemcutikaryawan.dto.ResponseData;
import uas.kel2.sytemcutikaryawan.models.HakCuti;
import uas.kel2.sytemcutikaryawan.models.Libur;
import uas.kel2.sytemcutikaryawan.models.PengajuanCuti;
import uas.kel2.sytemcutikaryawan.service.HakCutiService;
import uas.kel2.sytemcutikaryawan.service.PengajuanCutiService;

@RestController
@RequestMapping("/api/pengajuanCuti")
public class PengajuanCutiController {
    @Autowired
    private PengajuanCutiService pengajuanCutiService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/findAll")
    public Iterable<PengajuanCuti> findAll(@RequestParam(value = "isDeleted", required = false, defaultValue = "false") boolean isDeleted){
        return pengajuanCutiService.findALl(isDeleted);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Integer id){
        pengajuanCutiService.remove(id);
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

        PengajuanCuti pengajuanCuti = modelMapper.map(pengajuanCutiDto, PengajuanCuti.class);

        responseData.setStatus(true);
        responseData.getMessages().add("update sukses");
        responseData.setPayLoad(pengajuanCutiService.save(pengajuanCuti));
        return ResponseEntity.ok(responseData);
    }
}
