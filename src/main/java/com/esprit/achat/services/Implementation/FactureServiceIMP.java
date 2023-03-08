package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.*;
import com.esprit.achat.repositories.FactureRepository;
import com.esprit.achat.services.Interface.FactureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Override
    public String obtenirDevisePourFacture(Facture facture) {
        if (facture == null) {
            return "devise introuvable";
        }
        String adresse = facture.getAdresseclient().trim().toLowerCase();
        switch (adresse) {
            case "tunisie":
                return "TND";
            case "usa":
            case "canada":
                return "USD";
            case "france":
            case "belgique":
                return "EUR";
            case "uk":
                return "GBP";
            case "japon":
                return "JPY";
            case "australie":
                return "AUD";
            case "chine":
            case "hong kong":
            case "singapour":
                return "CNY";
            case "brésil":
                return "BRL";
            case "algérie":
                return "DZD";
            case "inde":
                return "INR";
            case "corée du sud":
                return "KRW";
            case "royaume-uni":
                return "GBP";
            case "mexique":
                return "MXN";
            default:
                return "devise introuvable";
        }
    }
    @Override
    public void affecterDeviseAuxFactures() {
        List<Facture> factures = factureRepository.findAll();
        for (Facture facture : factures) {
            String devise = obtenirDevisePourFacture(facture);
            facture.setDevise(devise);
        }
        factureRepository.saveAll(factures);
    }
    private static final Logger logger = LoggerFactory.getLogger(Facture.class);





}
