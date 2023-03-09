package com.esprit.achat.services.Interface;


import com.esprit.achat.persistence.entity.Facture;
import com.esprit.achat.persistence.entity.Livraison;
import com.esprit.achat.persistence.entity.Livreur;


public interface LivraisonService extends CrudService<Livraison,Long> {


    Livreur  getLivreurLePlusProche(Double latitude, Double longitude) ;
     void annulerLivraison(Long id) ;
     void affecterFactureLivraison(Integer factureId, Long livraisonId);
     String planifierLivraison(Facture facture, Livreur livreur, String typeLivraison) ;

      String ReclamationLivraison( Long livraison) ;

}
