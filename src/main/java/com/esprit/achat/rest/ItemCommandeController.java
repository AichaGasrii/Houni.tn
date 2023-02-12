package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.ItemCommande;
import com.esprit.achat.persistence.entity.ItemFactureAvoir;
import com.esprit.achat.services.Interface.ItemCommandeService;
import com.esprit.achat.services.Interface.ItemFactureAvoirService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/itemcommander")
@AllArgsConstructor
public class ItemCommandeController {
    private ItemCommandeService itemCommandeService;

    @GetMapping
    List<ItemCommande> retrieveAll() {
        return itemCommandeService.retrieveAll();
    }

    @PostMapping("/add")
    void add(ItemCommande i) {
        itemCommandeService.add(i);
    }

    @PutMapping("/edit")
    void update(ItemCommande i) {
        itemCommandeService.update(i);
    }

    @DeleteMapping("/delete/{id}")
    void remove(Integer id) {
        itemCommandeService.remove(id);
    }

    @GetMapping("/{id}")
    ItemCommande retrieve(Integer id) {
        return itemCommandeService.retrieve(id);
    }
}
