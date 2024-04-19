package by.softclub.menu_project.controller;

import by.softclub.menu_project.entity.dto.RoleDto;
import by.softclub.menu_project.entity.user.Role;
import by.softclub.menu_project.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/roles",
            produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/add")
    public ResponseEntity<Void> addRole(@RequestBody RoleDto roleDto){
        roleService.add(roleDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Role> getRole(@PathVariable("id") Long id){
        return ResponseEntity.ok(roleService.getById(id));
    }

    @GetMapping("/get/all")
    public List<Role> getRoles(){
        return roleService.getAll();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Role> updateRole(@RequestBody RoleDto roleDto, @PathVariable("id") Long id){
        return ResponseEntity.ok(roleService.update(roleDto, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable("id") Long id){
        roleService.delete(id);
        return ResponseEntity.ok().build();
    }
}
