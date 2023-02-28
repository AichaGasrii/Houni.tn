package com.esprit.achat.services.Implementation;

import com.esprit.achat.mail.EmailControllers;
import com.esprit.achat.persistence.entity.Rating;
import com.esprit.achat.persistence.entity.Reclamation;
import com.esprit.achat.repositories.RatingRepositoryMy;
import com.esprit.achat.services.Interface.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceIMP extends CrudServiceIMP<Rating,Integer> implements RatingService {

    @Autowired
    private RatingRepositoryMy RatingRepositoryMy ;
 @Override
   public Object[] getRatingByOffer(Integer idoff) {
       return 	RatingRepositoryMy.getRatingByOffer(idoff);
   }

}
