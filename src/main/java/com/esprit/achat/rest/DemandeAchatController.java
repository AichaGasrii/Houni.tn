package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.AutreCharge;
import com.esprit.achat.persistence.entity.DemandeAchat;
import com.esprit.achat.services.Interface.AutreChargeService;
import com.esprit.achat.services.Interface.DemandeAchatService;
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
import java.util.Objects;

@RestController
@RequestMapping("/demandeAchat")
@PreAuthorize("hasRole('User')")
@AllArgsConstructor
public class DemandeAchatController {
    private DemandeAchatService demandeAchatService;
    private AutreChargeService autreChargeService;

    @GetMapping
    List<DemandeAchat> retrieveAll(){
        return demandeAchatService.retrieveAll();
    }

    @PostMapping("/add")
    void add(@Valid @RequestBody DemandeAchat d){
        if(Objects.nonNull(d.getAutreCharge()) && Objects.nonNull(d.getAutreCharge().getId()) ) {
            AutreCharge autreCharge =  autreChargeService.retrieve(d.getAutreCharge().getId());
            d.setAutreCharge(autreCharge);
        }
        demandeAchatService.add(d);
    }

    @PutMapping("/edit")
    void update(@Valid @RequestBody DemandeAchat d){
        demandeAchatService.update(d);
    }

    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable("id") Integer id){
        demandeAchatService.remove(id);
    }

    @GetMapping("/{id}")
    DemandeAchat retrieve(@PathVariable("id") Integer id){
        return demandeAchatService.retrieve(id);
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