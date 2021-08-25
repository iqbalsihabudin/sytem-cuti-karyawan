package uas.kel2.sytemcutikaryawan.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uas.kel2.sytemcutikaryawan.dto.JenisCutiDto;
import uas.kel2.sytemcutikaryawan.dto.ResponseData;
import uas.kel2.sytemcutikaryawan.models.JenisCuti;
import uas.kel2.sytemcutikaryawan.service.JenisCutiService;

@RestController
@RequestMapping("/api/jeniscuti")
public class JenisCutiController {
    @Autowired
    private JenisCutiService jenisCutiService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/findAll")
    public Iterable<JenisCuti> findAll(@RequestParam(value = "isDeleted", required = false, defaultValue = "false") boolean isDeleted){
        return jenisCutiService.findALl(isDeleted);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Integer id){
        jenisCutiService.remove(id);
    }

    @PostMapping("insertJenisCuti")
    public ResponseEntity<ResponseData<JenisCuti>> create(@RequestBody JenisCutiDto jenisCutiDto){
        ResponseData<JenisCuti> responseData = new ResponseData<>();

        JenisCuti jenisCuti = modelMapper.map(jenisCutiDto, JenisCuti.class);

        responseData.setStatus(true);
        responseData.getMessages().add("update sukses");
        responseData.setPayLoad(jenisCutiService.save(jenisCuti));
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("updateJenisCuti")
    public ResponseEntity<ResponseData<JenisCuti>> update(@RequestBody JenisCutiDto jenisCutiDto){
        ResponseData<JenisCuti> responseData = new ResponseData<>();
        JenisCuti tamp = jenisCutiService.findById(jenisCutiDto.getJenisCutiId());
        JenisCuti jenisCuti = modelMapper.map(jenisCutiDto, JenisCuti.class);
        jenisCuti.setCreatedBy(tamp.getCreatedBy());
        jenisCuti.setCreatedDate(tamp.getCreatedDate());
        responseData.setStatus(true);
        responseData.getMessages().add("insert sukses");
        responseData.setPayLoad(jenisCutiService.save(jenisCuti));
        return ResponseEntity.ok(responseData);
    }

}
