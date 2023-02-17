package com.esprit.achat.repositories;

import com.esprit.achat.persistence.entity.Paiement;
import com.esprit.achat.persistence.entity.Question;
import org.springframework.stereotype.Repository;

@Repository
public interface PaiementRepository extends CrudRepository<Paiement, Integer>{
}
