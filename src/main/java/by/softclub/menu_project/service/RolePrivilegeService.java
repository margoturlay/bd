package by.softclub.menu_project.service;

import by.softclub.menu_project.entity.dto.RolePrivilegeDto;
import by.softclub.menu_project.entity.user.RolePrivilege;
import by.softclub.menu_project.repository.RolePrivilegeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolePrivilegeService {
    private final RolePrivilegeRepository rolePrivilegeRepository;

    public void add(RolePrivilegeDto rolePrivilegeDto){
        RolePrivilege rolePrivilege = new RolePrivilege();
        convertDtoToObject(rolePrivilegeDto, rolePrivilege);
        rolePrivilegeRepository.save(rolePrivilege);
    }

    public List<RolePrivilege> getAll(){
        return rolePrivilegeRepository.findAll();
    }

    public RolePrivilege getById(Long id){
        return rolePrivilegeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Privilege not found"));
    }

    public RolePrivilege update(RolePrivilegeDto rolePrivilegeDto, Long id){
        RolePrivilege rolePrivilege = rolePrivilegeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Privilege not found"));
        convertDtoToObject(rolePrivilegeDto, rolePrivilege);
        rolePrivilegeRepository.save(rolePrivilege);
        return rolePrivilege;
    }

    public void delete(Long id){
        rolePrivilegeRepository.deleteById(id);
    }

    public void convertDtoToObject(RolePrivilegeDto rolePrivilegeDto, RolePrivilege rolePrivilege){
        BeanUtils.copyProperties(rolePrivilegeDto, rolePrivilege);
    }

}
