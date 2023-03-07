package com.esprit.achat.services.Interface;

import com.esprit.achat.persistence.dto.MontantPanier;
import com.esprit.achat.persistence.dto.Panier;
import com.esprit.achat.persistence.entity.Commande;
import com.esprit.achat.persistence.entity.ItemCommande;
import com.esprit.achat.persistence.entity.Question;
import com.esprit.achat.persistence.enumeration.Etat;

import javax.transaction.Transactional;
import java.util.List;

public interface CommandeService extends CrudService<Commande, Integer> {

    Double  calculermontantTTC(Commande commande);

    MontantPanier calculMontantPanier(Panier panier);


    Integer nbCommandeParEtat(Etat etat);


    String obtenirDevisePourCommande(Commande commande);

    void affecterDeviseAuxCommandes();

    List<ItemCommande> listeDesItemParCommande(Integer commandeId);

    @Transactional
    void archiveExpiredCommande();
}

