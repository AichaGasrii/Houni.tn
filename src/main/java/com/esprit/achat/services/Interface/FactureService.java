package com.esprit.achat.services.Interface;

import com.esprit.achat.persistence.entity.Facture;
import com.esprit.achat.persistence.entity.ItemFacture;
import com.esprit.achat.persistence.entity.Question;
import com.mysql.cj.xdevapi.Client;
import org.springframework.scheduling.annotation.Scheduled;

import javax.transaction.Transactional;
import java.util.List;

public interface FactureService extends CrudService<Facture, Integer> {

    Double  calculermontantTTC(Facture facture);


    List<ItemFacture> listeDesItemParFacture(Integer factureId);

    String obtenirDevisePourFacture(Facture facture);

    void affecterDeviseAuxFactures();
}

