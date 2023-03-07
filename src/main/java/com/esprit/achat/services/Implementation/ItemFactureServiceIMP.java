package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.ItemFacture;
import com.esprit.achat.repositories.ItemFactureRepository;
import com.esprit.achat.services.Interface.ItemFactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemFactureServiceIMP extends CrudServiceIMP<ItemFacture,Integer> implements ItemFactureService {
    @Autowired
    ItemFactureRepository itemFactureRepository;

    @Override
    public void affecterTVAAuxItems() {
        List<ItemFacture> itemFactures = itemFactureRepository.findAll();
        for (ItemFacture itemFacture : itemFactures) {
            double quantity = itemFacture.getQuantity();
            double tva;
            if (quantity <= 10) {
                tva = 0.07;
            } else if (quantity > 10 && quantity <= 50) {
                tva = 0.12;
            } else {
                tva = 0.18;
            }
            double priceHt = itemFacture.getPriceHt();
            double montantHt = priceHt * quantity;
            double montantTtc = montantHt * (1 + tva);
            itemFacture.setTva(tva);
            itemFacture.setMontantHt(montantHt);
            itemFacture.setMontantTtc(montantTtc);
        }
        itemFactureRepository.saveAll(itemFactures);
    }
}
