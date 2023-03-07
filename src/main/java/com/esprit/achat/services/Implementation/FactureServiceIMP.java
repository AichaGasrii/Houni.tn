package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.*;
import com.esprit.achat.repositories.FactureRepository;
import com.esprit.achat.services.Interface.FactureService;
import com.mysql.cj.xdevapi.Client;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class FactureServiceIMP  extends CrudServiceIMP<Facture,Integer> implements FactureService {
    private FactureRepository factureRepository;


    @Override
    public Double  calculermontantTTC(Facture facture) {
        Double totalttc =0.0;

        // Calcul total ttc de chaque montant ttc de items
        for (ItemFacture itemFacture : facture.getItems()){
            totalttc +=  itemFacture.getMontantTtc();
        }

        return totalttc;
    }

    @Override
    public List<ItemFacture> listeDesItemParFacture(Integer factureId) {
        Facture facture = factureRepository.findById(factureId)
                .orElseThrow(() -> new EntityNotFoundException("Facture not found with id " + factureId));
        return facture.getItems();
    }





}
