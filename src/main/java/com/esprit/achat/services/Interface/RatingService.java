package com.esprit.achat.services.Interface;

import com.esprit.achat.persistence.entity.Rating;
import com.esprit.achat.persistence.entity.Reclamation;

import java.util.List;

public interface  RatingService extends CrudService<Rating, Integer> {
    //public List<Rating> indByOffreProduitId(Integer OffreProduitId);
    public Object[] getRatingByOffer(Integer idoff);

}
