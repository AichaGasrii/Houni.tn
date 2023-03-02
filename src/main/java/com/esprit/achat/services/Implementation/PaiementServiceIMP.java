package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.Paiement;
import com.esprit.achat.services.Interface.PaiementService;
import org.springframework.stereotype.Service;

@Service
public class PaiementServiceIMP  extends CrudServiceIMP<Paiement,Integer> implements PaiementService {
}