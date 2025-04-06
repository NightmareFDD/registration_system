package com.engeto.registration_system.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Lightweight DTO representing basic user information.
 * Typically used in list responses or where full details are not required.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserDTO {
    private Long id;
    private String name;
    private String surname;
}
