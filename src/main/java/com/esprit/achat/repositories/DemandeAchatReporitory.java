package com.esprit.achat.repositories;

import com.esprit.achat.persistence.entity.DemandeAchat;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeAchatReporitory extends CrudRepository<DemandeAchat, Integer>{
}
