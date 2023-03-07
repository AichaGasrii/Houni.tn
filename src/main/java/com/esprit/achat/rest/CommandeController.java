package com.esprit.achat.rest;

import com.esprit.achat.persistence.dto.MontantPanier;
import com.esprit.achat.persistence.dto.Panier;
import com.esprit.achat.persistence.dto.ValidAdress;
import com.esprit.achat.persistence.entity.*;
import com.esprit.achat.persistence.enumeration.Etat;
import com.esprit.achat.repositories.CommandeRepository;
import com.esprit.achat.services.Interface.CommandeService;
import com.esprit.achat.services.Interface.FactureService;
import com.esprit.achat.services.Interface.ItemCommandeService;
import com.esprit.achat.services.Interface.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
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

    private CommandeRepository commandeRepository;
    @PreAuthorize("hasRole('Operateur')")
    @GetMapping
    List<Commande> retrieveAll(){
        return commandeService.retrieveAll();
    }

    @PostMapping("/add")
    void add(@ValidAdress @RequestBody Commande c){

        if(Objects.nonNull(c.getFacture()) && Objects.nonNull(c.getFacture().getId()) ) {
            Facture facture =  factureService.retrieve(c.getFacture().getId());
            c.setFacture(facture);
        }

        commandeService.add(c);
    }
    @PreAuthorize("hasRole('Operateur')")
    @PutMapping("/edit")
    void update(@ValidAdress @RequestBody Commande c){
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
    @PutMapping ("/calculermontantTTC/{commandeId}")
    public Commande  calculermontantTTC(@PathVariable ("commandeId") Integer commandeId){
        commandeService.retrieve(commandeId);
        Commande commande = commandeService.retrieve(commandeId);
        commande.setTotalttc(commandeService.calculermontantTTC(commande));


        return commandeRepository.save(commande);
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
    @PreAuthorize("hasRole('Operateur')")
    @PostMapping("/affecter-devise")
    public ResponseEntity<Commande> affecterDeviseAuxCommandes() {
        commandeService.affecterDeviseAuxCommandes();
        return ResponseEntity.ok().build();
    }
    @PreAuthorize("hasRole('Operateur')")
    @GetMapping("/items-commande/{commandeId}")
    @ResponseStatus
    public List<ItemCommande> listeDesItemParCommande(@PathVariable Integer commandeId) {
        return commandeService.listeDesItemParCommande(commandeId);
    }

}
