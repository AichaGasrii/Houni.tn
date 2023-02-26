package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.AppelOffre;
import com.esprit.achat.persistence.entity.NatureArticle;
import com.esprit.achat.persistence.entity.OffreProduit;
import com.esprit.achat.services.Interface.AppelOffreService;
import com.esprit.achat.services.Interface.OffrePService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/offreProduit")
@AllArgsConstructor
public class OffreProduitController {

    private OffrePService offreService;
    private AppelOffreService appelOffreService;

    @GetMapping
    List<OffreProduit> retrieveAll(){
        return offreService.retrieveAll();
    }

    @PostMapping("/add")
    void add(@RequestBody OffreProduit o){

        if(Objects.nonNull(o.getAppeloffre()) && Objects.nonNull(o.getAppeloffre().getId()) ) {
            AppelOffre appelOffre =  appelOffreService.retrieve(o.getAppeloffre().getId());
            o.setAppeloffre(appelOffre);
        }
        offreService.add(o);
    }

    @PutMapping("/edit")
    void update(@RequestBody OffreProduit o){
        offreService.update(o);
    }

    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable("id") Integer id){
        offreService.remove(id);
    }

    @GetMapping("/{id}")
    OffreProduit retrieve(@PathVariable("id") Integer id){
        return offreService.retrieve(id);
    }

    @GetMapping("/produit-nature/{nature}")
    public List<OffreProduit> listeDeproduitParNature(@PathVariable("nature") NatureArticle natureArticle) {
        return offreService.listeDeproduitParNature(natureArticle);
    }



   /* @PostMapping("/affecter-unites")
    public ResponseEntity<OffreProduit> affecterUnitesAuxNaturesArticles(@RequestBody OffreProduit o) {
        offreService.affecterUnitesAuxNaturesArticles(o);
        return ResponseEntity.ok().build();
    }
    */




}
