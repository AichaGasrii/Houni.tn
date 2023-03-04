package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.ItemFactureAvoir;
import com.esprit.achat.repositories.ItemFactureAvoirRepository;
import com.esprit.achat.services.Interface.ItemFactureAvoirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemFactureAvoirServiceIMP extends CrudServiceIMP<ItemFactureAvoir,Integer> implements ItemFactureAvoirService {
    @Autowired
    ItemFactureAvoirRepository itemFactureAvoirRepository;

    @Override
    public void affecterTVAAuxItems() {
        List<ItemFactureAvoir> itemFactureAvoirs = itemFactureAvoirRepository.findAll();
        for (ItemFactureAvoir itemFactureAvoir : itemFactureAvoirs) {
            double quantity = itemFactureAvoir.getQuantity();
            double tva;
            if (quantity <= 10) {
                tva = 0.07;
            } else if (quantity > 10 && quantity <= 50) {
                tva = 0.12;
            } else {
                tva = 0.18;
            }
            double priceHt = itemFactureAvoir.getPriceHt();
            double montantHt = priceHt * quantity;
            double montantTtc = montantHt * (1 + tva);
            itemFactureAvoir.setTva(tva);
            itemFactureAvoir.setMontantHt(montantHt);
            itemFactureAvoir.setMontantTtc(montantTtc);
        }
        itemFactureAvoirRepository.saveAll(itemFactureAvoirs);
    }
}

