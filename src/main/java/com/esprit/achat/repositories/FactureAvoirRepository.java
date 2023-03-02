package com.esprit.achat.repositories;

import com.esprit.achat.persistence.entity.FactureAvoir;
import org.springframework.stereotype.Repository;

@Repository
public interface FactureAvoirRepository extends CrudRepository<FactureAvoir, Integer>{
}
