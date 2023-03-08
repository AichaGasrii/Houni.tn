package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.AutreCharge;
import com.esprit.achat.persistence.entity.DemandeAchat;
import com.esprit.achat.persistence.enumeration.ECharge;
import com.esprit.achat.persistence.enumeration.Etat;
import com.esprit.achat.services.Interface.AutreChargeService;
import com.esprit.achat.services.Interface.DemandeAchatService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/autreCharge")

@AllArgsConstructor
public class AutreChargeController {
    @Autowired
    AutreChargeService autreChargeService;
    @Autowired
    DemandeAchatService demandeAchatService;

    @GetMapping
    List<AutreCharge> retrieveAll(){
        return autreChargeService.retrieveAll();
    }
    @PreAuthorize("hasRole('Fournisseur')")
    @PostMapping("/add")
    void add(@Valid @RequestBody AutreCharge c){

        autreChargeService.add(c);
    }
    @PreAuthorize("hasRole('Fournisseur')")
    @PutMapping("/edit")
    void update(@Valid @RequestBody AutreCharge c){
        autreChargeService.update(c);
    }
    @PreAuthorize("hasRole('Fournisseur')")
    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable("id") Integer id){
        autreChargeService.remove(id);
    }

    @GetMapping("/{id}")
    AutreCharge retrieve(@PathVariable("id") Integer id){
        return autreChargeService.retrieve(id);
    }

    @GetMapping("/{autreChargeId}/demandeAchats")
    @ResponseStatus
    public List<DemandeAchat> getAllDemandesAchatByAutreCharge(@PathVariable Integer autreChargeId) {
        return autreChargeService.getAllDemandesAchatByAutreCharge(autreChargeId);
    }

    @GetMapping("/demandes-achat/autre-charge/{eCharge}")
    public ResponseEntity<?> getDemandesAchatByECharge(@PathVariable String eCharge) {
        try {
            List<DemandeAchat> demandes = autreChargeService.getDemandesAchatByECharge(eCharge);
            return ResponseEntity.ok(demandes);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body("Invalid eCharge value");
        }
    }

    @GetMapping("/nbChargeParECharge/{echarge}")
    public Integer nbChargeParECharge (@PathVariable ECharge echarge){
        return  autreChargeService.nbChargeParECharge(echarge);}

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