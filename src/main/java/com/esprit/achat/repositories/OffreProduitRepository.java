package com.esprit.achat.repositories;
import com.esprit.achat.persistence.entity.OffreProduit;
import org.springframework.stereotype.Repository;

@Repository
public interface OffreProduitRepository extends CrudRepository<OffreProduit, Integer>{
}
