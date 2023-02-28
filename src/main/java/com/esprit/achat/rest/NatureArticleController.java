package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.Commande;
import com.esprit.achat.persistence.entity.NatureArticle;
import com.esprit.achat.services.Interface.CommandeService;
import com.esprit.achat.services.Interface.NatureArticleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/natureArticle")
@AllArgsConstructor
public class NatureArticleController {
    private NatureArticleService natureArticleService;

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
}
