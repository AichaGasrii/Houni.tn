package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.AutreCharge;
import com.esprit.achat.persistence.entity.DemandeAchat;
import com.esprit.achat.services.Interface.AutreChargeService;
import com.esprit.achat.services.Interface.DemandeAchatService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/add")
    void add(@RequestBody AutreCharge c){
        autreChargeService.add(c);
    }

    @PutMapping("/edit")
    void update(@RequestBody AutreCharge c){
        autreChargeService.update(c);
    }

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
}