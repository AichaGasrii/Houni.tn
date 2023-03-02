package com.esprit.achat.repositories;

import com.esprit.achat.persistence.entity.Reclamation;
import org.springframework.stereotype.Repository;

@Repository
public interface ReclamationRepository extends CrudRepository<Reclamation, Integer> {
}
