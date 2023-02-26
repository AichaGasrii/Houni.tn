package com.esprit.achat.services.Interface;

import com.esprit.achat.persistence.dto.MontantPanier;
import com.esprit.achat.persistence.dto.Panier;
import com.esprit.achat.persistence.entity.Commande;
import com.esprit.achat.persistence.entity.Question;
import com.esprit.achat.persistence.enumeration.Etat;

public interface CommandeService extends CrudService<Commande, Integer> {
    MontantPanier calculMontantPanier(Panier panier);


    Integer nbCommandeParEtat(Etat etat);
}

