package com.example.travelleronline.service;

import com.example.travelleronline.model.exceptions.BadEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public String sendEmail(String reciever,String title,String content){
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("ThisEmailIsGenerated@gmail.com");
            message.setTo(reciever);

            message.setText(content);
            message.setSubject(title);

            mailSender.send(message);
            return "Mail send successfully";
        }catch (MailException e){
            throw new BadEmailException("Mail not send. "+e.getMessage()+" (https://http.cat/420)");
        }
    }
}
