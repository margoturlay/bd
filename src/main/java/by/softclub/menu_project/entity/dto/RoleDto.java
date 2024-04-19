package by.softclub.menu_project.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class RoleDto {

    private String name;

    Set<Long> rolePrivileges;
}
