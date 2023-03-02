package com.esprit.achat.rest;


import com.esprit.achat.persistence.entity.FactureAvoir;
import com.esprit.achat.persistence.entity.ItemFactureAvoir;
import com.esprit.achat.services.Interface.FactureavoirService;
import com.esprit.achat.services.Interface.ItemFactureAvoirService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/itemfactureavoir")
@AllArgsConstructor
public class ItemFactureAvoirController {
    private ItemFactureAvoirService itemFactureAvoirService;
    private FactureavoirService factureavoirService;


    @GetMapping
    List<ItemFactureAvoir> retrieveAll() {
        return itemFactureAvoirService.retrieveAll();
    }

    @PostMapping("/add")
    void add(@RequestBody ItemFactureAvoir i) {
        if(Objects.nonNull(i.getFactureAvoir()) && Objects.nonNull(i.getFactureAvoir().getId())) {

            FactureAvoir factureAvoir = factureavoirService.retrieve(i.getFactureAvoir().getId());

            i.setFactureAvoir(factureAvoir);

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
