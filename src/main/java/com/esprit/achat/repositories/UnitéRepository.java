package com.esprit.achat.repositories;
import com.esprit.achat.persistence.entity.Unité;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitéRepository extends CrudRepository<Unité, Integer>{
}
