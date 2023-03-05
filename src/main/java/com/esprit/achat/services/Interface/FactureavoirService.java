package com.esprit.achat.services.Interface;

import com.esprit.achat.persistence.entity.FactureAvoir;
import com.esprit.achat.persistence.entity.Question;
import org.springframework.scheduling.annotation.Scheduled;

import javax.transaction.Transactional;

public interface FactureavoirService extends CrudService<FactureAvoir, Integer> {

    Double  calculermontantTTC(FactureAvoir factureAvoir);
}

