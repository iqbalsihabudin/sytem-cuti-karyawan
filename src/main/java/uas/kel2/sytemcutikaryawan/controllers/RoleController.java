package uas.kel2.sytemcutikaryawan.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uas.kel2.sytemcutikaryawan.dto.ResponseData;
import uas.kel2.sytemcutikaryawan.dto.RoleDto;
import uas.kel2.sytemcutikaryawan.models.Role;
import uas.kel2.sytemcutikaryawan.service.RoleService;


@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/findAll")
    public Iterable<Role> findAll(@RequestParam(value = "isDeleted", required = false, defaultValue = "false") boolean isDeleted){
        return roleService.findALl(isDeleted);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Integer id){
        roleService.remove(id);
    }

    @PostMapping("/insertRole")
    public ResponseEntity<ResponseData<Role>> create(@RequestBody RoleDto roleDto){
        ResponseData<Role> responseData = new ResponseData<>();

        Role role = modelMapper.map(roleDto, Role.class);

        responseData.setStatus(true);
        responseData.getMessages().add("insert sukses");
        responseData.setPayLoad(roleService.save(role));
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/updateRole")
    public ResponseEntity<ResponseData<Role>> update(@RequestBody RoleDto roleDto){
        ResponseData<Role> responseData = new ResponseData<>();

        Role role = modelMapper.map(roleDto, Role.class);

        responseData.setStatus(true);
        responseData.getMessages().add("update sukses");
        responseData.setPayLoad(roleService.save(role));
        return ResponseEntity.ok(responseData);
    }
}
