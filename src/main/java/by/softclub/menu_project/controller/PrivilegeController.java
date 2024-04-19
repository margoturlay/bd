package by.softclub.menu_project.controller;

import by.softclub.menu_project.entity.dto.RolePrivilegeDto;
import by.softclub.menu_project.entity.user.RolePrivilege;
import by.softclub.menu_project.service.RolePrivilegeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/privilege",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class PrivilegeController {

    private final RolePrivilegeService rolePrivilegeService;

    @PostMapping("/add")
    public ResponseEntity<Void> addRolePrivilege(@RequestBody RolePrivilegeDto rolePrivilegeDto){
        rolePrivilegeService.add(rolePrivilegeDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<RolePrivilege> getRole(@PathVariable("id") Long id){
        return ResponseEntity.ok(rolePrivilegeService.getById(id));
    }

    @GetMapping("/get/all")
    public List<RolePrivilege> getRoles(){
        return rolePrivilegeService.getAll();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RolePrivilege> updateRole(@RequestBody RolePrivilegeDto rolePrivilegeDto, @PathVariable("id") Long id){
        return ResponseEntity.ok(rolePrivilegeService.update(rolePrivilegeDto, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable("id") Long id){
        rolePrivilegeService.delete(id);
        return ResponseEntity.ok().build();
    }
}
