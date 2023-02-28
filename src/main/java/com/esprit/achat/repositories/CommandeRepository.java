package com.esprit.achat.repositories;

import com.esprit.achat.persistence.entity.Commande;
import com.esprit.achat.persistence.entity.Question;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandeRepository extends CrudRepository<Commande, Integer>{
}
