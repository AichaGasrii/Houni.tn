package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.Commande;
import com.esprit.achat.persistence.entity.Unité;
import com.esprit.achat.services.Interface.CommandeService;
import com.esprit.achat.services.Interface.UnitéService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unite")
@AllArgsConstructor
public class UnitéController {
    @Autowired
    private UnitéService unitéService;

    @GetMapping
    List<Unité> retrieveAll(){
        return unitéService.retrieveAll();
    }

    @PostMapping("/add")
    void add(Unité u){
        unitéService.add(u);
    }

    @PutMapping("/edit")
    void update(Unité u){
        unitéService.update(u);
    }

    @DeleteMapping("/delete/{id}")
    void remove(Integer id){
        unitéService.remove(id);
    }

    @GetMapping("/{id}")
    Unité retrieve(Integer id){
        return unitéService.retrieve(id);
    }
}
