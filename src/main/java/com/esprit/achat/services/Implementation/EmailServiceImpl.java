package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.User;
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
    @Autowired
    private VerificationTokenService verificationTokenService;

    @Override
    public void sendCodeByMail(UserMail mail) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("farouk.douiri10@gmail.com");
        simpleMailMessage.setTo(mail.getTo());
        simpleMailMessage.setSubject("Code Active");
        simpleMailMessage.setText(mail.getCode());
        javaMailSender.send(simpleMailMessage);
    }

    public void sendVerificationEmail(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getUserEmail());
        user.setVerificationToken(verificationTokenService.generateVerificationToken());
        message.setSubject("Vérification du compte");
        message.setText("Bonjour " + user.getUserFirstName() + ",\n\n" +
                "Veuillez cliquer sur le lien ci-dessous pour activer votre compte :\n\n" +
                "http://localhost:9090/activate?token=" + user.getVerificationToken());
        javaMailSender.send(message);
    }


    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

}
