package com.esprit.achat.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class EmailControllers {
    @Autowired
    JavaMailSender mailSender;
    public String ApplicationMail( )
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("moslemhamdi2@gmail.com");
        message.setTo("moslem.hamdi@esprit.tn");
        message.setText("Mr/Mrs : Vous avez une nouvelle réclamation. Accédez à la plateforme pour la traiter.  ");
        message.setSubject("reclamation Mailing Bot");
        mailSender.send(message);


        return "Successfully sent";
    }
}
