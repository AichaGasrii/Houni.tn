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
    void add(@RequestBody Devise d){
        deviseService.add(d);
    }

    @PutMapping("/edit")
    void update(@RequestBody Devise d){
        deviseService.update(d);
    }

    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable("id") Integer id){
        deviseService.remove(id);
    }

    @GetMapping("/{id}")
    Devise retrieve(@PathVariable("id") Integer id){
        return deviseService.retrieve(id);
    }

}
