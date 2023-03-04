package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.ItemFacture;
import com.esprit.achat.persistence.entity.ItemFactureAvoir;
import com.esprit.achat.services.Interface.ItemFactureAvoirService;
import com.esprit.achat.services.Interface.ItemFactureService;
import org.springframework.stereotype.Service;

@Service
public class ItemFactureAvoirServiceIMP extends CrudServiceIMP<ItemFactureAvoir,Integer> implements ItemFactureAvoirService {
}

