package com.esprit.achat.services.Implementation;


import com.esprit.achat.persistence.entity.*;
import com.esprit.achat.persistence.enumeration.Disponibilite;
import com.esprit.achat.persistence.enumeration.ReclamationType;
import com.esprit.achat.persistence.enumeration.StatutLivraison;
import com.esprit.achat.repositories.*;
import com.esprit.achat.services.Interface.LivraisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Service
public class LivraisonServiceIMP extends CrudServiceIMP<Livraison,Long> implements LivraisonService {

    @Autowired
    private LivraisonRepository livraisonRepository;
    @Autowired
    private  LivreurRepository livreurRepository ;
    @Autowired
    private AdresseRepository adresseRepository ;
    @Autowired
    private FactureRepository factureRepository;
    @Autowired
    private ReclamationRepositoryMy reclamationRepositoryMy ;

    public Livreur  getLivreurLePlusProche(Double latitude, Double longitude) {
        List<Livreur> livreurs = livreurRepository.findByDisponible(Disponibilite.DISPONIBLE);

        if (livreurs == null || livreurs.isEmpty()) {
            throw new RuntimeException("Aucun livreur disponible");
        }
        Livreur livreurLePlusProche = null;
        double distanceMin = Double.MAX_VALUE;
        for (Livreur livreur : livreurs) {
            // Vérifier si le livreur est disponible
            if (livreur.getDisponible() != Disponibilite.DISPONIBLE) {
                continue;
            }
            // Calculer la distance entre le livreur et l'adresse du client
            double distance = distance(livreur.getAdresse().getLatitude(), livreur.getAdresse().getLongitude(), latitude, longitude);
           // Mettre à jour le livreur le plus proche si nécessaire
            if (distance < distanceMin) {
                distanceMin = distance;
                livreurLePlusProche = livreur;
            }
        }
        if (livreurLePlusProche == null) {
            throw new RuntimeException("Aucun livreur disponible à proximité");
        }
        return livreurLePlusProche;

    }

    public String ReclamationLivraison(Long livraison) {
        Livraison l = livraisonRepository.findById(livraison).orElse(null);
         Reclamation r = new Reclamation();
        if (l.getNoteClient() <2.2 && l.getStatutLivraison()==StatutLivraison.LIVREE) {

           r.setReclamationType(ReclamationType.SERVICE);
           r.setDescription("Reclamation cree pour la Livraison "+l.getId()+"");
           Date date =new Date(System.currentTimeMillis());
           r.setDate(date);
           l.setReclamation(r);
           l.setRec(true);

                 reclamationRepositoryMy.save(r);
                 livraisonRepository.save(l);
                 return ("Votre Reclamation a etait envoyée avec sucees");
                       }
        else if (l.getNoteClient() > 2.2 && l.getStatutLivraison()==StatutLivraison.LIVREE ){
             l.setRec(false);
             livraisonRepository.save(l);
            return ("la note du client est superieur a 2.2, Aucune Reclamation effectuée ");
        }
        return ("la Livraison n'est pas encore Livrée ");

    }

    public void annulerLivraison(Long id) {
        Livraison l = livraisonRepository.findById(id).orElse(null);
        // Annule la livraison pour la commande spécifiée

        l.setDateLivraison(LocalDate.now());
        l.setStatutLivraison(StatutLivraison.ANNULEE);
        l.setLivreur(null);

        livraisonRepository.save(l);
    }

    @Transactional
    public void affecterFactureLivraison(Integer factureId, Long livraisonId) {
        Livraison livraison = livraisonRepository.findById(livraisonId).orElse(null);
        Facture facture = factureRepository.findById(factureId).orElse(null);
        if (livraison == null || facture == null) {
            throw new EntityNotFoundException("Livraison ou Facture introuvable");
        }
        facture.setLivraison(livraison);
        factureRepository.save(facture);
        livraisonRepository.save(livraison);
    }

    @Transactional
    public String planifierLivraison(Facture facture, Livreur livreur, String typeLivraison) {
        if ((livreur.getDisponible() == Disponibilite.OCCUPE) || (livreur.getDisponible() == Disponibilite.EN_Pause)) {
            // Si le livreur est indisponible, retourner un message d'erreur
            return "Le livreur " + livreur.getNom() + " est " + livreur.getDisponible() + " pour le moment.";
        }

        // Assigner le livreur à la commande
        facture.setLivreur(livreur);

        // Déterminer la date de livraison selon le type de livraison
        LocalDate dateLivraison;
        if (typeLivraison.equals("rapide")) {
            facture.setFraisLivraison(facture.getFraisLivraison() + 5.0); // Ajouter 5 dinars de frais pour la livraison rapide
            dateLivraison = LocalDate.now().plusDays(3);
        } else if (typeLivraison.equals("express")) {
            facture.setFraisLivraison(facture.getFraisLivraison() + 10.0); // Ajouter 10 dinars de frais pour la livraison express
            dateLivraison = LocalDate.now().plusDays(1);
        }  else if (typeLivraison.equals("normal")) {
            facture.setFraisLivraison(facture.getFraisLivraison() + 3.0); // Ajouter 10 dinars de frais pour la livraison express
            dateLivraison = LocalDate.now().plusDays(7);
        }
        else {
            // Si le type de livraison n'est pas reconnu, retourner un message d'erreur
            return "Type de livraison inconnu : " + typeLivraison;
        }

        facture.setDateLivraison(dateLivraison);

        // Créer un nouveau suivi de livraison pour la commande
        Livraison livraison = new Livraison();
        livraison.setDatePlanification(LocalDate.now());
        livraison.setStatutLivraison(StatutLivraison.EN_COURS);
        livraison.setDateLivraison(dateLivraison);
        livraison.setTypeLivraison(typeLivraison);
        livraison.setAdresse(livreur.getAdresse());
        livraison.setLivreur(livreur);
        livraison.setFacture(facture);
        facture.setLivraison(livraison);

        // Sauvegarder la commande dans la base de données
        factureRepository.save(facture);
        livraisonRepository.save(livraison);

        // Marquer le livreur comme indisponible
        livreur.setDisponible(Disponibilite.OCCUPE);
        if (livraison.getDateLivraison().equals(LocalDate.now())) {
            livreur.setDisponible(Disponibilite.DISPONIBLE);
        }

        // Retourner un message de confirmation avec les détails de la livraison
        return "La livraison a été planifiée avec succès pour la commande numéro " + facture.getId() +
                " vers l'emplacement " + facture.getAdresseclient() + " avec le livreur " + livreur.getNom() +
                " pour le " + livraison.getDateLivraison() + ". Le frais de livraison est de " + facture.getFraisLivraison() + " dinars.";
    }

    public double distance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Rayon de la Terre en kilomètres
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}
