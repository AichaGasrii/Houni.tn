package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.OffreProduit;
import com.esprit.achat.services.Interface.OffrePService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offreProduit")
@AllArgsConstructor
public class OffreProduitController {
    @Autowired
    private OffrePService offreService;

    @GetMapping
    List<OffreProduit> retrieveAll(){
        return offreService.retrieveAll();
    }

    @PostMapping("/add")
    void add(OffreProduit o){
        offreService.add(o);
    }

    @PutMapping("/edit")
    void update(OffreProduit o){
        offreService.update(o);
    }

    @DeleteMapping("/delete/{id}")
    void remove(Integer id){
        offreService.remove(id);
    }

    @GetMapping("/{id}")
    OffreProduit retrieve(Integer id){
        return offreService.retrieve(id);
    }
}
