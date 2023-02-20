package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.AppelOffre;
import com.esprit.achat.persistence.entity.Commande;
import com.esprit.achat.services.Interface.AppelOffreService;
import com.esprit.achat.services.Interface.CommandeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/appelOffre")
@AllArgsConstructor
public class AppelOffreController {
    private AppelOffreService appelOffreService;

    @GetMapping
    List<AppelOffre> retrieveAll(){
        return appelOffreService.retrieveAll();
    }

    @PostMapping("/add")
    @CrossOrigin
    void add(@RequestBody  AppelOffre a){
        appelOffreService.add(a);
    }

    @PutMapping("/edit")
    void update(AppelOffre a){
        appelOffreService.update(a);
    }

    @DeleteMapping("/delete/{id}")
    void remove(Integer id){
        appelOffreService.remove(id);
    }

    @GetMapping("/{id}")
    AppelOffre retrieve(Integer id){
        return appelOffreService.retrieve(id);
    }
}
