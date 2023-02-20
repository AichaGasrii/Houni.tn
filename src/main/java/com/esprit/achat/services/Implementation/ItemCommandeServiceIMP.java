package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.ItemCommande;
import com.esprit.achat.persistence.entity.ItemFactureAvoir;
import com.esprit.achat.services.Interface.ItemCommandeService;
import com.esprit.achat.services.Interface.ItemFactureAvoirService;
import org.springframework.stereotype.Service;

@Service
public class ItemCommandeServiceIMP extends CrudServiceIMP<ItemCommande,Integer> implements ItemCommandeService {
}

