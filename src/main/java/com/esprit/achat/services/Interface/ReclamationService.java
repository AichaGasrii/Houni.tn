package com.esprit.achat.services.Interface;

import com.esprit.achat.persistence.entity.Rating;
import com.esprit.achat.persistence.entity.Reclamation;

import java.util.List;

public interface ReclamationService extends CrudService<Reclamation, Integer> {
   public Reclamation updateReclamation(Integer id, Reclamation reclamation);
   public List<Reclamation> getReclamationByUser(String name);
}
