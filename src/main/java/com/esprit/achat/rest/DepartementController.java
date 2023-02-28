package com.esprit.achat.rest;


import com.esprit.achat.persistence.entity.Departement;
import com.esprit.achat.services.Interface.DepartementService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Departement")
@AllArgsConstructor
public class DepartementController {
    
    private static DepartementService departementService ;
    @GetMapping
    List<Departement> retrieveAll(){
        return departementService.retrieveAll();
    }

    @PostMapping("/add")
    void add(@RequestBody Departement D){
        departementService.add(D);
    }

    @PutMapping("/edit")
    void update(@RequestBody Departement D){
        departementService.update(D);
    }

    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable("id") Integer id){ departementService.remove(id);}

    @GetMapping("/{id}")
    Departement retrieve(@PathVariable("id") Integer id) { return departementService.retrieve(id) ;}


    
    
}
