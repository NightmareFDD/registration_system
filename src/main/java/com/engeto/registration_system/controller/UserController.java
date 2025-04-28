package com.engeto.registration_system.controller;

import com.engeto.registration_system.dto.UserCreateDTO;
import com.engeto.registration_system.dto.UserDTO;
import com.engeto.registration_system.dto.UserUpdateDTO;
import com.engeto.registration_system.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/users", produces = "application/json")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO  createUser(@RequestBody @Valid UserCreateDTO userCreateDTO) {
        log.info("Received request to create user with personID: {}", userCreateDTO.getPersonID());
        return userService.createUser(userCreateDTO);
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable Long id, @RequestParam(required = false) Boolean detail) {
        log.debug("Received request to get user with ID: {} (detail: {})", id, detail);
        return userService.getUser(id, Boolean.TRUE.equals(detail));
    }

    @GetMapping
    public List<UserDTO> getAllUsers(@RequestParam(required = false) Boolean detail) {
        log.debug("Received request to get all users (detail: {})", detail);
        return userService.getAllUsers(Boolean.TRUE.equals(detail));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO  updateUser(@PathVariable Long id, @RequestBody @Valid UserUpdateDTO userUpdateDTO) {
        log.info("Received request to update user with ID: {}", id);
        return userService.updateUser(id, userUpdateDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        log.warn("Received request to delete user with ID: {}", id);
        userService.deleteUser(id);
    }
}
