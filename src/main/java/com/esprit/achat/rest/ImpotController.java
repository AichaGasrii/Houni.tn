package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.Facture;
import com.esprit.achat.persistence.entity.Impot;
import com.esprit.achat.services.Implementation.ImpotServiceIMP;
import com.esprit.achat.services.Interface.FactureService;
import com.esprit.achat.services.Interface.ImpotService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/impot")
@AllArgsConstructor
public class ImpotController {
    private ImpotService impotService;

    @GetMapping
    List<Impot> retrieveAll(){
        return impotService.retrieveAll();
    }

    @PostMapping("/add")
    void add(@RequestBody Impot i){
        impotService.add(i);
    }

    @PutMapping("/edit")
    void update(@RequestBody Impot i){
        impotService.update(i);
    }

    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable("id") Integer id){
        impotService.remove(id);
    }

    @GetMapping("/{id}")
    Impot retrieve(@PathVariable("id") Integer id){
        return impotService.retrieve(id);
    }

}
