package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.dto.MontantPanier;
import com.esprit.achat.persistence.dto.Panier;
import com.esprit.achat.persistence.entity.*;
import com.esprit.achat.persistence.enumeration.Etat;
import com.esprit.achat.repositories.CodePromoRepository;
import com.esprit.achat.repositories.CommandeRepository;
import com.esprit.achat.services.Interface.CommandeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Slf4j
public class CommandeServiceIMP  extends CrudServiceIMP<Commande,Integer> implements CommandeService {
    @Autowired
    CodePromoRepository codePromoRepository;
    @Autowired
    CommandeRepository commandeRepository;

    @Override
    public Double  calculermontantTTC(Commande commande) {
        Double totalttc =0.0;

        // Calcul total ttc de chaque montant ttc de items
        commande.getItems().size();
       for (ItemCommande itemCommande : commande.getItems()){
           totalttc +=  itemCommande.getMontantTtc();
       }

        return totalttc;
    }

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


    @Override
    public String obtenirDevisePourCommande(Commande commande) {
        if (commande == null) {
            return "devise introuvable";
        }
        String adresse = commande.getAdresseclient().trim().toLowerCase();
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
    public void affecterDeviseAuxCommandes() {
        List<Commande> commandes = commandeRepository.findAll();
        for (Commande commande : commandes) {
            String devise = obtenirDevisePourCommande(commande);
            commande.setDevise(devise);
        }
        commandeRepository.saveAll(commandes);
    }
    private static final Logger logger = LoggerFactory.getLogger(Commande.class);

    @Override
    public List<ItemCommande> listeDesItemParCommande(Integer commandeId) {
        Optional<Commande> optionalCommande = commandeRepository.findById(commandeId);
        if (optionalCommande.isPresent()) {
            Commande commande = optionalCommande.get();
            return commande.getItems();
        }
        return Collections.emptyList();
    }


    @Transactional
    @Override
    public void archiveExpiredCommande() {
        commandeRepository.findByArchiveFalseAndDateCreation(LocalDate.now())
                .stream()
                .forEach(commande -> commande.setArchive(true));
    }


}




