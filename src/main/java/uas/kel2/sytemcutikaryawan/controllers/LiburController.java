package uas.kel2.sytemcutikaryawan.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uas.kel2.sytemcutikaryawan.dto.LiburDto;
import uas.kel2.sytemcutikaryawan.dto.ResponseData;
import uas.kel2.sytemcutikaryawan.models.Libur;
import uas.kel2.sytemcutikaryawan.service.LiburService;

@RestController
@RequestMapping("/api/libur")
public class LiburController {

    @Autowired
    private LiburService liburService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/findAll")
    public Iterable<Libur> findAll(@RequestParam(value = "isDeleted", required = false, defaultValue = "false") boolean isDeleted){
        return liburService.findALl(isDeleted);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Integer id){
        liburService.remove(id);
    }

    @PostMapping("/insertLibur")
    public ResponseEntity<ResponseData<Libur>> create(@RequestBody LiburDto liburDto){
        ResponseData<Libur> responseData = new ResponseData<>();

        Libur libur = modelMapper.map(liburDto, Libur.class);

        responseData.setStatus(true);
        responseData.getMessages().add("insert sukses");
        responseData.setPayLoad(liburService.save(libur));
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/updateLibur")
    public ResponseEntity<ResponseData<Libur>> update(@RequestBody LiburDto liburDto){
        ResponseData<Libur> responseData = new ResponseData<>();

        Libur libur = modelMapper.map(liburDto, Libur.class);

        responseData.setStatus(true);
        responseData.getMessages().add("update sukses");
        responseData.setPayLoad(liburService.save(libur));
        return ResponseEntity.ok(responseData);
    }

}
