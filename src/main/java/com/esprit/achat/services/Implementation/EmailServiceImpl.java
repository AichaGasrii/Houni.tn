package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.UserMail;
import com.esprit.achat.repositories.IUserEmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;



    @Service
    public class EmailServiceImpl implements IUserEmailRepository {
        @Autowired
        private JavaMailSender javaMailSender;

        @Override
        public void sendCodeByMail(UserMail mail) {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("farouk.douiri10@gmail.com");
            simpleMailMessage.setTo(mail.getTo());
            simpleMailMessage.setSubject("Code Active");
            simpleMailMessage.setText(mail.getCode());
            javaMailSender.send(simpleMailMessage);
        }

    }
