package uas.kel2.sytemcutikaryawan.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uas.kel2.sytemcutikaryawan.dto.JenisCutiDto;
import uas.kel2.sytemcutikaryawan.dto.ResponseData;
import uas.kel2.sytemcutikaryawan.dto.StatusCutiDto;
import uas.kel2.sytemcutikaryawan.models.JenisCuti;
import uas.kel2.sytemcutikaryawan.models.Libur;
import uas.kel2.sytemcutikaryawan.models.StatusCuti;
import uas.kel2.sytemcutikaryawan.service.StatusCutiService;

@RestController
@RequestMapping("/api/statuscuti")
public class StatusCutiController {
    @Autowired
    private StatusCutiService statusCutiService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/findAll")
    public Iterable<StatusCuti> findAll(@RequestParam(value = "isDeleted", required = false, defaultValue = "false") boolean isDeleted){
        return statusCutiService.findALl(isDeleted);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Integer id){
        statusCutiService.remove(id);
    }

    @PostMapping("insertStatusCuti")
    public ResponseEntity<ResponseData<StatusCuti>> create(@RequestBody StatusCutiDto statusCutiDto){
        ResponseData<StatusCuti> responseData = new ResponseData<>();

        StatusCuti statusCuti = modelMapper.map(statusCutiDto, StatusCuti.class);

        responseData.setStatus(true);
        responseData.getMessages().add("insert sukses");
        responseData.setPayLoad(statusCutiService.save(statusCuti));
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("updateStatusCuti")
    public ResponseEntity<ResponseData<StatusCuti>> update(@RequestBody StatusCutiDto statusCutiDto){
        ResponseData<StatusCuti> responseData = new ResponseData<>();
        StatusCuti tamp = statusCutiService.findById(statusCutiDto.getStatusCutiId());
        StatusCuti statusCuti = modelMapper.map(statusCutiDto, StatusCuti.class);
        statusCuti.setCreatedBy(tamp.getCreatedBy());
        statusCuti.setCreatedDate(tamp.getCreatedDate());
        responseData.setStatus(true);
        responseData.getMessages().add("update sukses");
        responseData.setPayLoad(statusCutiService.save(statusCuti));
        return ResponseEntity.ok(responseData);
    }


}
