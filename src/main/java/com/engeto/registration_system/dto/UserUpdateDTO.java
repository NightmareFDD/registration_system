package com.engeto.registration_system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * Data Transfer Object used for updating an existing user.
 * Only first and last name are updatable.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String surname;
}
