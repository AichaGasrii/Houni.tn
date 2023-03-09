package com.esprit.achat.rest;



import com.esprit.achat.persistence.entity.*;
import com.esprit.achat.services.Interface.LivraisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    @PreAuthorize("hasRole('Operateur')")
    @PostMapping("/affecterFtoL/{factureId}/{livraisonId}")
    public ResponseEntity<?> affecterFactureLivraison(@PathVariable Integer factureId, @PathVariable Long livraisonId) {
        livraisonService.affecterFactureLivraison(factureId, livraisonId);
        return ResponseEntity.ok().build();
    }
    @PreAuthorize("hasRole('User')")
    @PostMapping("/planifierliv/{facture}/{livreur}/{typeLivraison}")
    public String planifierLivraison(@PathVariable Facture facture, @PathVariable Livreur livreur, @PathVariable String typeLivraison)
    {
        return livraisonService.planifierLivraison(facture,livreur,typeLivraison);
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
    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable("id") Long id) {
        livraisonService.remove(id);
    }
    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/{id}")
    Livraison retrieve(@PathVariable("id") Long id) {
        return livraisonService.retrieve(id);
    }
    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/getLivraison")
    List<Livraison> retrieveAll() {
        return livraisonService.retrieveAll();

    }
    @ControllerAdvice
    public class CommandeControllerAdvice {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ResponseBody
        public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
            Map<String, String> errors = new HashMap<>();
            ex.getBindingResult().getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            return errors;
        }
    }



}
