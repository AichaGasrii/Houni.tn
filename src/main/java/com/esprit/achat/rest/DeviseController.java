package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.Commande;
import com.esprit.achat.persistence.entity.Devise;
import com.esprit.achat.services.Interface.CommandeService;
import com.esprit.achat.services.Interface.DeviseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devise")
@AllArgsConstructor
public class DeviseController {

    private DeviseService deviseService;

    @GetMapping
    List<Devise> retrieveAll(){
        return deviseService.retrieveAll();
    }

    @PostMapping("/add")
    void add(Devise d){
        deviseService.add(d);
    }

    @PutMapping("/edit")
    void update(Devise d){
        deviseService.update(d);
    }

    @DeleteMapping("/delete/{id}")
    void remove(Integer id){
        deviseService.remove(id);
    }

    @GetMapping("/{id}")
    Devise retrieve(Integer id){
        return deviseService.retrieve(id);
    }

}
