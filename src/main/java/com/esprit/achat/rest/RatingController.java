package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.Rating;
import com.esprit.achat.services.Interface.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
@AllArgsConstructor
public class RatingController {
    private RatingService ratingService;
    @GetMapping
    List<Rating> retrieveAll(){
        return ratingService.retrieveAll();
    }
    @PostMapping("/add")
    void add(@RequestBody Rating r){

        ratingService.add(r);
    }
    @PutMapping("/edit")
    void update(@RequestBody Rating r){

        ratingService.update(r);
    }
    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable("id") Integer id){

        ratingService.remove(id);
    }
    @GetMapping("/{id}")
    Rating retrieve(@PathVariable("id") Integer id){

        return ratingService.retrieve(id);
    }
}
