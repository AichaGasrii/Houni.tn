package com.esprit.achat.rest;

import com.esprit.achat.mail.EmailControllers;
import com.esprit.achat.persistence.entity.Commande;
import com.esprit.achat.persistence.entity.Rating;
import com.esprit.achat.persistence.entity.Reclamation;
import com.esprit.achat.persistence.entity.User;
import com.esprit.achat.services.Implementation.UserService;
import com.esprit.achat.services.Interface.CommandeService;
import com.esprit.achat.services.Interface.ReclamationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/reclamation")
@PreAuthorize("hasRole('User')")
@AllArgsConstructor
public class ReclamationController {
    @Autowired
    ReclamationService reclamationService;
    @Autowired
    CommandeService commandeService;
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

    void add(@RequestBody Reclamation r){

        if(Objects.nonNull(r.getCommande()) && Objects.nonNull(r.getCommande().getId())  && Objects.nonNull(r.getUser()) && Objects.nonNull(r.getUser().getUserName()) ) {
            Commande commande =  commandeService.retrieve(r.getCommande().getId());
            User user =  userService.retrieve(r.getUser().getUserName());
            r.setCommande(commande);
            r.setUser(user);
        }
        reclamationService.add(r);


            EC.ApplicationMail();



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
