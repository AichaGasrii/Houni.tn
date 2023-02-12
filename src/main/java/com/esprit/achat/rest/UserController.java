package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.User;
import com.esprit.achat.services.Interface.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private UserService userService;
    @GetMapping
    List<User> retrieveAll(){

        return userService.retrieveAll();
    }
    @PostMapping("/add")
    void add(User u){

        userService.add(u);
    }
    @PutMapping("/edit")
    void update(User u){

        userService.update(u);
    }
    @DeleteMapping("/delete/{id}")
    void remove(Integer id){

        userService.remove(id);
    }
    @GetMapping("/{id}")
    User retrieve(Integer id){

        return userService.retrieve(id);
    }
}
