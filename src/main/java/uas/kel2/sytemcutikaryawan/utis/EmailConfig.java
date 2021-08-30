package uas.kel2.sytemcutikaryawan.utis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

public class EmailConfig {

    @Value("@{spring.mail.host}")
    String mailHost;
    @Value("@{spring.mail.port}")
    Integer mailPort;
    @Value("@{spring.mail.username}")
    String username;
    @Value("@{spring.mail.password}")
    String password;
    @Value("@{spring.mail.properties.mail.smtp.auth}")
    String mailSmtpAuth;
    @Value("@{spring.mail.properties.mail.smtp.starttls.enable}")
    String mailStartTls;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailHost);
        mailSender.setPort(mailPort);

        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", mailSmtpAuth);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", mailStartTls);
        props.put("mail.debug", "true");

        return mailSender;
    }
}
