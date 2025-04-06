package com.engeto.registration_system.repository;

import com.engeto.registration_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user entity by its unique personal ID.
     *
     * @param personID 12-character personal identifier
     * @return Optional containing user if found, otherwise empty
     */
    Optional<User> findByPersonID(String personID);
}
