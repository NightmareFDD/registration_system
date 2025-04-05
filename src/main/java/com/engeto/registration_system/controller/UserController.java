package com.engeto.registration_system.controller;

import com.engeto.registration_system.dto.UserCreateDTO;
import com.engeto.registration_system.dto.UserDTO;
import com.engeto.registration_system.dto.UserUpdateDTO;
import com.engeto.registration_system.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody @Valid UserCreateDTO dto) {
        log.info("Received request to create user with personID: {}", dto.getPersonID());
        userService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id, @RequestParam(required = false) Boolean detail) {
        log.debug("Received request to get user with ID: {} (detail: {})", id, detail);
        return ResponseEntity.ok(userService.getUser(id, Boolean.TRUE.equals(detail)));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(@RequestParam(required = false) Boolean detail) {
        log.debug("Received request to get all users (detail: {})", detail);
        return ResponseEntity.ok(userService.getAllUsers(Boolean.TRUE.equals(detail)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody @Valid UserUpdateDTO dto) {
        log.info("Received request to update user with ID: {}", id);
        userService.updateUser(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.warn("Received request to delete user with ID: {}", id);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
