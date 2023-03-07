package com.esprit.achat.services.Implementation;


import com.esprit.achat.persistence.entity.Livraison;
import com.esprit.achat.persistence.entity.Livreur;

import com.esprit.achat.repositories.LivraisonRepository;
import com.esprit.achat.repositories.LivreurRepository;
import com.esprit.achat.services.Interface.LivreurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;




@Service
public class LivreurServiceIMP extends CrudServiceIMP<Livreur,Long> implements LivreurService {


    @Autowired
    private LivreurRepository livreurRepository;
    @Autowired
    private LivraisonRepository livraisonRepository;



    public ResponseEntity<Livreur> ajouterLike(Long id) {
        Optional<Livreur> optionalLivreur = livreurRepository.findById(id);

        if (optionalLivreur.isPresent()) {
            Livreur livreur = optionalLivreur.get();
            livreur.setLikes(livreur.getLikes() + 1);
            livreurRepository.save(livreur);

            return ResponseEntity.ok(livreur);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


public ResponseEntity<Livreur> ajouterdisLike(Long id) {
        Optional<Livreur> optionalLivreur = livreurRepository.findById(id);

        if (optionalLivreur.isPresent()) {
            Livreur livreur = optionalLivreur.get();
            livreur.setDisLikes(livreur.getDisLikes() + 1);
            livreurRepository.save(livreur);

            return ResponseEntity.ok(livreur);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    public String  getLivreurMaxLikesForYear(int annee)  {
        LocalDate debutAnnee = LocalDate.of(annee, Month.JANUARY, 1);
        LocalDate finAnnee = LocalDate.of(annee, Month.DECEMBER, 31);

        List<Livreur> livreurs = livreurRepository.findByDateCreationBetween(debutAnnee, finAnnee);

        if (livreurs.isEmpty()) {
            return ("Aucun livreur trouvé pour l'année " + annee);
        } else {
            Livreur livreurMaxLikes = Collections.max(livreurs, Comparator.comparing(Livreur::getLikes));
            livreurMaxLikes.setPrime(true);
            return ("le livreur qui a le plus de like toute une Annnée est "+livreurMaxLikes.getNom()+"");
        }
    }

    public void supprimerLivreurPlusDislike(int annee) {
        // Récupération de la liste des livreurs créés entre le 1er janvier et le 31 décembre de l'année donnée
        LocalDate debutAnnee = LocalDate.of(annee, Month.JANUARY, 1);
        LocalDate finAnnee = LocalDate.of(annee, Month.DECEMBER, 31);
        List<Livreur> livreurs = livreurRepository.findByDateCreationBetween(debutAnnee, finAnnee);

        // Recherche du livreur ayant le plus de dislikes
        Livreur livreurPlusDeTest = null;
        int maxDislikes = Integer.MIN_VALUE;
        for (Livreur livreur : livreurs) {
            if (livreur.getDisLikes() > maxDislikes) {
                maxDislikes = livreur.getDisLikes();
                livreurPlusDeTest = livreur;
            }
        }


        // Suppression du livreur trouvé
        if (livreurPlusDeTest != null) {
            livreurRepository.delete(livreurPlusDeTest);
            System.out.println("Le livreur ayant le plus de dislikes pour l'année " + annee + " a été supprimé.");
        } else {
            System.out.println("Aucun livreur trouvé pour l'année " + annee + ".");
        }

    }


    public String chercherLivreurDisponible(Long id) {
        Optional<Livreur> livreur = livreurRepository.findById(id);
        if (livreur.isPresent()) {
            return ("Le livreur est disponible");
        } else {
            return "Le livreur n'est pas disponible";
        }
    }
@Transactional
    public String verifierPrimeLivreur(Long l) {
            Livreur livreur = livreurRepository.findById(l).orElse(null);
        int mois = 0;
        LocalDate dateMoyenneSuperieure = null;

        if (livreur.getMoyenneNote() >= 4.8) {
            if (livreur.getDateMoyenneSuperieure() == null) {
                // Si la moyenne est supérieure à 4.8 pour la première fois, stocker la date correspondante
                livreur.setDateMoyenneSuperieure(LocalDate.now());
            } else {
                // Calculer le nombre de mois entre la date de la moyenne supérieure à 4.8 et la date actuelle
                mois = Period.between(livreur.getDateMoyenneSuperieure(), LocalDate.now()).getMonths();
            }

            if (mois >= 1 && mois <= 2) {
                // Si la moyenne est supérieure à 4.8 depuis 1 ou calculer la prime correspondante
                livreur.setPrime(true);
                return ("Le livreur " + livreur.getNom() + " " + livreur.getPrenom() + " a droit à une prime de 150 dinars pour avoir une moyenne supérieure à 4.8 pendant " + mois + " mois cons ");

            }
        } else {
            // Réinitialiser la date de la moyenne supérieure à 4.8 si la moyenne est inférieure à 4.8
            livreur.setDateMoyenneSuperieure(null);
            return ("Le livreur " + livreur.getNom() + " " + livreur.getPrenom() + " n'a pas le droit d'une prime");

        }
             return "fin procedure" ;

    }

    public String ajouterNoteLivreur(Long idLivraison, int note) {
        Optional<Livraison> optionalLivraison = livraisonRepository.findById(idLivraison);
        if (optionalLivraison.isPresent()) {
            Livraison livraison = optionalLivraison.get();
            Livreur livreur = livraison.getLivreur();



            double moyenneNotes = (((livreur.getMoyenneNote() + livraison.getNoteClient()) / (livreur.getMoyenneNote())+1));
            livreur.setMoyenneNote(moyenneNotes);

            livreurRepository.save(livreur);
            livraison.setNoteClient(note);

            livraisonRepository.save(livraison);
            return "La note a été ajoutée avec succès pour la livraison " + idLivraison;
        } else {
            return "La livraison avec l'identifiant " + idLivraison + " n'existe pas.";
        }
    }
    public List<Livreur> trierLivreurParNoteMoyenne() {
        return livreurRepository.findAllOrderByNoteMoyenneDesc();
    }
}
