package com.esprit.achat.repositories;

import com.esprit.achat.persistence.entity.Devise;
import com.esprit.achat.persistence.entity.Question;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviseRepository extends CrudRepository<Devise, Integer>{
}
