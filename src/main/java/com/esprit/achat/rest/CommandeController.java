package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.Commande;
import com.esprit.achat.persistence.entity.Question;
import com.esprit.achat.services.Interface.CommandeService;
import com.esprit.achat.services.Interface.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commande")
@AllArgsConstructor
public class CommandeController {

    private CommandeService commandeService;

    @GetMapping
    List<Commande> retrieveAll(){
        return commandeService.retrieveAll();
    }

    @PostMapping("/add")
    void add(Commande c){
        commandeService.add(c);
    }

    @PutMapping("/edit")
    void update(Commande c){
        commandeService.update(c);
    }

    @DeleteMapping("/delete/{id}")
    void remove(Integer id){
        commandeService.remove(id);
    }

    @GetMapping("/{id}")
    Commande retrieve(Integer id){
        return commandeService.retrieve(id);
    }
}
