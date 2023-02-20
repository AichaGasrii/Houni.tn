package com.esprit.achat.repositories;

import com.esprit.achat.persistence.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, String> {
}
