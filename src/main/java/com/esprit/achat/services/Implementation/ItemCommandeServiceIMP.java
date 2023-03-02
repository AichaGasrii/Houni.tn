package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.ItemCommande;
import com.esprit.achat.services.Interface.ItemCommandeService;
import org.springframework.stereotype.Service;

@Service
public class ItemCommandeServiceIMP extends CrudServiceIMP<ItemCommande,Integer> implements ItemCommandeService {
}

