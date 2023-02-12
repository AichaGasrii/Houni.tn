package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.Devise;
import com.esprit.achat.persistence.entity.Facture;
import com.esprit.achat.services.Interface.DeviseService;
import com.esprit.achat.services.Interface.FactureService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/facture")
@AllArgsConstructor
public class FactureController {

    private FactureService factureService;

    @GetMapping
    List<Facture> retrieveAll(){
        return factureService.retrieveAll();
    }

    @PostMapping("/add")
    void add(Facture f){
        factureService.add(f);
    }

    @PutMapping("/edit")
    void update(Facture f){
        factureService.update(f);
    }

    @DeleteMapping("/delete/{id}")
    void remove(Integer id){
        factureService.remove(id);
    }

    @GetMapping("/{id}")
    Facture retrieve(Integer id){
        return factureService.retrieve(id);
    }
}
