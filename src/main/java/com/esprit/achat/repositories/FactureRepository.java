package com.esprit.achat.repositories;

import com.esprit.achat.persistence.entity.Facture;
import com.esprit.achat.persistence.entity.Question;
import org.springframework.stereotype.Repository;

@Repository
public interface FactureRepository extends CrudRepository<Facture, Integer>{
}
