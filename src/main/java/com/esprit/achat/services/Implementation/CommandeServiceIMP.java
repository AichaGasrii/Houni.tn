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
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.*;
import java.util.List;
import java.util.Objects;
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

    @PostConstruct
    public void scheduleCommandeStatusUpdate() {
        logger.info("scheduleCommandeStatusUpdate() method called");
        try {
            ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
            LocalDateTime now = LocalDateTime.now();
            long initialDelay = Duration.between(now, now.plusMinutes(1)).getSeconds();
            executorService.scheduleAtFixedRate(() -> {
                System.out.println("La tâche est en cours d'exécution...");
                // Récupère la liste des commandes en cours
                List<Commande> commandes = commandeRepository.findByEtat(Etat.ENCOURS);
                Instant now1 = Instant.now();
                for (Commande commande : commandes) {
                    // Vérifie si la commande a été créée il y a plus de 1 minute
                    if (Duration.between(commande.getDateCreation().toInstant(), now1).toMinutes() >= 1) {
                        // Change l'état de la commande en "validé"
                        commande.setEtat(Etat.VALIDE);
                        commandeRepository.save(commande);
                        System.out.println("Commande " + commande.getId() + " mise à jour : " + commande.getEtat());
                    }
                }
            }, initialDelay, 1, TimeUnit.MINUTES);
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite lors de l'exécution de la tâche : " + e.getMessage());
        }
    }

}




