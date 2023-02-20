package com.esprit.achat.repositories;

import com.esprit.achat.persistence.entity.AppelOffre;
import org.springframework.stereotype.Repository;

@Repository
public interface AppelOffreRepository extends CrudRepository<AppelOffre, Integer>{
}
