package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.AppelOffre;
import com.esprit.achat.persistence.entity.OffreService;
import com.esprit.achat.services.Interface.AppelOffreService;
import com.esprit.achat.services.Interface.OffreSService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/offreService")
@AllArgsConstructor
public class OffreServiceController {

    private OffreSService offreService;
    private AppelOffreService appelOffreService;

    @GetMapping
    List<OffreService> retrieveAll(){
        return offreService.retrieveAll();
    }

    @PostMapping("/add")
    void add(@RequestBody OffreService o){
        if(Objects.nonNull(o.getAppeloffre()) && Objects.nonNull(o.getAppeloffre().getId()) ) {
            AppelOffre appelOffre =  appelOffreService.retrieve(o.getAppeloffre().getId());
            o.setAppeloffre(appelOffre);
        }
        offreService.add(o);
    }

    @PutMapping("/edit")
    void update(@RequestBody OffreService o){
        offreService.update(o);
    }

    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable("id") Integer id){
        offreService.remove(id);
    }

    @GetMapping("/{id}")
    OffreService retrieve(@PathVariable("id") Integer id){
        return offreService.retrieve(id);
    }
}
