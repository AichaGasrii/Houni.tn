package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.*;
import com.esprit.achat.repositories.AppelOffreRepository;
import com.esprit.achat.services.Interface.*;
import lombok.AllArgsConstructor;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController

@RequestMapping("/appelOffre")
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


    @GetMapping("/desaffecterAppeloffreNatureArticle/{idA}")
    void desaffecterAppeloffreNatureArticle(@PathVariable Integer idA) {
        appelOffreService.desaffecterAppeloffreNatureArticle(idA);
    }

    @GetMapping("/meilleurMatch/{demande}")
    public ResponseEntity<String> trouverMeilleurMatch(@PathVariable ("demande") DemandeAchat demande){
        AppelOffre meilleurMatch = appelOffreService.trouverMeilleurMatch(demande);

        if (meilleurMatch == null) {
            return ResponseEntity.notFound().build();
        }

        String notifMessage = appelOffreService.notif(demande, meilleurMatch);

        return ResponseEntity.ok().body(notifMessage);
    }

    @PutMapping("/updatePriceAppelOffre/{appelOffre}")
    public AppelOffre updatePriceAppelOffre(@PathVariable ("appelOffre") AppelOffre appelOffre) {

        appelOffre.setPrixTotal(appelOffreService.calculerPrixTotal(appelOffre));
        return appelOffreRepository.save(appelOffre);
    }

    @PostMapping("/accepter")
    public ResponseEntity<String> accepterMatch(@RequestBody AppelOffre match) {
        String message = appelOffreService.accepterMatch(match);
        return ResponseEntity.ok(message);
    }

}
