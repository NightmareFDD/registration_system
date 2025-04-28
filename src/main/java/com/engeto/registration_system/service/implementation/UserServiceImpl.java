package com.engeto.registration_system.service.implementation;

import com.engeto.registration_system.dto.UserCreateDTO;
import com.engeto.registration_system.dto.UserDTO;
import com.engeto.registration_system.dto.UserUpdateDTO;
import com.engeto.registration_system.exception.PersonIdNotAllowedException;
import com.engeto.registration_system.exception.UserAlreadyExistsException;
import com.engeto.registration_system.exception.UserNotFoundException;
import com.engeto.registration_system.mapper.UserMapper;
import com.engeto.registration_system.repository.UserRepository;
import com.engeto.registration_system.service.UserService;
import com.engeto.registration_system.entity.User;
import com.engeto.registration_system.util.PersonIdValidator;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void createUser(@NotNull UserCreateDTO userCreateDTO) {
        log.debug("Attempting to create userCreateDTO with personID: {}", userCreateDTO.getPersonID());
        validatePersonId(userCreateDTO.getPersonID());
        ensurePersonIdIsUnique(userCreateDTO.getPersonID());

        User user = userMapper.toEntity(userCreateDTO);
        user.setUuid(generateUuid());
        userRepository.save(user);
        log.info("User created with ID: {} and UUID: {}", user.getId(), user.getUuid());
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
    public void updateUser(Long id, @NotNull UserUpdateDTO userUpdateDTO) {
        log.debug("Attempting to update user with ID: {}", id);
        User user = findUserById(id);
        user.setName(userUpdateDTO.getName());
        user.setSurname(userUpdateDTO.getSurname());
        userRepository.save(user);
        log.info("User with ID {} updated successfully", id);
    }

    @Override
    public void deleteUser(Long id) {
        log.debug("Attempting to delete user with ID: {}", id);
        userRepository.deleteById(id);
        log.info("User with ID {} deleted successfully", id);
    }

    // === Helper methods ===

    private void validatePersonId(String personID) {
        if (!PersonIdValidator.isValid(personID)) {
            throw new PersonIdNotAllowedException("personID is not allowed: " + personID);
        }
    }

    private void ensurePersonIdIsUnique(String personID) {
        userRepository.findByPersonID(personID).ifPresent(user -> {
            throw new UserAlreadyExistsException("User with personID already exists: " + personID);
        });
    }

    private User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found."));
    }

    private String generateUuid() {
        return UUID.randomUUID().toString();
    }
}