package com.esprit.achat.repositories;

import com.esprit.achat.persistence.entity.Rating;
import com.esprit.achat.persistence.entity.Reclamation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReclamationRepositoryMy extends CrudRepository<Reclamation, Integer> {
    @Query("SELECT c FROM Reclamation c where c.user.userName= :name ")
    List<Reclamation> getReclamationByUser(@Param("name") String name);



}
