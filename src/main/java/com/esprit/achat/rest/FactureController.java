package com.esprit.achat.rest;

import com.esprit.achat.persistence.dto.ValidAdress;
import com.esprit.achat.persistence.entity.*;
import com.esprit.achat.persistence.enumeration.Etat;
import com.esprit.achat.repositories.FactureRepository;
import com.esprit.achat.services.Interface.FactureService;
import com.mysql.cj.xdevapi.Client;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/facture")
@PreAuthorize("hasRole('Fournisseur')")
@AllArgsConstructor
public class FactureController {

    private FactureService factureService;
    private FactureRepository factureRepository;

    @GetMapping
    List<Facture> retrieveAll(){
        return factureService.retrieveAll();
    }

    @PostMapping("/add")
    void add(@ValidAdress @RequestBody Facture f){
        factureService.add(f);
    }

    @PutMapping("/edit")
    void update(@ValidAdress @RequestBody Facture f){
        factureService.update(f);
    }

    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable("id") Integer id){
        factureService.remove(id);
    }

    @GetMapping("/{id}")
    Facture retrieve(@PathVariable("id") Integer id){
        return factureService.retrieve(id);
    }


    @PutMapping ("/calculermontantTTC/{factureId}")
    public Facture calculermontantTTC(@PathVariable ("factureId") Integer factureId){
        factureService.retrieve(factureId);
        Facture facture = factureService.retrieve(factureId);
        facture.setTotalttc(factureService.calculermontantTTC(facture));
        return factureRepository.save(facture);
    }

    @GetMapping("/nbFactureParClient/{client}")
    public Integer nbFactureParClient (@PathVariable Client client){
        return  factureService.nbFactureParClient(client);}

    @GetMapping("/items-facture/{factureId}")
    @ResponseStatus
    public List<ItemFacture> listeDesItemParFacture(@PathVariable Integer factureId) {
        return factureService.listeDesItemParFacture(factureId);
    }
}
