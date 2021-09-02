package uas.kel2.sytemcutikaryawan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class EmailService {

    @Autowired
    JavaMailSender emailSender;

    public void sendEmail(String from, String[] to, String subject, String text) throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(new InternetAddress("kelompokjava2@gmail.com",""+from+""));
        helper.setTo(to);
        helper.setSubject(subject);;
        helper.setText(text);
        emailSender.send(message);
    }
}
