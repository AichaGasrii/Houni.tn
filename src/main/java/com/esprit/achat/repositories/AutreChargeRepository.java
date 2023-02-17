package com.esprit.achat.repositories;

import com.esprit.achat.persistence.entity.AutreCharge;
import org.springframework.stereotype.Repository;

@Repository
public interface AutreChargeRepository extends CrudRepository<AutreCharge, Integer>{
}
