package com.albendiego.OfficeManagement.service.impl;

import com.albendiego.OfficeManagement.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Async
public class EmailServiceImpl implements EmailService {
    JavaMailSender eMailSender;

    public void sendMessage(String[] to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("notificare.albendiego@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        eMailSender.send(message);

    }

}
