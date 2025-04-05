package com.engeto.registration_system.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserDetailDTO extends UserDTO {
    private String personID;
    private String uuid;

    public UserDetailDTO(Long id, String name, String surname, String personID, String uuid) {
        super(id, name, surname);
        this.personID = personID;
        this.uuid = uuid;
    }
}
