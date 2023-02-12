package com.esprit.achat.repositories;

import com.esprit.achat.persistence.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
}
