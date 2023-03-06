package com.esprit.achat.repositories;

import com.esprit.achat.persistence.entity.AutreCharge;
import com.esprit.achat.persistence.enumeration.ECharge;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutreChargeRepository extends CrudRepository<AutreCharge, Integer>{
    @Query("SELECT a FROM AutreCharge a WHERE a.eCharge = :eCharge")
    List<AutreCharge> findByECharge(@Param("eCharge") ECharge eCharge);
}
