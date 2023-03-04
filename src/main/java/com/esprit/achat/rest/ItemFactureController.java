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
    private OffrePService offrePService;
    private OffreSService offreSService;
    private FactureService factureService;

    @GetMapping
    List<ItemFacture> retrieveAll(){
        return itemFactureService.retrieveAll();
    }

    @PostMapping("/add")
    void add(@RequestBody ItemFacture i){
        if(Objects.nonNull(i.getOffreProduit()) && Objects.nonNull(i.getOffreProduit().getId()) &&  Objects.nonNull(i.getOffreService()) && Objects.nonNull(i.getOffreService().getId())  &&  Objects.nonNull(i.getFacture()) && Objects.nonNull(i.getFacture().getId())) {
            OffreProduit offreProduit =  offrePService.retrieve(i.getOffreProduit().getId());
            OffreService offreService = offreSService.retrieve(i.getOffreService().getId());
            Facture facture = factureService.retrieve(i.getFacture().getId());
            i.setOffreProduit(offreProduit);
            i.setOffreService(offreService);
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
