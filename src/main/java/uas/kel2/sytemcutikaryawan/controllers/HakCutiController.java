package uas.kel2.sytemcutikaryawan.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uas.kel2.sytemcutikaryawan.dto.HakCutiDto;
import uas.kel2.sytemcutikaryawan.dto.ResponseData;
import uas.kel2.sytemcutikaryawan.models.HakCuti;
import uas.kel2.sytemcutikaryawan.service.HakCutiService;

@RestController
@RequestMapping("/api/hakCuti")
public class HakCutiController {

    @Autowired
    private HakCutiService hakCutiService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/findAll")
    public Iterable<HakCuti> findAll(){ return hakCutiService.findALl();
    }

    @PostMapping("/insertHakCuti")
    public ResponseEntity<ResponseData<HakCuti>> create(@RequestBody HakCutiDto hakCutiDto){
        ResponseData<HakCuti> responseData = new ResponseData<>();

        HakCuti hakCuti = modelMapper.map(hakCutiDto, HakCuti.class);

        responseData.setStatus(true);
        hakCutiService.save(hakCuti);
//        responseData.setPayLoad();
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/updateHakCuti")
    public ResponseEntity<ResponseData<HakCuti>> update(@RequestBody HakCutiDto hakCutiDto){
        ResponseData<HakCuti> responseData = new ResponseData<>();

        HakCuti hakCuti = modelMapper.map(hakCutiDto, HakCuti.class);

        responseData.setStatus(true);
        hakCutiService.save(hakCuti);
//        responseData.setPayLoad();
        return ResponseEntity.ok(responseData);
    }
}
