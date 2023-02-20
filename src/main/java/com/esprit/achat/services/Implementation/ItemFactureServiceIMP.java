package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.Facture;
import com.esprit.achat.persistence.entity.ItemFacture;
import com.esprit.achat.services.Interface.FactureService;
import com.esprit.achat.services.Interface.ItemFactureService;
import org.springframework.stereotype.Service;

@Service
public class ItemFactureServiceIMP extends CrudServiceIMP<ItemFacture,Integer> implements ItemFactureService {
}
