package com.esprit.achat.Configuration;

import com.esprit.achat.persistence.entity.Commande;
import com.esprit.achat.persistence.enumeration.Etat;
import com.esprit.achat.repositories.CodePromoRepository;
import com.esprit.achat.repositories.CommandeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class ScheduleBean  {

    @Autowired
    CodePromoRepository codePromoRepository;
    @Autowired
    CommandeRepository commandeRepository;

  //  @Scheduled(cron = "*/30 * * * * *")
    public void scheduleCommandeStatusUpdate() {
        log.info("scheduleCommandeStatusUpdate() method called");
        try {

            List<Commande> commandes = commandeRepository.findByEtat(Etat.ENCOURS);
            LocalDate today = LocalDate.now();
            for (Commande commande : commandes) {
                // Vérifie si la commande a été créée il y a plus de 1 minute
                //if (ChronoUnit.MINUTES.between(commande.getDateCreation(), today.atStartOfDay()) >= 24) {
                    // Change l'état de la commande en "validé"
                    commande.setEtat(Etat.VALIDE);
                    commandeRepository.save(commande);
                    System.out.println("Commande " + commande.getId() + " mise à jour : " + commande.getEtat());
                //}
            }

        } catch (Exception e) {
            System.out.println("Une erreur s'est produite lors de l'exécution de la tâche : " + e.getMessage());
        }
    }

   // @Scheduled(cron = "*/30 * * * * *")
    public void retrieveAndUpdateStatusCommande() {

        commandeRepository.findByArchiveFalseAndDateCreation(LocalDate.now())
                .stream()
                .forEach(commande -> commande.setArchive(true));

        commandeRepository.findByArchiveFalse().stream()
                .filter(commande -> ChronoUnit.DAYS.between(LocalDate.now(),commande.getDateCreation()) < 30 )
                .forEach(commande ->
                        log.info(
                                "Commande num: " +commande.getId() +
                                        " de le client " + commande.getClient()+
                                        " expirera aprés 15 jour de cette date " + commande.getDateCreation() +
                                        " / "+ ChronoUnit.DAYS.between(LocalDate.now(),commande.getDateCreation())
                        )
                );
    }
}