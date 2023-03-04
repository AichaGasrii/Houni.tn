package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.CodePromo;
import com.esprit.achat.persistence.entity.Commande;
import com.esprit.achat.services.Interface.CodePromoService;
import com.esprit.achat.services.Interface.CommandeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/codePromo")
@AllArgsConstructor
public class CodePromoController {
    private CodePromoService codePromoService;

    @GetMapping
    List<CodePromo> retrieveAll(){
        return codePromoService.retrieveAll();
    }

    @PostMapping("/add")
    void add(@RequestBody CodePromo c){
        codePromoService.add(c);
    }

    @PutMapping("/edit")
    void update(@RequestBody CodePromo c){
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
}
