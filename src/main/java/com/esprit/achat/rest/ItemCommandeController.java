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
@RequestMapping("/itemcommande")
@PreAuthorize("hasRole('Operateur')")
@AllArgsConstructor
public class ItemCommandeController {
    private ItemCommandeService itemCommandeService;
    private CommandeService commandeService;
    private OffrePService offrePService;
    private OffreSService offreSService;

    @GetMapping
    List<ItemCommande> retrieveAll() { return itemCommandeService.retrieveAll();}

    @PostMapping("/add/{commandeId}")
    void add(@RequestBody ItemCommande i, @PathVariable ("commandeId")Integer commandeId) {

        if(Objects.nonNull(i.getOffreProduit()) && Objects.nonNull(i.getOffreProduit().getId()) &&  Objects.nonNull(i.getOffreService()) && Objects.nonNull(i.getOffreService().getId())) {
            OffreProduit offreProduit =  offrePService.retrieve(i.getOffreProduit().getId());
            OffreService offreService = offreSService.retrieve(i.getOffreService().getId());
            Commande commande = commandeService.retrieve(commandeId);
            i.setOffreProduit(offreProduit);
            i.setOffreService(offreService);
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

    @PostMapping("/calculate-tva")
    public ResponseEntity<String> calculateTva() {
        itemCommandeService.affecterTVAAuxItems();
        return ResponseEntity.ok("TVA affectée avec succès");
    }
}
