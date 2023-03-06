package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.*;
import com.esprit.achat.services.Interface.NatureArticleService;
import com.esprit.achat.services.Interface.OffrePService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/natureArticle")
@AllArgsConstructor
public class NatureArticleController {
    private NatureArticleService natureArticleService;
    private OffrePService offrePService;


    @GetMapping
    List<NatureArticle> retrieveAll(){
        return natureArticleService.retrieveAll();
    }

    @PostMapping("/add")
    void add(@RequestBody NatureArticle n){

        natureArticleService.add(n);
    }



    @PutMapping("/edit")
    void update(@RequestBody  NatureArticle n){
        natureArticleService.update(n);
    }

    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable("id") Integer id){
        natureArticleService.remove(id);
    }

    @GetMapping("/{id}")
    NatureArticle retrieve(@PathVariable("id") Integer id){
        return natureArticleService.retrieve(id);
    }


    @PostMapping("/affecter-unites")
    public ResponseEntity<NatureArticle> affecterUnitesAuxNaturesArticles() {
        natureArticleService.affecterUnitesAuxNaturesArticles();
        return ResponseEntity.ok().build();
    }

    @PutMapping("affecterOffresProduitsANatureArticle/{idOffre}/{idNature}")
    void affecterOffresProduitsANatureArticle(@PathVariable Integer idOffre, @PathVariable Integer idNature){
        natureArticleService.affecterOffresProduitsANatureArticle(idOffre,idNature);
    }

    @GetMapping("/produit-nature/{natureId}")
    @ResponseStatus
    public List<OffreProduit> listeDeproduitParNature(@PathVariable Integer natureId) {
        return natureArticleService.listeDeproduitParNature(natureId);
    }

}