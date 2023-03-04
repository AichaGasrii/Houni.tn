package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.*;
import com.esprit.achat.services.Interface.NatureArticleService;
import com.esprit.achat.services.Interface.OffrePService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/natureArticle")
@PreAuthorize("hasRole('User')")
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

        if(Objects.nonNull(n.getOffreProduit()) && Objects.nonNull(n.getOffreProduit().getId()) ) {
            OffreProduit offreProduit =  offrePService.retrieve(n.getOffreProduit().getId());

            n.setOffreProduit(offreProduit);

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


    @PostMapping("/affecter-unites")
    public ResponseEntity<NatureArticle> affecterUnitesAuxNaturesArticles() {
        natureArticleService.affecterUnitesAuxNaturesArticles();
        return ResponseEntity.ok().build();
    }


}