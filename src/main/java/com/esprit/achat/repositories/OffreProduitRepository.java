package com.esprit.achat.repositories;
import com.esprit.achat.persistence.entity.AppelOffre;
import com.esprit.achat.persistence.entity.NatureArticle;
import com.esprit.achat.persistence.entity.OffreProduit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OffreProduitRepository extends CrudRepository<OffreProduit, Integer>{
    @Query("Select p FROM OffreProduit p join p.appeloffre na where na.natureArticle = :natureArticle")
    List<OffreProduit> produitParNature(@Param("natureArticle") NatureArticle natureArticle);
}
