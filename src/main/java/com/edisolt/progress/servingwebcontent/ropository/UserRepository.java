package com.edisolt.progress.servingwebcontent.ropository;

import com.edisolt.progress.servingwebcontent.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    User findByActivationCode(String code);
}
