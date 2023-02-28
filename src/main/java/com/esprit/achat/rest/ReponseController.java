package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.Reponse;
import com.esprit.achat.services.Interface.ReponseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/reponse")
@AllArgsConstructor
public class ReponseController {
    private ReponseService reponseService;
    @GetMapping
    List<Reponse> retrieveAll(){
        return reponseService.retrieveAll();
    }
    @PostMapping("/add")
    void add(@RequestBody Reponse r){
        reponseService.add(r);
    }
    @PutMapping("/edit")
    void update(@RequestBody Reponse r){
        reponseService.update(r);
    }
    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable("id") Integer id){
        reponseService.remove(id);
    }
    @GetMapping("/{id}")
    Reponse retrieve(@PathVariable("id") Integer id){
        return reponseService.retrieve(id);
    }
}
