package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.Devise;
import com.esprit.achat.persistence.entity.Paiement;
import com.esprit.achat.services.Interface.DeviseService;
import com.esprit.achat.services.Interface.PaiementService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paiement")
@AllArgsConstructor
public class PaiementController {

    private PaiementService paiementService;

    @GetMapping
    List<Paiement> retrieveAll(){
        return paiementService.retrieveAll();
    }

    @PostMapping("/add")
    void add(Paiement p){
        paiementService.add(p);
    }

    @PutMapping("/edit")
    void update(Paiement p){
        paiementService.update(p);
    }

    @DeleteMapping("/delete/{id}")
    void remove(Integer id){
        paiementService.remove(id);
    }

    @GetMapping("/{id}")
    Paiement retrieve(Integer id){
        return paiementService.retrieve(id);
    }
}
