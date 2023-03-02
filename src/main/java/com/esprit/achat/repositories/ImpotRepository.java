package com.esprit.achat.repositories;

import com.esprit.achat.persistence.entity.Impot;
import org.springframework.stereotype.Repository;

@Repository
public interface ImpotRepository extends CrudRepository<Impot, Integer>{
}
