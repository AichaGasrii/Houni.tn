package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.Commande;
import com.esprit.achat.persistence.entity.Offre;
import com.esprit.achat.services.Interface.CommandeService;
import com.esprit.achat.services.Interface.OffreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offre")
@AllArgsConstructor
public class OffreController {
    private OffreService offreService;

    @GetMapping
    List<Offre> retrieveAll(){
        return offreService.retrieveAll();
    }

    @PostMapping("/add")
    void add(Offre o){
        offreService.add(o);
    }

    @PutMapping("/edit")
    void update(Offre o){
        offreService.update(o);
    }

    @DeleteMapping("/delete/{id}")
    void remove(Integer id){
        offreService.remove(id);
    }

    @GetMapping("/{id}")
    Offre retrieve(Integer id){
        return offreService.retrieve(id);
    }
}
