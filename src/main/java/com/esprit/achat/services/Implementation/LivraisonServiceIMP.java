package com.esprit.achat.services.Implementation;


import com.esprit.achat.persistence.entity.*;
import com.esprit.achat.persistence.enumeration.ReclamationType;
import com.esprit.achat.persistence.enumeration.StatutLivraison;
import com.esprit.achat.repositories.*;
import com.esprit.achat.services.Interface.LivraisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Date;


@Service
public class LivraisonServiceIMP extends CrudServiceIMP<Livraison,Long> implements LivraisonService {

    @Autowired
    private LivraisonRepository livraisonRepository;
    @Autowired
    private FactureRepository factureRepository;
    @Autowired
    private ReclamationRepositoryMy reclamationRepositoryMy ;

    @Transactional
    public String ReclamationLivraison(Long livraison) {
        Livraison l = livraisonRepository.findById(livraison).orElse(null);
         Reclamation r = new Reclamation();
        if (l.getNoteClient() < 2.2 && l.getStatutLivraison()==StatutLivraison.LIVREE) {

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
            return ("la note du client est superieur a 2, Aucune Reclamation effectuée ");
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
    public String planifierLivraison(Facture facture, Livreur livreur) {
        if (!livreur.isDisponible()) {

            // Si le livreur est indisponible, retourner un message d'erreur
            return "Le livreur " + livreur.getNom() + " est indisponible pour le moment.";
        }
        // Assigner le livreur à la commande
        facture.setLivreur(livreur);
        facture.setDateLivraison(LocalDate.now().plusDays(2));



        // Créer un nouveau suivi de livraison pour la commande
        Livraison livraison = new Livraison();
        livraison.setDatePlanification(LocalDate.now());
        livraison.setStatutLivraison(StatutLivraison.EN_COURS);
        livraison.setDateLivraison(LocalDate.now().plusDays(2));
        livraison.setAdresseClient(facture.getAdresseclient());
        livraison.setLivreur(livreur);
        livraison.setFacture(facture);
        facture.setLivraison(livraison);

        // Sauvegarder la commande dans la base de données
         factureRepository.save(facture);
        livraisonRepository.save(livraison);

        // Marquer le livreur comme indisponible
        livreur.setDisponible(false);
        if (livraison.getDateLivraison().equals(LocalDate.now())) {
            livreur.setDisponible(true);
        }

        // Retourner un message de confirmation avec les détails de la livraison
        return "La livraison a été planifiée avec succès pour la commande numéro " + facture.getId() +
                " vers l'emplacement " + facture.getAdresseclient() + " avec le livreur " + livreur.getNom() +
                " pour le " + livraison.getDateLivraison() + ". Le frais de livraison est de " + facture.getDevise() + " dinars.";
    }


}
