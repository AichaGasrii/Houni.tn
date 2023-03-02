package com.esprit.achat.repositories;

import com.esprit.achat.persistence.entity.Paiement;
import org.springframework.stereotype.Repository;

@Repository
public interface PaiementRepository extends CrudRepository<Paiement, Integer>{
}
