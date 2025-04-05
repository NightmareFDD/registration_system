package com.engeto.registration_system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String surname;
}
