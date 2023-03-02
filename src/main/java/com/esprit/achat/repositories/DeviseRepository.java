package com.esprit.achat.repositories;

import com.esprit.achat.persistence.entity.Devise;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviseRepository extends CrudRepository<Devise, Integer>{
}
