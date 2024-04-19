package by.softclub.menu_project.entity.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
public class UserDto {

    private String name;

    private String mail;

    private String password;

    private LocalDateTime creationDate;

    private Set<Long> roles;
}



