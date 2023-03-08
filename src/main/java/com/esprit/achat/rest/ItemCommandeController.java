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
@RequestMapping("/itemcommande")

@AllArgsConstructor
public class ItemCommandeController {
    private ItemCommandeService itemCommandeService;
    private CommandeService commandeService;
    private OffrePService offrePService;
    private OffreSService offreSService;

    @GetMapping
    List<ItemCommande> retrieveAll() { return itemCommandeService.retrieveAll();}
    @PreAuthorize("hasRole('Operateur')")
    @PostMapping("/add/{commandeId}")
    void add(@Valid @RequestBody ItemCommande i, @PathVariable ("commandeId")Integer commandeId) {

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
    @PreAuthorize("hasRole('Operateur')")
    @PutMapping("/edit")
    void update(@Valid @RequestBody ItemCommande i) {

        itemCommandeService.update(i);
    }
    @PreAuthorize("hasRole('Operateur')")
    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable("id") Integer id) {
        ItemCommande itemCommande = itemCommandeService.retrieve(id);
       /* if (itemCommande == null) {
            throw new IllegalArgumentException("Invalid item ID: " + id);
        }

        */
        Commande commande = itemCommande.getCommande();
        /*if (commande == null) {
            throw new IllegalStateException("Item " + id + " is not associated with any commande");
        }

         */
        commande.getItems().remove(itemCommande);
        commandeService.add(commande);

        itemCommandeService.remove(id);
    }

    @GetMapping("/{id}")
    ItemCommande retrieve(@PathVariable("id") Integer id) {
        return itemCommandeService.retrieve(id);
    }
    @PreAuthorize("hasRole('Operateur')")
    @PostMapping("/calculate-tva")
    public ResponseEntity<String> calculateTva() {
        itemCommandeService.affecterTVAAuxItems();
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
