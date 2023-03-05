package com.esprit.achat.services.Interface;

import com.esprit.achat.persistence.entity.Facture;
import com.esprit.achat.persistence.entity.Question;
import org.springframework.scheduling.annotation.Scheduled;

import javax.transaction.Transactional;

public interface FactureService extends CrudService<Facture, Integer> {

    Double  calculermontantTTC(Facture facture);
}

