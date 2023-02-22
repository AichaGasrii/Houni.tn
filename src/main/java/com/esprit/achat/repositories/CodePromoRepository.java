package com.esprit.achat.repositories;

import com.esprit.achat.persistence.entity.AutreCharge;
import com.esprit.achat.persistence.entity.CodePromo;
import org.springframework.stereotype.Repository;

@Repository
public interface CodePromoRepository extends CrudRepository<CodePromo, Integer>{
}
