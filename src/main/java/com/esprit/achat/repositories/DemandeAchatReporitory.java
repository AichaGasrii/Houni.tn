package com.esprit.achat.repositories;

import com.esprit.achat.persistence.entity.AutreCharge;
import com.esprit.achat.persistence.entity.DemandeAchat;
import com.esprit.achat.persistence.enumeration.OffreType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandeAchatReporitory extends CrudRepository<DemandeAchat, Integer>{

    @Query("SELECT d FROM DemandeAchat d WHERE d.autreCharge = :autreCharge")
    List<DemandeAchat> findByAutreCharge(@Param("autreCharge") AutreCharge autreCharge);

    @Query("Select COUNT(*)FROM DemandeAchat d where d.offreType = :offreType")
    int nbreAchatParType(@Param("offreType") OffreType offreType);
}
