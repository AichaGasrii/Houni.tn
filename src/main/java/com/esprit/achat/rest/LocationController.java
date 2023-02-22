package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.Location;
import com.esprit.achat.services.Interface.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Location")
@AllArgsConstructor
public class LocationController {
    
   private LocationService locationService ;

    @GetMapping
    List<Location> retrieveAll(){
        return locationService.retrieveAll();
    }

    @PostMapping("/add")
    void add(@RequestBody Location L){
        locationService.add(L);
    }

    @PutMapping("/edit")
    void update(@RequestBody Location L){
        locationService.update(L);
    }

    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable("id") Integer id){ locationService.remove(id);}

    @GetMapping("/{id}")
    Location retrieve(@PathVariable("id") Integer id) { return locationService.retrieve(id) ;}
    
}
