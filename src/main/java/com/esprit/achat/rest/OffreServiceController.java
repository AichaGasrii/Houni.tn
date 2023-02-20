package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.OffreService;
import com.esprit.achat.services.Interface.OffreSService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offreService")
@AllArgsConstructor
public class OffreServiceController {
    @Autowired
    private OffreSService offreService;

    @GetMapping
    List<OffreService> retrieveAll(){
        return offreService.retrieveAll();
    }

    @PostMapping("/add")
    void add(OffreService o){
        offreService.add(o);
    }

    @PutMapping("/edit")
    void update(OffreService o){
        offreService.update(o);
    }

    @DeleteMapping("/delete/{id}")
    void remove(Integer id){
        offreService.remove(id);
    }

    @GetMapping("/{id}")
    OffreService retrieve(Integer id){
        return offreService.retrieve(id);
    }
}
