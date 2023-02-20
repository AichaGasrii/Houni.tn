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
    void add(Adresse A){
        adresseService.add(A);
    }

    @PutMapping("/edit")
    void update(Adresse A){
        adresseService.update(A);
    }

    @DeleteMapping("/delete/{id}")
    void remove(Integer id){ adresseService.remove(id);}

    @GetMapping("/{id}")
    Adresse retrieve(Integer id) { return adresseService.retrieve(id) ;}

}
