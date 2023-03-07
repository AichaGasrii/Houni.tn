package com.esprit.achat.services.Interface;

import com.esprit.achat.persistence.entity.ItemCommande;
import com.esprit.achat.persistence.entity.Paiement;
import com.esprit.achat.persistence.entity.Question;
import com.esprit.achat.persistence.enumeration.Methode;

public interface PaiementService extends CrudService<Paiement, Integer> {

    String obtenirDevisePourPaiement(Paiement paiement);

    void affecterDeviseAuxPaiements();

    Integer nbPaiementParMethode(Methode methode);
}

