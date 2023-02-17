package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.Commande;
import com.esprit.achat.persistence.entity.DemandeAchat;
import com.esprit.achat.services.Interface.CommandeService;
import com.esprit.achat.services.Interface.DemandeAchatService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demandeAchat")
@AllArgsConstructor
public class DemandeAchatController {
    @Autowired
    private DemandeAchatService demandeAchatService;

    @GetMapping
    List<DemandeAchat> retrieveAll(){
        return demandeAchatService.retrieveAll();
    }

    @PostMapping("/add")
    void add(DemandeAchat d){
        demandeAchatService.add(d);
    }

    @PutMapping("/edit")
    void update(DemandeAchat d){
        demandeAchatService.update(d);
    }

    @DeleteMapping("/delete/{id}")
    void remove(Integer id){
        demandeAchatService.remove(id);
    }

    @GetMapping("/{id}")
    DemandeAchat retrieve(Integer id){
        return demandeAchatService.retrieve(id);
    }
}
