package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.Commande;
import com.esprit.achat.persistence.entity.Unité;
import com.esprit.achat.services.Interface.CommandeService;
import com.esprit.achat.services.Interface.UnitéService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unite")
@AllArgsConstructor
public class UnitéController {
    private UnitéService unitéService;

    @GetMapping
    List<Unité> retrieveAll(){
        return unitéService.retrieveAll();
    }

    @PostMapping("/add")
    void add(@RequestBody Unité u){
        unitéService.add(u);
    }

    @PutMapping("/edit")
    void update(@RequestBody Unité u){
        unitéService.update(u);
    }

    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable("id") Integer id){
        unitéService.remove(id);
    }

    @GetMapping("/{id}")
    Unité retrieve(@PathVariable("id") Integer id){
        return unitéService.retrieve(id);
    }
}
