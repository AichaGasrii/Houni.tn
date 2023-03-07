package com.esprit.achat.repositories;

import com.esprit.achat.persistence.entity.CodePromo;
import com.esprit.achat.persistence.entity.Commande;
import com.esprit.achat.persistence.entity.Facture;
import com.esprit.achat.persistence.entity.Question;
import com.esprit.achat.persistence.enumeration.Etat;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CommandeRepository extends CrudRepository<Commande, Integer>{

     public List<Commande> findByclientcin(String clientcin);

     List<Commande> findByEtat(Etat etat);

      List<Commande> findByArchiveFalse();
    List<Commande> findByArchiveFalseAndDateCreation(LocalDate dateCreation);





}
