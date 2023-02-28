package com.esprit.achat.rest;

import com.esprit.achat.persistence.dto.MontantPanier;
import com.esprit.achat.persistence.dto.Panier;
import com.esprit.achat.persistence.entity.Commande;
import com.esprit.achat.persistence.entity.Facture;
import com.esprit.achat.persistence.entity.Question;
import com.esprit.achat.services.Interface.CommandeService;
import com.esprit.achat.services.Interface.FactureService;
import com.esprit.achat.services.Interface.ItemCommandeService;
import com.esprit.achat.services.Interface.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/commande")
@AllArgsConstructor
public class CommandeController {

    private CommandeService commandeService;

    private FactureService factureService;

    @GetMapping
    List<Commande> retrieveAll(){
        return commandeService.retrieveAll();
    }

    @PostMapping("/add")
    void add(@RequestBody Commande c){

        if(Objects.nonNull(c.getFacture()) && Objects.nonNull(c.getFacture().getId()) ) {
            Facture facture =  factureService.retrieve(c.getFacture().getId());
            c.setFacture(facture);
        }

        commandeService.add(c);
    }

    @PutMapping("/edit")
    void update(@RequestBody Commande c){
        commandeService.update(c);
    }

    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable("id") Integer id){
        commandeService.remove(id);
    }

    @GetMapping("/{id}")
    Commande retrieve(@PathVariable("id") Integer id){
        return commandeService.retrieve(id);
    }

    @PostMapping("/montant-panier")
    MontantPanier montantPanier(@RequestBody Panier panier){
        return commandeService.calculMontantPanier(panier);
    }

}
