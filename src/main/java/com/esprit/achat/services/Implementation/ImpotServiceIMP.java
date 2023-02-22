package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.Facture;
import com.esprit.achat.persistence.entity.Impot;
import com.esprit.achat.services.Interface.FactureService;
import com.esprit.achat.services.Interface.ImpotService;
import org.springframework.stereotype.Service;

@Service
public class ImpotServiceIMP extends CrudServiceIMP<Impot,Integer> implements ImpotService {
}
