package uas.kel2.sytemcutikaryawan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uas.kel2.sytemcutikaryawan.dto.EmailPayLoad;
import uas.kel2.sytemcutikaryawan.service.EmailService;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    EmailService emailService;

    @GetMapping("/send")
    public Object send(@RequestBody EmailPayLoad dto){
        try {
            String[] email = new String[1];
            email[0] = dto.getTo();
            emailService.sendEmail(dto.getFrom(), email,"acc", "test");
        }catch (Exception e){

        }
        return "Send Email Success";
    }
}
