package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.*;
import com.esprit.achat.services.Implementation.OffrePServiceIMP;
import com.esprit.achat.services.Implementation.UserService;
import com.esprit.achat.services.Interface.CommandeService;
import com.esprit.achat.services.Interface.OffrePService;
import com.esprit.achat.services.Interface.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/rating")
@AllArgsConstructor
public class RatingController {
    @Autowired
    RatingService ratingService;
    @Autowired
    OffrePService offre;
    @Autowired
    UserService userService;
    @GetMapping
    List<Rating> retrieveAll(){
        return ratingService.retrieveAll();
    }
    @PostMapping("/add")
    void add(@RequestBody Rating r){
        if(Objects.nonNull(r.getOffreProduit()) && Objects.nonNull(r.getOffreProduit().getId())  && Objects.nonNull(r.getUser()) && Objects.nonNull(r.getUser().getUserName()) ) {
            OffreProduit p =  offre.retrieve(r.getOffreProduit().getId());
            User user =  userService.retrieve(r.getUser().getUserName());
            r.setOffreProduit(p);
            r.setUser(user);
        }
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
    //@GetMapping("/RatingOffreProduit/{id}")
   // List<Rating> RatingOffreProduit(@PathVariable("id")  Integer OffreProduitId) {
       // List<Rating> ratings =ratingService.indByOffreProduitId(OffreProduitId);
       // return ratings ;

    //}
   @GetMapping("/GgetRatingByOffer/{id}")
    @ResponseBody
    public Object[] getRatingByOffer (@PathVariable("id") Integer id)
    {
        return ratingService.getRatingByOffer(id);
    }

}
