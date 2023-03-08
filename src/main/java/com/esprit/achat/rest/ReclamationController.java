package com.esprit.achat.rest;

import com.esprit.achat.mail.EmailControllers;
import com.esprit.achat.persistence.entity.Commande;
import com.esprit.achat.persistence.entity.Reclamation;
import com.esprit.achat.persistence.entity.User;
import com.esprit.achat.repositories.CommandeRepository;
import com.esprit.achat.services.Implementation.UserService;
import com.esprit.achat.services.Interface.CommandeService;
import com.esprit.achat.services.Interface.ReclamationService;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.apache.commons.validator.routines.EmailValidator;

@RestController
@RequestMapping("/reclamation")
@AllArgsConstructor
public class ReclamationController {
    @Autowired
    ReclamationService reclamationService;
    @Autowired
    CommandeService commandeService;
    @Autowired
    CommandeRepository commandeRepository;
     @Autowired
    UserService userService;
    @Autowired
    EmailControllers EC;
    @GetMapping
    @ResponseBody
    List<Reclamation> retrieveAll(){

        return reclamationService.retrieveAll();
    }
    @PostMapping("/add")
    ResponseEntity<String> add(@RequestBody Reclamation r){
        String message;
        Commande c= r.getCommande();
        Integer d=c.getId();
        Optional<Commande> cc= commandeRepository.findById(d);
        LocalDate f=cc.get().getDateCreation();
        LocalDate currentDate = LocalDate.now();
        Date l = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date s = Date.from(f.atStartOfDay(ZoneId.systemDefault()).toInstant());
        long diffInMillies = Math.abs(l.getTime() - s.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        if (diff>60){
            message="desole vous ne pouvez pas faire une reclamation a cette commande car vous avez depassé 60 jours";


            return ResponseEntity.ok(message);
            }
    else {
        if(Objects.nonNull(r.getCommande()) && Objects.nonNull(r.getCommande().getId())  && Objects.nonNull(r.getUser()) && Objects.nonNull(r.getUser().getUserName()) ) {
            Commande commande =  commandeService.retrieve(r.getCommande().getId());
            User user =  userService.retrieve(r.getUser().getUserName());
            r.setCommande(commande);
            r.setUser(user);
        }
            LocalDate q = LocalDate.now();
            Date qq = Date.from(q.atStartOfDay(ZoneId.systemDefault()).toInstant());
            r.setDate(qq);
            EmailValidator validator = EmailValidator.getInstance();
             String email =r.getEmail();
            boolean isValid = validator.isValid(email);
           if (isValid) {
                reclamationService.add(r);

                EC.ApplicationMail();
                message="votre reclamation a été envoyée";
                return ResponseEntity.ok(message);
            } else {
                message="votre adresse n'est pas valide";
                return ResponseEntity.ok(message);
          }




       }

    }
    @PutMapping("/edit/{id}")
    @ResponseBody
    Reclamation update(@RequestBody Reclamation r,@PathVariable("id") Integer id){

        return reclamationService.updateReclamation(id, r);
    }
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    void remove(@PathVariable("id") Integer id){

        reclamationService.remove(id);
    }
    @GetMapping("/{id}")
    @ResponseBody
    Reclamation retrieve(@PathVariable("id") Integer id){
        List<String> interdit = Arrays.asList("mot1", "mot2", "mot3");
        Reclamation reclamation= reclamationService.retrieve(id);
        String censoredreclamation  = reclamation.getDescription();
        for (String motInterdit : interdit) {
           String ss=  censoredreclamation.replaceAll("mot3", "***");
            reclamation.setDescription(ss);



        }

        return reclamation;




    }
    @GetMapping("/getReclamationByUser/{name}")
    @ResponseBody

    public List<Reclamation> getReclamationByUser (@PathVariable("name") String name)
    {
        return reclamationService.getReclamationByUser(name);
    }


}
