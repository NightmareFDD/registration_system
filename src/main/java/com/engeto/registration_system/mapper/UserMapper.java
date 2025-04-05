package com.engeto.registration_system.mapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.engeto.registration_system.entity.User;
import com.engeto.registration_system.dto.*;

@Slf4j
@Component
public class UserMapper {

    public User toEntity(UserCreateDTO dto) {
        log.debug("Mapping UserCreateDTO to User entity for personID: {}", dto.getPersonID());
        User user = new User();
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setPersonID(dto.getPersonID());
        return user;
    }

    public UserDTO toDto(User user) {
        log.debug("Mapping User entity to UserDTO for user ID: {}", user.getId());
        return new UserDTO(user.getId(), user.getName(), user.getSurname());
    }

    public UserDetailDTO toDetailDto(User user) {
        log.debug("Mapping User entity to UserDetailDto for user ID: {}", user.getId());
        return new UserDetailDTO(user.getId(), user.getName(), user.getSurname(), user.getPersonID(), user.getUuid());
    }
}