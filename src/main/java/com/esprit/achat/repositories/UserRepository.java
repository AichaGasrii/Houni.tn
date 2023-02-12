package com.esprit.achat.repositories;

import com.esprit.achat.persistence.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
