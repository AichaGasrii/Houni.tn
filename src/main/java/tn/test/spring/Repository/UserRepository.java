package tn.test.spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.test.spring.Entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}
