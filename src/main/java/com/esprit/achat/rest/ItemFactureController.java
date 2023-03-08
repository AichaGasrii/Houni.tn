package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.*;
import com.esprit.achat.services.Interface.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    void add(@Valid @RequestBody ItemFacture i, @PathVariable ("factureId") Integer factureId) {
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
    void update(@Valid @RequestBody ItemFacture i){
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
