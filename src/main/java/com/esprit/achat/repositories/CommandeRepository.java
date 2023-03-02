package com.esprit.achat.repositories;


import com.esprit.achat.persistence.entity.Commande;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CommandeRepository extends CrudRepository<Commande, Integer>{

     public List<Commande> findByclientcin(String clientcin);
}
