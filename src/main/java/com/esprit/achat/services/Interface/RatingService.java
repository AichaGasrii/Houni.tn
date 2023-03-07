package com.esprit.achat.services.Interface;

import com.esprit.achat.persistence.entity.Rating;
import com.esprit.achat.persistence.entity.Reclamation;

import java.util.List;

public interface  RatingService  {
    //public List<Rating> indByOffreProduitId(Integer OffreProduitId);
      public Object[] getRatingByOffer(Integer idoff);
    List<Rating> retrieveAll();
    void add(Rating t);
    void update(Rating t);
    void remove(Integer id);
    Rating retrieve(Integer id);
}
