package com.esprit.achat.repositories;

import com.esprit.achat.persistence.entity.Livreur;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import java.util.List;

@Repository
public interface LivreurRepository extends CrudRepository<Livreur,Long> {

   // List<Livreur> findByDisponibiliteTrue();
   @Query("SELECT l FROM Livreur l ORDER BY l.MoyenneNote DESC")
   List<Livreur> findAllOrderByNoteMoyenneDesc();
   List<Livreur> findByDateCreationBetween(LocalDate debut, LocalDate fin);




}
