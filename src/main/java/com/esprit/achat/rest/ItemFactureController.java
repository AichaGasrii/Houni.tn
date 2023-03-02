package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.*;
import com.esprit.achat.services.Interface.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/itemfacture")
@AllArgsConstructor
public class ItemFactureController {
    private ItemFactureService itemFactureService;
    private FactureService factureService;

    @GetMapping
    List<ItemFacture> retrieveAll(){
        return itemFactureService.retrieveAll();
    }

    @PostMapping("/add")
    void add(@RequestBody ItemFacture i){
        if(Objects.nonNull(i.getFacture()) && Objects.nonNull(i.getFacture().getId())) {

            Facture facture = factureService.retrieve(i.getFacture().getId());

            i.setFacture(facture);

        }


        itemFactureService.add(i);
    }

    @PutMapping("/edit")
    void update(@RequestBody ItemFacture i){
        itemFactureService.update(i);
    }

    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable("id") Integer id){
        itemFactureService.remove(id);
    }

    @GetMapping("/{id}")
    ItemFacture retrieve(@PathVariable("id") Integer id){
        return itemFactureService.retrieve(id);
    }
}
