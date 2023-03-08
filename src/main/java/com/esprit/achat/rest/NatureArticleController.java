package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.*;
import com.esprit.achat.services.Interface.NatureArticleService;
import com.esprit.achat.services.Interface.OffrePService;
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
@RequestMapping("/natureArticle")
@PreAuthorize("hasRole('Operateur')")
@AllArgsConstructor
public class NatureArticleController {
    private NatureArticleService natureArticleService;
    private OffrePService offrePService;


    @GetMapping
    List<NatureArticle> retrieveAll(){
        return natureArticleService.retrieveAll();
    }

    @PostMapping("/add")
    void add(@Valid @RequestBody NatureArticle n){

        natureArticleService.add(n);
    }



    @PutMapping("/edit")
    void update(@Valid @RequestBody  NatureArticle n){
        natureArticleService.update(n);
    }

    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable("id") Integer id){
        natureArticleService.remove(id);
    }

    @GetMapping("/{id}")
    NatureArticle retrieve(@PathVariable("id") Integer id){
        return natureArticleService.retrieve(id);
    }


    @PostMapping("/affecter-unites")
    public ResponseEntity<NatureArticle> affecterUnitesAuxNaturesArticles() {
        natureArticleService.affecterUnitesAuxNaturesArticles();
        return ResponseEntity.ok().build();
    }

    @PutMapping("affecterOffresProduitsANatureArticle/{idOffre}/{idNature}")
    void affecterOffresProduitsANatureArticle(@PathVariable Integer idOffre, @PathVariable Integer idNature){
        natureArticleService.affecterOffresProduitsANatureArticle(idOffre,idNature);
    }

    @GetMapping("/produit-nature/{natureId}")
    @ResponseStatus
    public List<OffreProduit> listeDeproduitParNature(@PathVariable Integer natureId) {
        return natureArticleService.listeDeproduitParNature(natureId);
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