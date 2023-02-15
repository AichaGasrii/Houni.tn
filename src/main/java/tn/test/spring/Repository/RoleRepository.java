package tn.test.spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.test.spring.Entity.Role;

public interface RoleRepository extends JpaRepository<Role, String> {
}
