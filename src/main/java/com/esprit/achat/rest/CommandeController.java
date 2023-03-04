package com.esprit.achat.rest;

import com.esprit.achat.persistence.dto.MontantPanier;
import com.esprit.achat.persistence.dto.Panier;
import com.esprit.achat.persistence.entity.*;
import com.esprit.achat.persistence.enumeration.Etat;
import com.esprit.achat.services.Interface.CommandeService;
import com.esprit.achat.services.Interface.FactureService;
import com.esprit.achat.services.Interface.ItemCommandeService;
import com.esprit.achat.services.Interface.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/commande")
@AllArgsConstructor
public class CommandeController {

    private CommandeService commandeService;

    private FactureService factureService;
    @PreAuthorize("hasRole('Operateur')")
    @GetMapping
    List<Commande> retrieveAll(){
        return commandeService.retrieveAll();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('Operateur')")
    void add(@Valid @RequestBody Commande c){

            if (Objects.nonNull(c.getFacture()) && Objects.nonNull(c.getFacture().getId())) {
                Facture facture = factureService.retrieve(c.getFacture().getId());
                c.setFacture(facture);
            }

        commandeService.add(c);
    }
    /*
     Facture facture = commande.getFacture();
    facture.setCommande(commande);
    commande.setFacture(facture);
    return commandeRepository.save(commande);
     */
    @PreAuthorize("hasRole('Operateur')")
    @PutMapping("/edit")
    void update(@Valid @RequestBody Commande c){
        commandeService.update(c);
    }
    @PreAuthorize("hasRole('Operateur')")
    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable("id") Integer id){
        commandeService.remove(id);
    }
    @PreAuthorize("hasRole('User')")
    @GetMapping("/{id}")
    Commande retrieve(@PathVariable("id") Integer id){
        return commandeService.retrieve(id);
    }
    @PreAuthorize("hasRole('Operateur')")
    @PostMapping("/montant-panier")
    MontantPanier montantPanier(@RequestBody Panier panier){
        return commandeService.calculMontantPanier(panier);
    }
    @PreAuthorize("hasRole('Operateur')")
    @GetMapping("/nbCommandeParEtat/{etat}")
    public Integer nbCommandeParEtat (@PathVariable Etat etat){
        return  commandeService.nbCommandeParEtat(etat);}


}
