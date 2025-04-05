package com.engeto.registration_system.service;

import java.util.List;
import com.engeto.registration_system.dto.*;


public interface UserService {
    void createUser(UserCreateDTO dto);
    UserDTO getUser(Long id, boolean detail);
    List<UserDTO> getAllUsers(boolean detail);
    void updateUser(Long id, UserUpdateDTO dto);
    void deleteUser(Long id);
}
