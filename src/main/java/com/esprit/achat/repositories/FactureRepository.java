package com.esprit.achat.repositories;

import com.esprit.achat.persistence.entity.Facture;
import com.esprit.achat.persistence.entity.Question;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FactureRepository extends CrudRepository<Facture, Integer>{


}
