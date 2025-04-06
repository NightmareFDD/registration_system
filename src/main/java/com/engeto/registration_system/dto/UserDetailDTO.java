package com.engeto.registration_system.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * DTO representing detailed information about a user.
 * Extends {@link UserDTO} with additional fields such as person ID and UUID.
 */

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class UserDetailDTO extends UserDTO {
    private String personID;
    private String uuid;

}
