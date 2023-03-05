package com.esprit.achat.rest;

import com.esprit.achat.persistence.dto.ValidAdress;
import com.esprit.achat.persistence.entity.Facture;
import com.esprit.achat.persistence.entity.FactureAvoir;
import com.esprit.achat.repositories.FactureAvoirRepository;
import com.esprit.achat.services.Interface.FactureavoirService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/factureavoir")
@PreAuthorize("hasRole('Fournisseur')")
@AllArgsConstructor
public class FactureAvoirController {

    private FactureavoirService factureavoirService;
    private FactureAvoirRepository factureAvoirRepository;

    @GetMapping
    List<FactureAvoir> retrieveAll(){
        return factureavoirService.retrieveAll();
    }

    @PostMapping("/add")
    void add(@ValidAdress @RequestBody FactureAvoir f){
        factureavoirService.add(f);
    }

    @PutMapping("/edit")
    void update(@ValidAdress @RequestBody FactureAvoir f){
        factureavoirService.update(f);
    }

    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable("id") Integer id){
        factureavoirService.remove(id);
    }

    @GetMapping("/{id}")
    FactureAvoir retrieve(@PathVariable("id") Integer id){
        return factureavoirService.retrieve(id);
    }

    @PutMapping ("/calculermontantTTC/{factureAvoirId}")
    public FactureAvoir calculermontantTTC(@PathVariable ("factureAvoirId") Integer factureAvoirId){
        factureavoirService.retrieve(factureAvoirId);
        FactureAvoir factureAvoir = factureavoirService.retrieve(factureAvoirId);
        factureAvoir.setRemboursement(factureavoirService.calculermontantTTC(factureAvoir));
        return factureAvoirRepository.save(factureAvoir);
    }


}
