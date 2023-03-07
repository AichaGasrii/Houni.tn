package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.ItemCommande;
import com.esprit.achat.repositories.ItemCommandeRepository;
import com.esprit.achat.services.Interface.ItemCommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCommandeServiceIMP extends CrudServiceIMP<ItemCommande,Integer> implements ItemCommandeService {
    @Autowired
    ItemCommandeRepository itemCommandeRepository;
    @Override
    public void affecterTVAAuxItems() {
        List<ItemCommande> itemCommandes = itemCommandeRepository.findAll();
        for (ItemCommande itemCommande : itemCommandes) {
            double quantity = itemCommande.getQuantity();
            double tva;
            if (quantity <= 10) {
                tva = 0.07;
            } else if (quantity > 10 && quantity <= 50) {
                tva = 0.12;
            } else {
                tva = 0.18;
            }
            double priceHt = itemCommande.getPriceHt();
            double montantHt = priceHt * quantity;
            double montantTtc = montantHt * (1 + tva);
            itemCommande.setTva(tva);
            itemCommande.setMontantHt(montantHt);
            itemCommande.setMontantTtc(montantTtc);
        }
        itemCommandeRepository.saveAll(itemCommandes);
    }


}

