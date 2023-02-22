package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.AutreCharge;
import com.esprit.achat.persistence.entity.Commande;
import com.esprit.achat.persistence.entity.DemandeAchat;
import com.esprit.achat.persistence.entity.Facture;
import com.esprit.achat.services.Interface.AutreChargeService;
import com.esprit.achat.services.Interface.CommandeService;
import com.esprit.achat.services.Interface.DemandeAchatService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/demandeAchat")
@AllArgsConstructor
public class DemandeAchatController {
    private DemandeAchatService demandeAchatService;
    private AutreChargeService autreChargeService;

    @GetMapping
    List<DemandeAchat> retrieveAll(){
        return demandeAchatService.retrieveAll();
    }

    @PostMapping("/add")
    void add(@RequestBody DemandeAchat d){
        if(Objects.nonNull(d.getAutreCharge()) && Objects.nonNull(d.getAutreCharge().getId()) ) {
            AutreCharge autreCharge =  autreChargeService.retrieve(d.getAutreCharge().getId());
            d.setAutreCharge(autreCharge);
        }

        demandeAchatService.add(d);
    }

    @PutMapping("/edit")
    void update(@RequestBody DemandeAchat d){
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
}
