package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.ItemFacture;
import com.esprit.achat.persistence.entity.Paiement;
import com.esprit.achat.services.Interface.ItemFactureService;
import com.esprit.achat.services.Interface.PaiementService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itemfacture")
@AllArgsConstructor
public class ItemFactureController {
    private ItemFactureService itemFactureService;

    @GetMapping
    List<ItemFacture> retrieveAll(){
        return itemFactureService.retrieveAll();
    }

    @PostMapping("/add")
    void add(ItemFacture i){
        itemFactureService.add(i);
    }

    @PutMapping("/edit")
    void update(ItemFacture i){
        itemFactureService.update(i);
    }

    @DeleteMapping("/delete/{id}")
    void remove(Integer id){
        itemFactureService.remove(id);
    }

    @GetMapping("/{id}")
    ItemFacture retrieve(Integer id){
        return itemFactureService.retrieve(id);
    }
}
