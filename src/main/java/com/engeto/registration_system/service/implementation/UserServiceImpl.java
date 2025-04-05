package com.engeto.registration_system.service.implementation;

import com.engeto.registration_system.dto.UserCreateDTO;
import com.engeto.registration_system.dto.UserDTO;
import com.engeto.registration_system.dto.UserUpdateDTO;
import com.engeto.registration_system.exception.PersonIdNotAllowedException;
import com.engeto.registration_system.exception.UserAlreadyExistsException;
import com.engeto.registration_system.mapper.UserMapper;
import com.engeto.registration_system.repository.UserRepository;
import com.engeto.registration_system.service.UserService;
import com.engeto.registration_system.entity.User;
import com.engeto.registration_system.util.PersonIdValidator;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;


@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public void createUser(@NotNull UserCreateDTO dto) {
        log.info("Creating user with personID: {}", dto.getPersonID());
        validatePersonId(dto.getPersonID());
        checkIfUserExists(dto.getPersonID());

        User user = userMapper.toEntity(dto);
        user.setUuid(generateUuid());
        userRepository.save(user);
        log.info("User created with ID: {}", user.getId());
    }

    @Override
    public UserDTO getUser(Long id, boolean detail) {
        User user = findUserById(id);
        return detail ? userMapper.toDetailDto(user) : userMapper.toDto(user);
    }

    @Override
    public List<UserDTO> getAllUsers(boolean detail) {
        return userRepository.findAll().stream()
                .map(user -> detail ? userMapper.toDetailDto(user) : userMapper.toDto(user)).toList();
    }

    @Override
    public void updateUser(Long id, @NotNull UserUpdateDTO dto) {
        log.info("Updating user with ID: {}", id);
        User user = findUserById(id);
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        userRepository.save(user);
        log.info("User updated successfully");
    }

    @Override
    public void deleteUser(Long id) {
        log.warn("Deleting user with ID: {}", id);
        userRepository.deleteById(id);
        log.info("User deleted");
    }

    // === Helper methods ===

    private void validatePersonId(String personID) {
        if (!PersonIdValidator.isValid(personID)) {
            throw new PersonIdNotAllowedException("personID is not allowed: " + personID);
        }
    }

    private void checkIfUserExists(String personID) {
        userRepository.findByPersonID(personID).ifPresent(user -> {
            throw new UserAlreadyExistsException("User with personID already exists: " + personID);
        });
    }

    private User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    private String generateUuid() {
        return UUID.randomUUID().toString();
    }
}