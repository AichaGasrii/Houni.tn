package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.Paiement;
import com.esprit.achat.repositories.PaiementRepository;
import com.esprit.achat.services.Interface.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaiementServiceIMP  extends CrudServiceIMP<Paiement,Integer> implements PaiementService {
    @Autowired
    PaiementRepository paiementRepository;
@Override
    public String obtenirDevisePourPaiement(Paiement paiement) {
        if (paiement == null) {
            return "devise introuvable";
        }
        String adresse = paiement.getPays().trim().toLowerCase();
        switch (adresse) {
            case "Tunisie":
                return "dt";
            case "USA":
            case "Canada":
                return "dollar";
            case "France":
            case "Belgique":
                return "euro";
            case "UK":
                return "pound";
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

}