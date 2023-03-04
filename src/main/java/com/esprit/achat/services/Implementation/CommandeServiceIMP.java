package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.dto.MontantPanier;
import com.esprit.achat.persistence.dto.Panier;
import com.esprit.achat.persistence.entity.CodePromo;
import com.esprit.achat.persistence.entity.Commande;
import com.esprit.achat.persistence.enumeration.Etat;
import com.esprit.achat.repositories.CodePromoRepository;
import com.esprit.achat.repositories.CommandeRepository;
import com.esprit.achat.services.Interface.CommandeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class CommandeServiceIMP  extends CrudServiceIMP<Commande,Integer> implements CommandeService {
    @Autowired
    CodePromoRepository codePromoRepository;
    @Autowired
    CommandeRepository commandeRepository;

    public MontantPanier calculMontantPanier(Panier panier) {
        //calcule du panier = quantity*montantHt
        MontantPanier montantPanier = new MontantPanier();
        montantPanier.setMontantTotalHT(0d);
        montantPanier.setMontantTotalAPayer(0d);
        panier.getItems().stream().forEach(item -> {
            Double aux = (item.getQuantity() * item.getMontantHt()) ;
          //  aux = aux + aux * (item.getTva() /100d);
            montantPanier.setMontantTotalHT(aux + montantPanier.getMontantTotalHT());
        });

        //codePromo selon la remise que l'user va l'affecter
        List<CodePromo> codePromoList = codePromoRepository.findByCode(panier.getCodePromo());
        if (!codePromoList.isEmpty()) {
            CodePromo codePromo = codePromoList.get(0);
            if (Objects.nonNull(codePromo.getRemise()) && codePromo.getRemise() > 0) {
                Double montant = montantPanier.getMontantTotalHT();
                montant = montant - montant * (codePromo.getRemise() / 100d);
                montantPanier.setMontantTotalHT(montant);
            }}

        //un client avec son cin est fidéle lorsqu'il passe au moins 5 commande il va avoir donc une remise de 20%
        List<Commande> commandeList = commandeRepository.findByclientcin(panier.getClientcin());
        if (!commandeList.isEmpty()) {
            Commande commande = commandeList.get(0);
            if (Objects.nonNull(commande.getClientcin()) && commandeList.size() > 5) {
                Double montant = montantPanier.getMontantTotalHT();
                montant = montant - montant * (20d / 100d);
                montantPanier.setMontantTotalHT(montant);
            }
        }
        return montantPanier;
    }

    @Override
    public Integer nbCommandeParEtat(Etat etat) {

        Integer nbr = 0;


        List<Commande> commandeList = commandeRepository.findAll();
        for (Commande commande : commandeList) {
            if (commande.getEtat().equals(etat)) {
                nbr++;
            }
        }

        return nbr;
    }


}

