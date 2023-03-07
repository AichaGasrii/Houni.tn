package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.Paiement;
import com.esprit.achat.persistence.enumeration.Methode;
import com.esprit.achat.repositories.PaiementRepository;
import com.esprit.achat.services.Interface.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaiementServiceIMP extends CrudServiceIMP<Paiement,Integer> implements PaiementService {

    @Autowired
    PaiementRepository paiementRepository;

    @Override
    public String obtenirDevisePourPaiement(Paiement paiement) {
        if (paiement == null) {
            return "devise introuvable";
        }
        String adresse = paiement.getPays().trim().toLowerCase();
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
    public void affecterDeviseAuxPaiements() {
        List<Paiement> paiements = paiementRepository.findAll();
        for (Paiement paiement : paiements) {
            String devise = obtenirDevisePourPaiement(paiement);
            paiement.setDevise(devise);
        }
        paiementRepository.saveAll(paiements);
    }

    @Override
    public Integer nbPaiementParMethode(Methode methode) {

        Integer nbr = 0;


        List<Paiement> paiements = paiementRepository.findAll();
        for (Paiement paiement : paiements) {
            if (paiement.getMethode().equals(methode)) {
                nbr++;
            }
        }

        return nbr;
    }

}
