package by.softclub.menu_project.service;

import by.softclub.menu_project.entity.dto.RoleDto;
import by.softclub.menu_project.entity.user.Role;
import by.softclub.menu_project.entity.user.RolePrivilege;
import by.softclub.menu_project.repository.RolePrivilegeRepository;
import by.softclub.menu_project.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final RolePrivilegeRepository rolePrivilegeRepository;


    public void add(RoleDto roleDto){
        Role role = new Role();
        convertDtoToObject(roleDto, role);
        roleRepository.save(role);
    }

    public Role getById(Long id){
        return roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }

    public List<Role> getAll(){
        return roleRepository.findAll();
    }

    public Role update(RoleDto roleDto, Long id){
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        convertDtoToObject(roleDto, role);
        roleRepository.save(role);
        return role;
    }

    public void delete(Long id){
        roleRepository.deleteById(id);
    }

    public void convertDtoToObject(RoleDto roleDto, Role role){
        BeanUtils.copyProperties(roleDto, role, "rolePrivileges");
        Set<RolePrivilege> privileges = rolePrivilegeRepository.findAllByIds(roleDto.getRolePrivileges());
        role.setRolePrivileges(privileges);
    }
}
