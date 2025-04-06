package com.engeto.registration_system.service;

import com.engeto.registration_system.dto.UserCreateDTO;
import com.engeto.registration_system.entity.User;
import com.engeto.registration_system.exception.UserAlreadyExistsException;
import com.engeto.registration_system.mapper.UserMapper;
import com.engeto.registration_system.repository.UserRepository;
import com.engeto.registration_system.service.implementation.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private UserServiceImpl userService;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        userMapper = mock(UserMapper.class);
        userService = new UserServiceImpl(userRepository, userMapper);
    }

    @Test
    void createUser_whenPersonIdAlreadyExists_throwsException() {
        // given
        String personId = "mY6sT1jA3cLz";
        UserCreateDTO dto = UserCreateDTO.builder()
                .name("John")
                .surname("Doe")
                .personID(personId)
                .build();

        when(userRepository.findByPersonID(personId)).thenReturn(Optional.of(new User()));

        // then
        assertThrows(UserAlreadyExistsException.class, () -> userService.createUser(dto));
    }

    @Test
    void createUser_successfullySavesUser() {
        // given
        String personId = "nS7tJ0qR5wGh";
        UserCreateDTO dto = UserCreateDTO.builder()
                .name("John")
                .surname("Doe")
                .personID(personId)
                .build();

        when(userRepository.findByPersonID(personId)).thenReturn(Optional.empty());
        when(userMapper.toEntity(dto)).thenReturn(new User());
        when(userRepository.save(any(User.class))).thenReturn(new User());

        // when
        userService.createUser(dto);

        // then
        verify(userRepository).save(any(User.class));
    }
}