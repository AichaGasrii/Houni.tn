package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.Commande;
import com.esprit.achat.persistence.entity.Facture;
import com.esprit.achat.persistence.entity.NatureArticle;
import com.esprit.achat.repositories.FactureRepository;
import com.esprit.achat.services.Interface.FactureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Slf4j
public class FactureServiceIMP  extends CrudServiceIMP<Facture,Integer> implements FactureService {
    @Autowired
    FactureRepository factureRepository;

    @Scheduled(cron = "*/30 * * * * *")
    public void retrieveAndUpdateStatusFacture() {

        // Archive all expired commande
        this.archiveExpiredFacture();

        factureRepository.findByArchiveFalse().stream()
                .filter(facture -> ChronoUnit.DAYS.between(LocalDate.now(),facture.getDatefacture()) < 30 )
                .forEach(facture ->
                        log.info(
                                "Facture num: " +facture.getId() +
                                        " de le client " + facture.getClient()+
                                        " expirera aprés 15 jour de cette date " + facture.getDatefacture() +
                                        " / "+ ChronoUnit.DAYS.between(LocalDate.now(),facture.getDatefacture())
                        )
                );
    }

    @Transactional
    @Override
    public void archiveExpiredFacture() {
        factureRepository.findByArchiveFalseAndDatefacture(LocalDate.now())
                .stream()
                .forEach(facture -> facture.setArchive(true));
    }

}
