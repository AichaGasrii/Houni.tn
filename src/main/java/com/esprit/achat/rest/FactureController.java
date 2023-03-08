package com.esprit.achat.rest;

import com.esprit.achat.persistence.dto.ValidAdress;
import com.esprit.achat.persistence.dto.ValidCountry;
import com.esprit.achat.persistence.entity.*;
import com.esprit.achat.persistence.enumeration.Etat;
import com.esprit.achat.repositories.FactureRepository;
import com.esprit.achat.services.Interface.FactureService;
import com.mysql.cj.xdevapi.Client;
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
    void add(@Valid @RequestBody Facture f){



        factureService.add(f);
    }

    @PutMapping("/edit")
    void update(@Valid @RequestBody Facture f){

        factureService.update(f);
    }

    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable("id") Integer id){

        factureService.remove(id);
    }

    @GetMapping("/{id}")
    Facture retrieve(@PathVariable("id") Integer id){
        return
                factureService.retrieve(id);
    }


    @PutMapping ("/calculermontantTTC/{factureId}")
    public Facture calculermontantTTC(@PathVariable ("factureId") Integer factureId){
        factureService.retrieve(factureId);
        Facture facture = factureService.retrieve(factureId);
        facture.setTotalttc(factureService.calculermontantTTC(facture));
        return factureRepository.save(facture);
    }


    @GetMapping("/items-facture/{factureId}")
    @ResponseStatus
    public List<ItemFacture> listeDesItemParFacture(@PathVariable Integer factureId) {
        return factureService.listeDesItemParFacture(factureId);
    }

    @PostMapping("/affecter-devise")
    public ResponseEntity<Facture> affecterDeviseAuxFactures() {
        factureService.affecterDeviseAuxFactures();
        return ResponseEntity.ok().build();
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
