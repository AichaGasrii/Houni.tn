package com.esprit.achat.services.Interface;

import com.esprit.achat.persistence.entity.Livreur;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface LivreurService extends CrudService<Livreur,Long> {

    String chercherLivreurDisponible(Long id) ;
    String ajouterNoteLivreur(Long idLivraison, int note);
    String verifierPrimeLivreur(Long l) ;
    List<Livreur> trierLivreurParNoteMoyenne();
     String  getLivreurMaxLikesForYear(int annee);

     void supprimerLivreurPlusDislike(int annee);
    ResponseEntity<Livreur> ajouterLike(Long id);
    ResponseEntity<Livreur> ajouterdisLike(Long id);

}
