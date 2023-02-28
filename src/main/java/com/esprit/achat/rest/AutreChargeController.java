package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.AutreCharge;
import com.esprit.achat.persistence.entity.Commande;
import com.esprit.achat.services.Interface.AutreChargeService;
import com.esprit.achat.services.Interface.CommandeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autreCharge")
@AllArgsConstructor
public class AutreChargeController {
    private AutreChargeService autreChargeService;

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
}
