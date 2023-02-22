package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.Reclamation;
import com.esprit.achat.services.Interface.ReclamationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/reclamation")
@AllArgsConstructor
public class ReclamationController {
    private ReclamationService reclamationService;
    @GetMapping
    List<Reclamation> retrieveAll(){

        return reclamationService.retrieveAll();
    }
    @PostMapping("/add")
    void add(@RequestBody Reclamation r){
        reclamationService.add(r);
    }
    @PutMapping("/edit")
    void update(@RequestBody Reclamation r){

        reclamationService.update(r);
    }
    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable("id") Integer id){

        reclamationService.remove(id);
    }
    @GetMapping("/{id}")
    Reclamation retrieve(@PathVariable("id") Integer id){

        return reclamationService.retrieve(id);
    }
}
