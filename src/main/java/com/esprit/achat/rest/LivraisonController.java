package com.esprit.achat.rest;



import com.esprit.achat.persistence.entity.*;
import com.esprit.achat.services.Interface.LivraisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/Livraison")

public class LivraisonController {

    @Autowired
    private LivraisonService livraisonService;
    @PreAuthorize("hasRole('User')")
     @PostMapping("/RecLivraison/{livraison}")
    public String ReclamationLivraison(@PathVariable Long livraison)
    {
       return livraisonService.ReclamationLivraison( livraison);
    }
    @PreAuthorize("hasRole('User')")
    @GetMapping("/livreurplusProche/{latitude}/{longitude}")
    public Livreur  getLivreurLePlusProche(@PathVariable Double latitude,@PathVariable Double longitude)
    {
        return livraisonService.getLivreurLePlusProche(latitude, longitude);
    }


    @PostMapping("/affecterFtoL/{factureId}/{livraisonId}")
    public ResponseEntity<?> affecterFactureLivraison(@PathVariable Integer factureId, @PathVariable Long livraisonId) {
        livraisonService.affecterFactureLivraison(factureId, livraisonId);
        return ResponseEntity.ok().build();
    }
    @PreAuthorize("hasRole('User')")
    @PostMapping("/planifierliv/{facture}/{livreur}")
    public String planifierLivraison(@PathVariable Facture facture, @PathVariable Livreur livreur)
    {
        return livraisonService.planifierLivraison(facture,livreur);
    }
    @PreAuthorize("hasRole('User')")
    @PostMapping("/Annuler/{id}")
    public void annulerLivraison(@PathVariable Long id)
    {
         livraisonService.annulerLivraison(id);
    }
    @PreAuthorize("hasRole('User')")
    @PutMapping("/edit")
    void update(@RequestBody Livraison n) {
        livraisonService.update(n);
    }
    @PreAuthorize("hasRole('User')")
    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable("id") Long id) {
        livraisonService.remove(id);
    }
    @PreAuthorize("hasRole('User')")
    @GetMapping("/{id}")
    Livraison retrieve(@PathVariable("id") Long id) {
        return livraisonService.retrieve(id);
    }
    @PreAuthorize("hasRole('User')")
    @GetMapping("/getLivraison")
    List<Livraison> retrieveAll() {
        return livraisonService.retrieveAll();

    }



}
