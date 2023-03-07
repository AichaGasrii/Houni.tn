package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.FactureAvoir;
import com.esprit.achat.persistence.entity.ItemFactureAvoir;
import com.esprit.achat.services.Interface.FactureavoirService;
import org.springframework.stereotype.Service;


@Service
public class FactureavoirServiceIMP  extends CrudServiceIMP<FactureAvoir,Integer> implements FactureavoirService {

    @Override
    public Double  calculermontantTTC(FactureAvoir factureAvoir) {
        Double totalttc =0.0;

        // Calcul total ttc de chaque montant ttc de items
        for (ItemFactureAvoir itemFactureAvoir : factureAvoir.getItems()){
            totalttc +=  itemFactureAvoir.getMontantTtc();
        }

        return totalttc;
    }



}
