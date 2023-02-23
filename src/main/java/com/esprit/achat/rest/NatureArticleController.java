package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.*;
import com.esprit.achat.services.Interface.CommandeService;
import com.esprit.achat.services.Interface.NatureArticleService;
import com.esprit.achat.services.Interface.OffrePService;
import com.esprit.achat.services.Interface.UnitéService;
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
    private UnitéService unitéService;

    @GetMapping
    List<NatureArticle> retrieveAll(){
        return natureArticleService.retrieveAll();
    }

    @PostMapping("/add")
    void add(@RequestBody NatureArticle n){

        if(Objects.nonNull(n.getOffreProduit()) && Objects.nonNull(n.getOffreProduit().getId()) && Objects.nonNull(n.getUnite()) && Objects.nonNull(n.getUnite().getId()) ) {
            OffreProduit offreProduit =  offrePService.retrieve(n.getOffreProduit().getId());
            Unité unité =  unitéService.retrieve(n.getUnite().getId());
            n.setOffreProduit(offreProduit);
            n.setUnite(unité);
        }

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

   /* @PostMapping("/affecter-unites")
    void affecterUnitesAuxNaturesArticles() {
        natureArticleService.affecterUnitesAuxNaturesArticles();
    }

    */

    @PostMapping("/affecter-unites")
    public ResponseEntity<Unité> affecterUnitesAuxNaturesArticles() {
        natureArticleService.affecterUnitesAuxNaturesArticles();
        return ResponseEntity.ok().build();
    }
}
