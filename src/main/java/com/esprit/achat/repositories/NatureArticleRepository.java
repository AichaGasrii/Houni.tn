package com.esprit.achat.repositories;
import com.esprit.achat.persistence.entity.NatureArticle;
import org.springframework.stereotype.Repository;

@Repository
public interface NatureArticleRepository extends CrudRepository<NatureArticle, Integer>{
}
