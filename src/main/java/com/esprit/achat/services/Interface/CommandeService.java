package com.esprit.achat.services.Interface;

import com.esprit.achat.persistence.dto.MontantPanier;
import com.esprit.achat.persistence.dto.Panier;
import com.esprit.achat.persistence.entity.Commande;
import com.esprit.achat.persistence.entity.Question;

public interface CommandeService extends CrudService<Commande, Integer> {
    MontantPanier calculMontantPanier(Panier panier);
}

