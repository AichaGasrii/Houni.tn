package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.*;
import com.esprit.achat.repositories.AppelOffreRepository;
import com.esprit.achat.services.Interface.*;
import lombok.AllArgsConstructor;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController

@RequestMapping("/appelOffre")
@PreAuthorize("hasRole('Fournisseur')")
@AllArgsConstructor
public class AppelOffreController {
    private AppelOffreService appelOffreService;
    private DemandeAchatService demandeAchatService;
    private NatureArticleService natureArticleService;
    private AppelOffreRepository appelOffreRepository;
    private OffreSService offreSService;

    @GetMapping
    List<AppelOffre> retrieveAll() {
        return appelOffreService.retrieveAll();
    }

    @PostMapping("/add")
    @CrossOrigin
    void add(@RequestBody AppelOffre a) {
        if (Objects.nonNull(a.getDemandeAchat()) && Objects.nonNull(a.getDemandeAchat().getId()) && Objects.nonNull(a.getNatureArticle()) && Objects.nonNull(a.getNatureArticle().getId())) {
            DemandeAchat demandeAchat = demandeAchatService.retrieve(a.getDemandeAchat().getId());
            NatureArticle natureArticle = natureArticleService.retrieve(a.getNatureArticle().getId());

            a.setDemandeAchat(demandeAchat);
            a.setNatureArticle(natureArticle);
        }

        appelOffreService.add(a);
    }


    @PutMapping("/edit")
    void update(@RequestBody AppelOffre a) {
        appelOffreService.update(a);
    }

    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable("id") Integer id) {
        appelOffreService.remove(id);
    }

    @GetMapping("/{id}")
    AppelOffre retrieve(@PathVariable("id") Integer id) {
        return appelOffreService.retrieve(id);
    }

    @PutMapping(value = "/affecterAppleOffreAOffreProduit/{idA}/{idO}")
    public void affecterAppleOffreAOffreProduit(@PathVariable int idA, @PathVariable int idO) {
        appelOffreService.affecterAppleOffreAOffreProduit(idA, idO);
    }

    @GetMapping("/desaffecterAppeloffreNatureArticle/{idA}")
    void desaffecterAppeloffreNatureArticle(@PathVariable Integer idA) {
        appelOffreService.desaffecterAppeloffreNatureArticle(idA);
    }

    @PostMapping("/meilleurMatch/{demande}")
    public ResponseEntity<AppelOffre> trouverMeilleurMatch(@PathVariable ("demande") DemandeAchat demande){
        AppelOffre meilleurMatch = appelOffreService.trouverMeilleurMatch(demande);

        if (meilleurMatch == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(meilleurMatch);
    }

    /*@PostMapping("/trouverMeilleurMatch")
    public ResponseEntity<AppelOffre> trouverMeilleurMatch(@RequestBody DemandeAchat demande) {

        AppelOffre meilleurMatch = null;
        double meilleureNote = 2.0;

        List<AppelOffre> offres = appelOffreRepository.findAll();

        for (AppelOffre offre : offres) {
            double note = 0.0;

            if (demande.getNom().equals(offre.getNom())) {
                note += 1.0;
            }

            if (demande.getQuantiteMin() <= offre.getQuantiteMin()) {
                note += 1.0;
            }

            if (demande.getObjet().equals(offre.getObjet())) {
                note += 1.0;
            }

            // autres règles de correspondance

            if (note > meilleureNote) {
                meilleurMatch = offre;
                meilleureNote = note;
            }
        }

        if (meilleurMatch == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(meilleurMatch);
    }

     */
   /* @GetMapping("/trouverMeilleurMatch/{demandeId}")
    public ResponseEntity<AppelOffre> trouverMeilleurMatch(@PathVariable Integer demandeId) {
        DemandeAchat demande = new DemandeAchat();
        demande.setId(demandeId);
        AppelOffre meilleurMatch = appelOffreService.trouverMeilleurMatch(demande);
        if (meilleurMatch == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(meilleurMatch);
    }

    */

}
