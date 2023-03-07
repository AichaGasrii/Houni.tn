package com.esprit.achat.repositories;

import com.esprit.achat.persistence.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepositoryMy extends JpaRepository<Rating, Integer> {
    //List<Rating> indByOffreProduitId(Integer OffreProduitId);
    //@Query("SELECT c FROM Rating c where c.OffreProduit.id= :id_offer GROUP BY COUNT(c.OffreProduit.id)")
   @Query("SELECT c.rate,COUNT(c.id) FROM Rating c where c.offreProduit.id= :id_offer GROUP BY  c.rate")
    Object[] getRatingByOffer(@Param("id_offer") Integer idoff);
    // @Query("SELECT p.id, COUNT(r.id) FROM Product p LEFT JOIN Rating r ON p.id = r.product_id WHERE p.id = :productId GROUP BY p.id")}
}