package com.esprit.achat.repositories;

import com.esprit.achat.persistence.entity.Reclamation;
import org.springframework.stereotype.Repository;

@Repository
public interface ReclamationRepositoryMy extends CrudRepository<Reclamation, Integer> {
}
