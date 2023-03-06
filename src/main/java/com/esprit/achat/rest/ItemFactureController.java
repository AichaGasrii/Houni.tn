package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.*;
import com.esprit.achat.services.Interface.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/itemfacture")
@PreAuthorize("hasRole('Fournisseur')")
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

    @PostMapping("/add/{factureId}")
    void add(@RequestBody ItemFacture i, @PathVariable ("factureId") Integer factureId) {
        if(Objects.nonNull(i.getOffreProduit()) && Objects.nonNull(i.getOffreProduit().getId()) &&  Objects.nonNull(i.getOffreService()) && Objects.nonNull(i.getOffreService().getId())) {
            OffreProduit offreProduit =  offrePService.retrieve(i.getOffreProduit().getId());
            OffreService offreService = offreSService.retrieve(i.getOffreService().getId());
            Facture facture = factureService.retrieve(factureId);
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


        ItemFacture itemFacture = itemFactureService.retrieve(id);
       /* if (itemCommande == null) {
            throw new IllegalArgumentException("Invalid item ID: " + id);
        }

        */
        Facture facture = itemFacture.getFacture();
        /*if (commande == null) {
            throw new IllegalStateException("Item " + id + " is not associated with any commande");
        }

         */
        facture.getItems().remove(itemFacture);
        factureService.add(facture);

        itemFactureService.remove(id);
        itemFactureService.remove(id);
        itemFactureService.remove(id);
    }

    @GetMapping("/{id}")
    ItemFacture retrieve(@PathVariable("id") Integer id){
        return itemFactureService.retrieve(id);
    }

    @PostMapping("/calculate-tva")
    public ResponseEntity<String> calculateTva() {
        itemFactureService.affecterTVAAuxItems();
        return ResponseEntity.ok("TVA affectée avec succès");
    }
}
