package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.Role;
import com.esprit.achat.repositories.RoleRepository;
import com.esprit.achat.services.Interface.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceIMP extends CrudServiceIMP<Role,Integer> implements RoleService {
    @Autowired
    RoleRepository roleRepository;
}
