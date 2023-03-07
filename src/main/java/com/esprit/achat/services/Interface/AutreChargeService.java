package com.esprit.achat.services.Interface;

import com.esprit.achat.persistence.entity.AutreCharge;
import com.esprit.achat.persistence.entity.DemandeAchat;
import com.esprit.achat.persistence.enumeration.ECharge;

import java.util.List;

public interface AutreChargeService extends CrudService<AutreCharge, Integer>{
    List<DemandeAchat> getAllDemandesAchatByAutreCharge(Integer autreChargeId);

    List<DemandeAchat> getDemandesAchatByECharge(String eCharge);

    Integer nbChargeParECharge(ECharge eCharge);
}
