package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.Role;
import com.esprit.achat.services.Interface.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@AllArgsConstructor
public class RoleController {
    private RoleService roleService;
    @GetMapping
    List<Role> retrieveAll(){
        return roleService.retrieveAll();
    }
    @PostMapping("/add")
    void add(Role r){
        roleService.add(r);
    }
    @PutMapping("/edit")
    void update(Role r){
        roleService.update(r);
    }
    @DeleteMapping("/delete/{id}")
    void remove(Integer id){
        roleService.remove(id);
    }
    @GetMapping("/{id}")
    Role retrieve(Integer id){
        return roleService.retrieve(id);
    }
}
