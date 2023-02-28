package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.AutreCharge;
import com.esprit.achat.persistence.entity.CodePromo;
import com.esprit.achat.services.Interface.AutreChargeService;
import com.esprit.achat.services.Interface.CodePromoService;
import org.springframework.stereotype.Service;

@Service
public class CodePromoServiceIMP extends CrudServiceIMP<CodePromo,Integer> implements CodePromoService {
}
