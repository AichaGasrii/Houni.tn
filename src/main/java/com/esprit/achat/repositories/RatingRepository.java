package com.esprit.achat.repositories;

import com.esprit.achat.persistence.entity.Rating;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends CrudRepository<Rating, Integer> {
}
