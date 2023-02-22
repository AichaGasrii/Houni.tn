package com.esprit.achat.repositories;

import com.esprit.achat.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {

}
