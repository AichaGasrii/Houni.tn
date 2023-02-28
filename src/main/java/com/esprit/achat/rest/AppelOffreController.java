package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.*;
import com.esprit.achat.services.Interface.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController

@RequestMapping("/appelOffre")
@AllArgsConstructor
public class AppelOffreController {
    private AppelOffreService appelOffreService;
    private UnitéService unitéService;
    private DemandeAchatService demandeAchatService;
    private NatureArticleService natureArticleService;
    private OffrePService offrePService;
    private OffreSService offreSService;

    @GetMapping
    List<AppelOffre> retrieveAll(){
        return appelOffreService.retrieveAll();
    }

    @PostMapping("/add")
    @CrossOrigin
    void add(@RequestBody  AppelOffre a){
        if(Objects.nonNull(a.getDemandeAchat()) && Objects.nonNull(a.getDemandeAchat().getId()) && Objects.nonNull(a.getNatureArticle()) && Objects.nonNull(a.getNatureArticle().getId()) ) {
            DemandeAchat demandeAchat =  demandeAchatService.retrieve(a.getDemandeAchat().getId());
            NatureArticle natureArticle= natureArticleService.retrieve(a.getNatureArticle().getId());

            a.setDemandeAchat(demandeAchat);
            a.setNatureArticle(natureArticle);
        }

        appelOffreService.add(a);
    }


    @PutMapping("/edit")
    void update(@RequestBody AppelOffre a){
        appelOffreService.update(a);
    }

    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable("id") Integer id){
        appelOffreService.remove(id);
    }

    @GetMapping("/{id}")
    AppelOffre retrieve(@PathVariable("id") Integer id){
        return appelOffreService.retrieve(id);
    }

    @PutMapping(value = "/affecterAppleOffreAOffreProduit/{idA}/{idO}")
    public void affecterAppleOffreAOffreProduit(@PathVariable int idA, @PathVariable int idO) {
        appelOffreService.affecterAppleOffreAOffreProduit(idA, idO);
    }

    @GetMapping("/desaffecterAppeloffreNatureArticle/{idA}")
    void desaffecterAppeloffreNatureArticle (@PathVariable Integer idA){
        appelOffreService.desaffecterAppeloffreNatureArticle(idA );
    }

}