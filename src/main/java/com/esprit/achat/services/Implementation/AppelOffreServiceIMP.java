package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.*;
import com.esprit.achat.repositories.AppelOffreRepository;
import com.esprit.achat.repositories.OffreProduitRepository;
import com.esprit.achat.services.Interface.AppelOffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class AppelOffreServiceIMP extends CrudServiceIMP<AppelOffre,Integer> implements AppelOffreService {
    @Autowired
    AppelOffreRepository appelOffreRepository;
    @Autowired
    OffreProduitRepository offreProduitRepository;



    @Override
    public void desaffecterAppeloffreNatureArticle( Integer idA) {

        AppelOffre appelOffre =appelOffreRepository.findById(idA).orElse(null);

        appelOffre.setNatureArticle(null);
        appelOffreRepository.save(appelOffre);

    }
    @Override
    public double calculerPrixTotal(AppelOffre appelOffre) {
        double prixTotal = 0.0;

        // Calculer le prix total des offres de produits
        for (OffreProduit produit : appelOffre.getOffreProduits()) {
            prixTotal += produit.getQuantite() * produit.getPrixUnitaire();
        }

        // Calculer le prix total des offres de services
        for (OffreService service : appelOffre.getOffreServices()) {
            prixTotal += service.getHeures() * service.getPrixparheure();
        }

        return prixTotal;
    }

@Override
    public String notif(DemandeAchat demande, AppelOffre offre) {
        String message = "Notification pour la demande d'achat: " + demande.getNom() + ":\n";
        message += "Le meilleur match est l'appel d'offre: " + offre.getNom() + ":\n";
        message += "Description : " + offre.getDescription() + "\n";
        message += "Quantité disponible : " + offre.getQuantiteMin() + "\n";
        message += "Prix Total : " + offre.getPrixTotal() + "\n";
    message += "Vous pouvez sois refuser ce match sois l'accepter et passer une commande "  ;
        // autres informations à inclure dans la notification

        return message;
    }

    @Override
    public AppelOffre trouverMeilleurMatch(DemandeAchat demande)  {

        AppelOffre meilleurMatch = null;
        double meilleureNote = 2.0;

    List<AppelOffre> offres = appelOffreRepository.findAll();

        for (AppelOffre offre : offres) {
            double note = 0.0;

            if (demande.getNom().equals(offre.getNom())) {
                note += 1.0;
            }

            if (demande.getObjet().equals(offre.getObjet())) {
                note += 1.0;
            }

            if (demande.getQuantiteMin() <= offre.getQuantiteMin()) {
                note += 1.0;
            }

            // autres règles de correspondance

            if (note > meilleureNote) {
                meilleurMatch = offre;
                meilleureNote = note;
            }
        }


        // call the notif method to send the notification
       notif(demande, meilleurMatch);

        return meilleurMatch;
    }

@Override
    public String accepterMatch(AppelOffre match) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Voulez-vous accepter le matching ? (oui ou non)");
        String reponse = scanner.nextLine();

        if ("oui".equalsIgnoreCase(reponse)) {
            match.setAccepte(true);
            appelOffreRepository.save(match);
            return "Matching accepté, votre commande sera préparée aux plus brefs délais.";
        } else {
            match.setAccepte(false);
            appelOffreRepository.save(match);
            return "Matching refusé, nous ferons de notre mieux pour vous trouver le bon matching lors de votre suivante demande d'achat.";
        }
    }



}
