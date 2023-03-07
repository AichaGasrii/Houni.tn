package com.esprit.achat.repositories;

import com.esprit.achat.persistence.entity.Adresse;
import com.esprit.achat.persistence.entity.Livreur;
import com.esprit.achat.persistence.enumeration.Disponibilite;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;

import java.util.List;

@Repository
public interface LivreurRepository extends CrudRepository<Livreur,Long> {


   List<Livreur> findByDisponible(Disponibilite disponibilite);
   @Query("SELECT l FROM Livreur l ORDER BY l.MoyenneNote DESC")
   List<Livreur> findAllOrderByNoteMoyenneDesc();
   List<Livreur> findByDateCreationBetween(LocalDate debut, LocalDate fin);




}
