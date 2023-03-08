package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.CodePromo;
import com.esprit.achat.persistence.entity.Commande;
import com.esprit.achat.services.Interface.CodePromoService;
import com.esprit.achat.services.Interface.CommandeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/codePromo")
@PreAuthorize("hasRole('Operateur')")
@AllArgsConstructor
public class CodePromoController {
    private CodePromoService codePromoService;

    @GetMapping
    List<CodePromo> retrieveAll(){
        return codePromoService.retrieveAll();
    }

    @PostMapping("/add")
    void add(@Valid @RequestBody CodePromo c){
        codePromoService.add(c);
    }

    @PutMapping("/edit")
    void update(@Valid @RequestBody CodePromo c){
        codePromoService.update(c);
    }

    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable("id") Integer id){
        codePromoService.remove(id);
    }

    @GetMapping("/{id}")
    CodePromo retrieve(@PathVariable("id") Integer id){
        return codePromoService.retrieve(id);
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
