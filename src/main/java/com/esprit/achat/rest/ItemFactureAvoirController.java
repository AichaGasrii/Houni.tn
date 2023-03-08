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
@RequestMapping("/itemfactureavoir")
@PreAuthorize("hasRole('Fournisseur')")
@AllArgsConstructor
public class ItemFactureAvoirController {
    private ItemFactureAvoirService itemFactureAvoirService;
    private OffrePService offrePService;
    private OffreSService offreSService;
    private FactureavoirService factureavoirService;

    @GetMapping
    List<ItemFactureAvoir> retrieveAll() {
        return itemFactureAvoirService.retrieveAll();
    }

    @PostMapping("/add/{factureAvoirId}")
    void add(@Valid @RequestBody ItemFactureAvoir i, @PathVariable ("factureAvoirId") Integer factureAvoirId) {
        if(Objects.nonNull(i.getOffreProduit()) && Objects.nonNull(i.getOffreProduit().getId()) &&  Objects.nonNull(i.getOffreService()) && Objects.nonNull(i.getOffreService().getId())) {
            OffreProduit offreProduit =  offrePService.retrieve(i.getOffreProduit().getId());
            OffreService offreService = offreSService.retrieve(i.getOffreService().getId());
            FactureAvoir factureAvoir = factureavoirService.retrieve(factureAvoirId);
            i.setOffreProduit(offreProduit);
            i.setOffreService(offreService);
            i.setFactureAvoir(factureAvoir);
        }
        itemFactureAvoirService.add(i);
    }

    @PutMapping("/edit")
    void update(@RequestBody ItemFactureAvoir i) {
        itemFactureAvoirService.update(i);
    }

    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable("id") Integer id) {

     ItemFactureAvoir itemFactureAvoir = itemFactureAvoirService.retrieve(id);
       /* if (itemCommande == null) {
            throw new IllegalArgumentException("Invalid item ID: " + id);
        }

        */
        FactureAvoir factureAvoir = itemFactureAvoir.getFactureAvoir();
        /*if (commande == null) {
            throw new IllegalStateException("Item " + id + " is not associated with any commande");
        }

         */
        factureAvoir.getItems().remove(itemFactureAvoir);
        factureavoirService.add(factureAvoir);

        itemFactureAvoirService.remove(id);
        itemFactureAvoirService.remove(id);
    }


    @GetMapping("/{id}")
    ItemFactureAvoir retrieve(@PathVariable("id") Integer id) {
        return itemFactureAvoirService.retrieve(id);
    }

    @PostMapping("/calculate-tva")
    public ResponseEntity<String> calculateTva() {
        itemFactureAvoirService.affecterTVAAuxItems();
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
