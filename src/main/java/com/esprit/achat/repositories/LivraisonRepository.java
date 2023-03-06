package com.esprit.achat.repositories;

import com.esprit.achat.persistence.entity.Livraison;
import com.esprit.achat.persistence.entity.Livreur;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivraisonRepository extends CrudRepository<Livraison,Long> {
  //  List<Livraison> findByEffectuee(boolean effectuee);
  //List<Livreur> findAllLivreursDisponibles();

}
