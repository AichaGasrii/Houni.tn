package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.Devise;
import com.esprit.achat.persistence.entity.FactureAvoir;
import com.esprit.achat.services.Interface.DeviseService;
import com.esprit.achat.services.Interface.FactureService;
import com.esprit.achat.services.Interface.FactureavoirService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/factureavoir")
@AllArgsConstructor
public class FactureAvoirController {

    private FactureavoirService factureavoirService;

    @GetMapping
    List<FactureAvoir> retrieveAll(){
        return factureavoirService.retrieveAll();
    }

    @PostMapping("/add")
    void add(FactureAvoir f){
        factureavoirService.add(f);
    }

    @PutMapping("/edit")
    void update(FactureAvoir f){
        factureavoirService.update(f);
    }

    @DeleteMapping("/delete/{id}")
    void remove(Integer id){
        factureavoirService.remove(id);
    }

    @GetMapping("/{id}")
    FactureAvoir retrieve(Integer id){
        return factureavoirService.retrieve(id);
    }
}
