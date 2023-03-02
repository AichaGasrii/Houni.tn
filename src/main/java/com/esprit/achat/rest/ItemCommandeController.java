package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.*;
import com.esprit.achat.services.Interface.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/itemcommande")
@AllArgsConstructor
public class ItemCommandeController {
    private ItemCommandeService itemCommandeService;
    private CommandeService commandeService;


    @GetMapping
    List<ItemCommande> retrieveAll() {
        return itemCommandeService.retrieveAll();
    }

    @PostMapping("/add")
    void add(@RequestBody ItemCommande i) {

        if( Objects.nonNull(i.getCommande()) && Objects.nonNull(i.getCommande().getId())) {
            Commande commande = commandeService.retrieve(i.getCommande().getId());

            i.setCommande(commande);

        }

        itemCommandeService.add(i);
    }

    @PutMapping("/edit")
    void update(@RequestBody ItemCommande i) {
        itemCommandeService.update(i);
    }

    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable("id") Integer id) {
        itemCommandeService.remove(id);
    }

    @GetMapping("/{id}")
    ItemCommande retrieve(@PathVariable("id") Integer id) {
        return itemCommandeService.retrieve(id);
    }
}
