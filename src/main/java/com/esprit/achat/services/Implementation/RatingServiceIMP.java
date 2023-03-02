package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.Rating;
import com.esprit.achat.services.Interface.RatingService;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceIMP extends CrudServiceIMP<Rating,Integer> implements RatingService {
}
