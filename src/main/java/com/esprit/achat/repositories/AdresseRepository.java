package com.esprit.achat.repositories;

import com.esprit.achat.persistence.entity.Adresse;
import org.springframework.stereotype.Repository;

@Repository
public interface AdresseRepository extends CrudRepository<Adresse,Long> {
    Adresse findByLatitudeAndLongitude(Double latitude, Double longitude);
}
