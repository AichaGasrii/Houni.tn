package com.esprit.achat.repositories;

import com.esprit.achat.persistence.entity.Facture;
import com.esprit.achat.persistence.entity.Paiement;
import com.esprit.achat.persistence.entity.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaiementRepository extends CrudRepository<Paiement, Integer>{

    @Query("SELECT p FROM Paiement p ORDER BY p.prix ASC")
    List<Paiement> findAllOrderByprixASC();
}
