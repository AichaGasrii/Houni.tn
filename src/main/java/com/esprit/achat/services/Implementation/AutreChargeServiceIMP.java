package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.AutreCharge;
import com.esprit.achat.persistence.entity.DemandeAchat;
import com.esprit.achat.persistence.enumeration.ECharge;
import com.esprit.achat.repositories.AutreChargeRepository;
import com.esprit.achat.repositories.DemandeAchatReporitory;
import com.esprit.achat.services.Interface.AutreChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class AutreChargeServiceIMP extends CrudServiceIMP<AutreCharge,Integer> implements AutreChargeService {
    @Autowired
     AutreChargeRepository autreChargeRepository;
    @Autowired
    DemandeAchatReporitory demandeAchatReporitory;


    @Override
    public List<DemandeAchat> getAllDemandesAchatByAutreCharge(Integer autreChargeId) {
        AutreCharge autreCharge = autreChargeRepository.findById(autreChargeId).orElse(null);
        
        return autreCharge.getDemandeAchats();
    }

    @Override
    public List<DemandeAchat> getDemandesAchatByECharge(String eCharge) {
        ECharge charge = ECharge.valueOf(eCharge); // convertit la chaîne en une valeur d'énumération
        List<AutreCharge> autresCharges = autreChargeRepository.findByECharge(charge); // récupère les autres charges ayant la même valeur d'énumération
        List<DemandeAchat> demandes = new ArrayList<>();
        for (AutreCharge autreCharge : autresCharges) {
            demandes.addAll(demandeAchatReporitory.findByAutreCharge(autreCharge));
        }
        return demandes;
    }

    @Override
    public Integer nbChargeParECharge(ECharge eCharge) {

        Integer nbr = 0;


        List<AutreCharge> autreCharges = autreChargeRepository.findAll();
        for (AutreCharge autreCharge : autreCharges) {
            if (autreCharge.getECharge().equals(eCharge)) {
                nbr++;
            }
        }

        return nbr;
    }
}