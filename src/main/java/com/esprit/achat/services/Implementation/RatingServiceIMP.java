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
public class RatingServiceIMP  implements RatingService {
    @Autowired
    private RatingRepositoryMy RatingRepositoryMy ;
    @Autowired
    EmailControllers EC;
    // @Override
    //public List<Rating> indByOffreProduitId(Integer OffreProduitId) {
    // List<Rating> ratings =  RatingRepositoryMy.findByOffreProduitId(OffreProduitId);
    // return ratings;
    // }
 @Override
   public Object[] getRatingByOffer(Integer idoff) {
       return 	RatingRepositoryMy.getRatingByOffer(idoff);
   }
    @Override
    public List<Rating> retrieveAll() {
        try{
            return  RatingRepositoryMy.findAll();

        } catch (Exception err) {
            System.out.println("Un erreur est survenue : " + err);
        }
        return null;
    }

    @Override
    public void add(Rating t) {


        RatingRepositoryMy.save(t);


    }

    @Override
    public void update(Rating t) {
        try {
            System.out.println("mise a jour avec succes");
            RatingRepositoryMy.save(t);
        } catch (Exception err) {
            System.out.println("Un erreur est survenue : " + err);
        }

    }

    @Override
    public void remove(Integer id) {
        try{
            Rating t = RatingRepositoryMy.findById(id).orElse(null);
            RatingRepositoryMy.delete(t);
        } catch (Exception err) {
            System.out.println("Un erreur est survenue : " + err);
        }
    }

    @Override
    public Rating retrieve(Integer id) {
        try{
            Rating cr= RatingRepositoryMy.findById(id).get();
            if(cr != null)
            {
                EC.ApplicationMail();
            }
            return  cr;

        } catch (Exception err) {
            System.out.println("Un erreur est survenue : " + err);
        }
        return null;
    }
}
