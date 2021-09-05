package uas.kel2.sytemcutikaryawan.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import uas.kel2.sytemcutikaryawan.dto.HakCutiDto;
import uas.kel2.sytemcutikaryawan.dto.PengajuanCutiDto;
import uas.kel2.sytemcutikaryawan.dto.ResponseData;


import uas.kel2.sytemcutikaryawan.models.*;
import uas.kel2.sytemcutikaryawan.service.*;


import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DetailPengajuanCutiService detailPengajuanCutiService;


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

    @PostMapping("/insertPengajuanCuti")
    public ResponseEntity<ResponseData<PengajuanCuti>> create(@RequestBody PengajuanCutiDto pengajuanCutiDto) throws MessagingException, UnsupportedEncodingException {
        ResponseData<PengajuanCuti> responseData = new ResponseData<>();
//        Employee user = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        List<String> emailList = employeeService.emailHRD();
//        String[] emailArr = emailList.toArray(new String[emailList.size()]);
//        String text = "ada pengajuan cuti dari " +user.getNamaLengkap() +" ";
//        emailService.sendEmail(user.getEmail(), emailArr,"pengajuan", text);
        PengajuanCuti pengajuanCuti = modelMapper.map(pengajuanCutiDto, PengajuanCuti.class);
        responseData.setPayLoad(pengajuanCutiService.save(pengajuanCuti));
        //====insert detail pengajuan
        DetailPengajuanCuti detailPengajuanCuti = new DetailPengajuanCuti();
        detailPengajuanCuti.setPengajuanCuti(pengajuanCuti);
        detailPengajuanCuti.setJenisCuti(pengajuanCutiDto.getJenisCuti());
        detailPengajuanCuti.setTglCuti(pengajuanCutiDto.getDate());
        detailPengajuanCutiService.save(detailPengajuanCuti);
        responseData.setStatus(true);
        responseData.getMessages().add("insert sukses");
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/email")
    public List<String> email(){
        return employeeService.emailHRD();
    }

    @PostMapping("/updatePengajuanCuti")
    public ResponseEntity<ResponseData<PengajuanCuti>> update(@RequestBody PengajuanCutiDto pengajuanCutiDto) throws MessagingException, UnsupportedEncodingException {
        ResponseData<PengajuanCuti> responseData = new ResponseData<>();
        Employee user = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<String> emailList = employeeService.emailHRD();
        String[] emailArr = emailList.toArray(new String[emailList.size()]);
        PengajuanCuti pengajuanCuti = modelMapper.map(pengajuanCutiDto, PengajuanCuti.class);
        PengajuanCuti tamp = pengajuanCutiService.findById(pengajuanCuti.getPengajuanCutiId());
        pengajuanCuti.setCreatedBy(tamp.getCreatedBy());
        pengajuanCuti.setCreatedDate(tamp.getCreatedDate());
        responseData.setPayLoad(pengajuanCutiService.save(pengajuanCuti));
        String text = user.getNamaLengkap() +" - Pengajuan Cuti\n" +
                " \n" +
                "Kepada HRD UAS JAVA yang terhormat \n"+
                "Dikarenakan ada urusan lain yang harus saya selesaikan, \n" +
                "maka dari itu saya mengajukan cuti dengan rincian sebagai berikut: \n"+
                "Lama cuti : "+pengajuanCuti.getLamaCuti() + "\n" +
                " \n" +
                "Namun apabila saya harus tetap masuk kerja pada hari-hari itu, \n" +
                "dan anda mengusulkan cuti pada hari lain, saya tidak masalah. \n" +
                " \n" +
                "Terimakasih atas pertimbangan dan pengertian anda. \n"+
                " \n" +
                "Hormat saya, \n" +
                " \n" +
                user.getNamaLengkap();
        emailService.sendEmail(user.getEmail(), emailArr,"pengajuan", text);
        //====insert detail pengajuan
        DetailPengajuanCuti detailPengajuanCuti = detailPengajuanCutiService.findById(pengajuanCutiDto.getDetId());
        detailPengajuanCuti.setPengajuanCuti(pengajuanCuti);
        detailPengajuanCuti.setJenisCuti(pengajuanCutiDto.getJenisCuti());
        detailPengajuanCuti.setTglCuti(pengajuanCutiDto.getDate());
        detailPengajuanCutiService.save(detailPengajuanCuti);

        responseData.setStatus(true);
        responseData.getMessages().add("insert sukses");
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

    @PutMapping("/updatePeng")
    public ResponseEntity<ResponseData<PengajuanCuti>> updatePeng(@RequestBody PengajuanCutiDto pengajuanCutiDto) throws MessagingException, UnsupportedEncodingException {
        ResponseData<PengajuanCuti> responseData = new ResponseData<>();
        System.out.println(pengajuanCutiDto.getPengajuanCutiId());
        PengajuanCuti pengajuanCuti = modelMapper.map(pengajuanCutiDto, PengajuanCuti.class);
        PengajuanCuti tamp = pengajuanCutiService.findById(pengajuanCuti.getPengajuanCutiId());
        pengajuanCuti.setCreatedBy(tamp.getCreatedBy());
        pengajuanCuti.setCreatedDate(tamp.getCreatedDate());
        responseData.setPayLoad(pengajuanCutiService.update(pengajuanCuti));
        DetailPengajuanCuti detailPengajuanCuti = detailPengajuanCutiService.findById(pengajuanCutiDto.getDetId());
        detailPengajuanCuti.setPengajuanCuti(pengajuanCuti);
        detailPengajuanCuti.setJenisCuti(pengajuanCutiDto.getJenisCuti());
        detailPengajuanCuti.setTglCuti(pengajuanCutiDto.getDate());
        detailPengajuanCutiService.save(detailPengajuanCuti);
        responseData.setStatus(true);
        responseData.getMessages().add("insert sukses");
        return ResponseEntity.ok(responseData);
    }
}
