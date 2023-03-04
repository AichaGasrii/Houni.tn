package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.AppelOffre;
import com.esprit.achat.persistence.entity.AutreCharge;
import com.esprit.achat.services.Interface.AppelOffreService;
import com.esprit.achat.services.Interface.AutreChargeService;
import org.springframework.stereotype.Service;

@Service
public class AutreChargeServiceIMP extends CrudServiceIMP<AutreCharge,Integer> implements AutreChargeService {
}