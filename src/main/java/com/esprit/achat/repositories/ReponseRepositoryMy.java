package com.esprit.achat.repositories;

import com.esprit.achat.persistence.entity.Reponse;
import org.springframework.stereotype.Repository;

@Repository
public interface ReponseRepositoryMy extends CrudRepository<Reponse, Integer> {
}
