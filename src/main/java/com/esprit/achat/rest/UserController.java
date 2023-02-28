package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.User;
import com.esprit.achat.services.Implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/users"})
    @PreAuthorize("hasRole('Admin')")
    public List<User> getAll(){
        return userService.getAll();
    }
    @GetMapping({"/user/{userName}"})
    public User findOne(@PathVariable String userName){
        return userService.findOne(userName);
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }

    @DeleteMapping ({"/delete/{userName}"})
    @PreAuthorize("hasRole('Admin')")
    public void delete(@PathVariable String userName){
        userService.delete(userName);
    }

    @PutMapping  ({"/update"})
    @PreAuthorize("hasRole('User')")
    public void update(@RequestBody User user){
        userService.update(user);
    }
    @GetMapping("/count")
    @PreAuthorize("hasRole('Admin')")
    public long count(){return userService.count();}
    @GetMapping("/countoperateur")
    @PreAuthorize("hasRole('Admin')")
    public long countoperateur(){return userService.countoperateur();}
    @GetMapping("/countadmin")
    @PreAuthorize("hasRole('Admin')")
    public long countadmin(){return userService.countadmin();}


}