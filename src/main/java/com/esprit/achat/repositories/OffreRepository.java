package com.esprit.achat.repositories;
import com.esprit.achat.persistence.entity.Offre;
import org.springframework.stereotype.Repository;

@Repository
public interface OffreRepository extends CrudRepository<Offre, Integer>{
}
