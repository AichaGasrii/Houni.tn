package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.AppelOffre;
import com.esprit.achat.persistence.entity.DemandeAchat;
import com.esprit.achat.persistence.enumeration.OffreType;
import com.esprit.achat.repositories.DemandeAchatReporitory;
import com.esprit.achat.services.Interface.DemandeAchatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class DemandeAchatServiceIMP extends CrudServiceIMP<DemandeAchat,Integer> implements DemandeAchatService {
    @Autowired
    DemandeAchatReporitory demandeAchatReporitory;


    public void enregistrerAppelOffre(int idDemandeAchat) {
        DemandeAchat demandeAchat = demandeAchatReporitory.findById(idDemandeAchat)
                .orElseThrow(() -> new IllegalArgumentException("Demande d'achat inexistante avec l'ID : " + idDemandeAchat));
        List<AppelOffre> nbAppelsOffres = demandeAchat.getAppelOffres();
      // demandeAchat.setAppelOffres(nbAppelsOffres + 1);
        demandeAchatReporitory.save(demandeAchat);

    }

    @Override
   // @Scheduled(cron = "*/30 * * * * *")
    public void nbreAchatParType() {
        log.info("--- Nombre des demandes d'achat PRODUIT :" + demandeAchatReporitory.nbreAchatParType(OffreType.PRODUIT));
        log.info("--- Nombre des demandes d'achat SERVICE : " + demandeAchatReporitory.nbreAchatParType(OffreType.SERVICE));
    }



}
