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
import uas.kel2.sytemcutikaryawan.models.JenisCuti;
import uas.kel2.sytemcutikaryawan.models.PengajuanCuti;
import uas.kel2.sytemcutikaryawan.service.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

    @Autowired
    private HakCutiService hakCutiService;

    private final PDFGeneratorService pdfGeneratorService;

    public DetailPengajuanCutiController(PDFGeneratorService pdfGeneratorService){
        this.pdfGeneratorService = pdfGeneratorService;
    }

        @GetMapping("/pdf/generate/{id}")
    public void generatePDF(@PathVariable("id") Integer id,HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        DetailPengajuanCuti detailPengajuanCuti = detailPengajuanCutiService.findById(id);
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=detail_Pengajuan_"+ currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        this.pdfGeneratorService.export(response,detailPengajuanCuti);
    }

    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=PengajuanCuti_"+ currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        Iterable<DetailPengajuanCuti> listData = detailPengajuanCutiService.findALl(false);

        ExcelExporterService excelExporter = new ExcelExporterService(listData);

        excelExporter.export(response);
    }

    @GetMapping("/findAllByLimit")
    public  Iterable<DetailPengajuanCuti> findAllByLimit(@RequestParam(value = "start",defaultValue = "0") int start, @RequestParam(value = "limit",defaultValue = "10") int limit){
        return detailPengajuanCutiService.findAllByLimit(start,limit);
    }

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
            Integer idJen = detailPengajuanCutiService.findById(id).getJenisCuti().getJenisCutiId();
            Integer idEmp = detailPengajuanCutiService.findById(id).getPengajuanCuti().getEmployee().getEmployee_id();
            Employee user = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            pengajuanCutiService.updateStatusAcc(idPeng, user.getEmployee_id());
            hakCutiService.hakCutiMinus(idJen, idEmp);
            PengajuanCuti pengajuanCuti = pengajuanCutiService.findById(id);
            String[] email = new String[1];
            email[0] = pengajuanCuti.getEmployee().getEmail();
            String text = user.getNamaLengkap() +" - Pengajuan Cuti Disetujui\n" +
                    "Pengajuan cuti dengan rincian sebagai berikut: \n"+
                    "Nama karyawan : "+user.getNamaLengkap() + "\n" +
                    "Lama cuti     : "+pengajuanCuti.getLamaCuti() + "\n" +
                    "Setelah dipertimbangkan dengan melihat situasi dan kondisi, \n"+
                    "maka kami menyetujui permohonan pengajuan cuti anda.\n"+
                    " \n" +
                    "Salam, \n" +
                    " \n" +
                    "HRD UAS Java \n" ;
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
            Employee user = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            pengajuanCutiService.updateStatusReject(idPeng, user.getEmployee_id());
            PengajuanCuti pengajuanCuti = pengajuanCutiService.findById(id);
            String[] email = new String[1];
            email[0] = pengajuanCuti.getEmployee().getEmail();
            String text = user.getNamaLengkap() +" - Pengajuan Cuti Ditolak\n" +
                    "Pengajuan cuti dengan rincian sebagai berikut: \n"+
                    "Nama karyawan : "+user.getNamaLengkap() + "\n" +
                    "Lama cuti     : "+pengajuanCuti.getLamaCuti() + "\n" +
                    "Setelah dipertimbangkan dengan melihat situasi dan kondisi, \n"+
                    "maka dengan berat hati kami menolak permohonan pengajuan cuti anda.\n"+
                    " \n" +
                    "Mohon maaf atas ketidaknyamanan ini. \n"+
                    " \n" +
                    "Salam, \n" +
                    " \n" +
                    "HRD UAS Java \n" ;
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

    @PutMapping("/cancel/{id}")
    public void pengajuanCancel(@PathVariable("id") Integer id){
        DetailPengajuanCuti dp = detailPengajuanCutiService.findById(id);
        detailPengajuanCutiService.pengajuanCancel(dp);

    }

}
