package com.esprit.achat.repositories;

import com.esprit.achat.persistence.entity.Facture;
import com.esprit.achat.persistence.entity.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FactureRepository extends CrudRepository<Facture, Integer>{

    @Query("SELECT f FROM Facture f ORDER BY f.totalttc DESC")
   List<Facture> findAllOrderBytotalttcDesc();



}
