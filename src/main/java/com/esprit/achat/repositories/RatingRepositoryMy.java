package com.esprit.achat.repositories;

import com.esprit.achat.persistence.entity.Rating;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepositoryMy extends CrudRepository<Rating, Integer> {
}
