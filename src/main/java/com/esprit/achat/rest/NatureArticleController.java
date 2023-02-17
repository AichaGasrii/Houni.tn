package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.Commande;
import com.esprit.achat.persistence.entity.NatureArticle;
import com.esprit.achat.services.Interface.CommandeService;
import com.esprit.achat.services.Interface.NatureArticleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/natureArticle")
@AllArgsConstructor
public class NatureArticleController {
    @Autowired
    private NatureArticleService natureArticleService;

    @GetMapping
    List<NatureArticle> retrieveAll(){
        return natureArticleService.retrieveAll();
    }

    @PostMapping("/add")
    void add(NatureArticle n){
        natureArticleService.add(n);
    }

    @PutMapping("/edit")
    void update(NatureArticle n){
        natureArticleService.update(n);
    }

    @DeleteMapping("/delete/{id}")
    void remove(Integer id){
        natureArticleService.remove(id);
    }

    @GetMapping("/{id}")
    NatureArticle retrieve(Integer id){
        return natureArticleService.retrieve(id);
    }
}
