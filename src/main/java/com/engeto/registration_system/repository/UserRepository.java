package com.engeto.registration_system.repository;

import com.engeto.registration_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPersonID(String personID);
}
