package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.ItemFacture;
import com.esprit.achat.persistence.entity.ItemFactureAvoir;
import com.esprit.achat.services.Interface.ItemFactureAvoirService;
import com.esprit.achat.services.Interface.ItemFactureService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itemfactureavoir")
@AllArgsConstructor
public class ItemFactureAvoirController {
    private ItemFactureAvoirService itemFactureAvoirService;

    @GetMapping
    List<ItemFactureAvoir> retrieveAll() {
        return itemFactureAvoirService.retrieveAll();
    }

    @PostMapping("/add")
    void add(ItemFactureAvoir i) {
        itemFactureAvoirService.add(i);
    }

    @PutMapping("/edit")
    void update(ItemFactureAvoir i) {
        itemFactureAvoirService.update(i);
    }

    @DeleteMapping("/delete/{id}")
    void remove(Integer id) {
        itemFactureAvoirService.remove(id);
    }

    @GetMapping("/{id}")
    ItemFactureAvoir retrieve(Integer id) {
        return itemFactureAvoirService.retrieve(id);
    }
}
