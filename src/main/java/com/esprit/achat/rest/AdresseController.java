package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.Adresse;
import com.esprit.achat.services.Interface.AdresseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Adresse")
@AllArgsConstructor
public class AdresseController {

    private AdresseService adresseService ;
    @GetMapping
    List<Adresse> retrieveAll(){
        return adresseService.retrieveAll();
    }

    @PostMapping("/add")
    void add(@RequestBody Adresse A){
        adresseService.add(A);
    }

    @PutMapping("/edit")
    void update(@RequestBody Adresse A){
        adresseService.update(A);
    }

    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable("id") Integer id){ adresseService.remove(id);}

    @GetMapping("/{id}")
    Adresse retrieve(@PathVariable("id") Integer id) { return adresseService.retrieve(id) ;}

}
