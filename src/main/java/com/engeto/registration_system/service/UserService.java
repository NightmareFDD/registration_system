package com.engeto.registration_system.service;

import java.util.List;
import com.engeto.registration_system.dto.*;


public interface UserService {
    /**
     * Creates a new user from the provided data transfer object.
     *
     * @param dto the DTO containing data for the new user
     */
    void createUser(UserCreateDTO dto);

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
     * @param id  the ID of the user to update
     * @param dto the DTO containing updated user data
     */
    void updateUser(Long id, UserUpdateDTO dto);

    /**
     * Deletes the user with the specified ID.
     *
     * @param id the ID of the user to delete
     */
    void deleteUser(Long id);
}
