package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.User;
import com.esprit.achat.repositories.UserRepository;
import com.esprit.achat.services.Interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceIMP extends CrudServiceIMP<User,Integer> implements UserService {
    @Autowired
    UserRepository userRepository;
}
