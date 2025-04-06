package com.engeto.registration_system.mapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.engeto.registration_system.entity.User;
import com.engeto.registration_system.dto.*;

/**
 * Component responsible for mapping between User entities and DTO objects.
 */

@Slf4j
@Component
public class UserMapper {

    /**
     * Maps a {@link UserCreateDTO} to a {@link User} entity.
     *
     * @param dto the user creation DTO
     * @return mapped User entity
     */
    public User toEntity(UserCreateDTO dto) {
        log.debug("Mapping UserCreateDTO to User entity for personID: {}", dto.getPersonID());

        User user = new User();
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setPersonID(dto.getPersonID());

        return user;
    }

    /**
     * Maps a {@link User} entity to a lightweight {@link UserDTO}.
     *
     * @param user the user entity
     * @return basic user DTO
     */
    public UserDTO toDto(User user) {
        log.debug("Mapping User entity to UserDTO for user ID: {}", user.getId());
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .build();
    }

    /**
     * Maps a {@link User} entity to a detailed {@link UserDetailDTO}.
     *
     * @param user the user entity
     * @return detailed user DTO
     */
    public UserDetailDTO toDetailDto(User user) {
        log.debug("Mapping User entity to UserDetailDto for user ID: {}", user.getId());
        return UserDetailDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .personID(user.getPersonID())
                .uuid(user.getUuid())
                .build();
    }
}