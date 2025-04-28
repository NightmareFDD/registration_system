package com.engeto.registration_system.service;

import com.engeto.registration_system.dto.UserCreateDTO;
import com.engeto.registration_system.dto.UserDTO;
import com.engeto.registration_system.dto.UserUpdateDTO;

import java.util.List;


public interface UserService {
    /**
     * Creates a new user from the provided data transfer object.
     *
     * @param userCreateDTO the DTO containing data for the new user
     * @return the user data in DTO format
     */
    UserDTO createUser(UserCreateDTO userCreateDTO);

    /**
     * Retrieves a user by their ID.
     * Optionally returns detailed or simplified representation.
     *
     * @param id     the ID of the user to retrieve
     * @param detail if true, returns detailed data; otherwise simplified
     * @return the user data in DTO format
     */
    UserDTO getUser(Long id, boolean detail);

    /**
     * Retrieves all users.
     * Optionally returns detailed or simplified representation.
     *
     * @param detail if true, returns detailed data; otherwise simplified
     * @return list of user DTOs
     */
    List<UserDTO> getAllUsers(boolean detail);

    /**
     * Updates the user with the given ID using the provided DTO.
     *
     * @param id            the ID of the user to update
     * @param userUpdateDTO the DTO containing updated user data
     * @return the user data in DTO format
     */
    UserDTO updateUser(Long id, UserUpdateDTO userUpdateDTO);

    /**
     * Deletes the user with the specified ID.
     *
     * @param id the ID of the user to delete
     */
    void deleteUser(Long id);
}
