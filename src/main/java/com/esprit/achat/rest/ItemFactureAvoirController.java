package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.ItemFacture;
import com.esprit.achat.persistence.entity.ItemFactureAvoir;
import com.esprit.achat.persistence.entity.OffreProduit;
import com.esprit.achat.persistence.entity.OffreService;
import com.esprit.achat.services.Interface.ItemFactureAvoirService;
import com.esprit.achat.services.Interface.ItemFactureService;
import com.esprit.achat.services.Interface.OffrePService;
import com.esprit.achat.services.Interface.OffreSService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/itemfactureavoir")
@AllArgsConstructor
public class ItemFactureAvoirController {
    private ItemFactureAvoirService itemFactureAvoirService;
    private OffrePService offrePService;
    private OffreSService offreSService;

    @GetMapping
    List<ItemFactureAvoir> retrieveAll() {
        return itemFactureAvoirService.retrieveAll();
    }

    @PostMapping("/add")
    void add(@RequestBody ItemFactureAvoir i) {
        if(Objects.nonNull(i.getOffreProduit()) && Objects.nonNull(i.getOffreProduit().getId()) &&  Objects.nonNull(i.getOffreService()) && Objects.nonNull(i.getOffreService().getId())) {
            OffreProduit offreProduit =  offrePService.retrieve(i.getOffreProduit().getId());
            OffreService offreService = offreSService.retrieve(i.getOffreService().getId());
            i.setOffreProduit(offreProduit);
            i.setOffreService(offreService);
        }
        itemFactureAvoirService.add(i);
    }

    @PutMapping("/edit")
    void update(@RequestBody ItemFactureAvoir i) {
        itemFactureAvoirService.update(i);
    }

    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable("id") Integer id) {
        itemFactureAvoirService.remove(id);
    }

    @GetMapping("/{id}")
    ItemFactureAvoir retrieve(@PathVariable("id") Integer id) {
        return itemFactureAvoirService.retrieve(id);
    }
}
