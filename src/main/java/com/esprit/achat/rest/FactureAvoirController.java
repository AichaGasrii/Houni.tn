package com.esprit.achat.rest;

import com.esprit.achat.persistence.dto.ValidAdress;
import com.esprit.achat.persistence.entity.Facture;
import com.esprit.achat.persistence.entity.FactureAvoir;
import com.esprit.achat.repositories.FactureAvoirRepository;
import com.esprit.achat.services.Interface.FactureavoirService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    void add(@Valid @RequestBody FactureAvoir f){
        factureavoirService.add(f);
    }

    @PutMapping("/edit")
    void update(@Valid @RequestBody FactureAvoir f){
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
        factureAvoir.setTotalttc(factureavoirService.calculermontantTTC(factureAvoir));
        return factureAvoirRepository.save(factureAvoir);
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
