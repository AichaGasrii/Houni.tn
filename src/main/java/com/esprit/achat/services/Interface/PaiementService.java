package com.esprit.achat.services.Interface;

import com.esprit.achat.persistence.entity.ItemCommande;
import com.esprit.achat.persistence.entity.Paiement;
import com.esprit.achat.persistence.entity.Question;

public interface PaiementService extends CrudService<Paiement, Integer> {

    String obtenirDevisePourPaiement(Paiement paiement);

    void affecterDeviseAuxPaiements();
}

