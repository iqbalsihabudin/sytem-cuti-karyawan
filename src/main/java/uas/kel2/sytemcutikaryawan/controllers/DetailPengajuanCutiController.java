package uas.kel2.sytemcutikaryawan.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uas.kel2.sytemcutikaryawan.dto.DetailPengajuanCutiDto;
import uas.kel2.sytemcutikaryawan.dto.ResponseData;
import uas.kel2.sytemcutikaryawan.models.DetailPengajuanCuti;
import uas.kel2.sytemcutikaryawan.service.DetailPengajuanCutiService;

@RestController
@RequestMapping("/api/detailPengajuanCuti")
public class DetailPengajuanCutiController {


    @Autowired
    private DetailPengajuanCutiService detailPengajuanCutiService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/findAll")
    public Iterable<DetailPengajuanCuti> findAll(){ return detailPengajuanCutiService.findALl();
    }

    @PostMapping("/insertDetailPengajuanCuti")
    public ResponseEntity<ResponseData<DetailPengajuanCuti>> create(@RequestBody DetailPengajuanCutiDto detailPengajuanCutiDto){
        ResponseData<DetailPengajuanCuti> responseData = new ResponseData<>();

        DetailPengajuanCuti detailPengajuanCuti = modelMapper.map(detailPengajuanCutiDto, DetailPengajuanCuti.class);

        responseData.setStatus(true);
        detailPengajuanCutiService.save(detailPengajuanCuti);
//        responseData.setPayLoad();
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/updateDetailPengajuanCuti")
    public ResponseEntity<ResponseData<DetailPengajuanCuti>> update(@RequestBody DetailPengajuanCutiDto detailPengajuanCutiDto){
        ResponseData<DetailPengajuanCuti> responseData = new ResponseData<>();

        DetailPengajuanCuti detailPengajuanCuti = modelMapper.map(detailPengajuanCutiDto, DetailPengajuanCuti.class);

        responseData.setStatus(true);
        detailPengajuanCutiService.save(detailPengajuanCuti);
//        responseData.setPayLoad();
        return ResponseEntity.ok(responseData);
    }
}
