package by.softclub.menu_project.service;

import by.softclub.menu_project.entity.dto.UserDto;
import by.softclub.menu_project.entity.user.Role;
import by.softclub.menu_project.entity.user.User;
import by.softclub.menu_project.repository.RoleRepository;
import by.softclub.menu_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService{

    private final UserRepository userRepository;
    
    private final RoleRepository roleRepository;


    public void add(UserDto userDto){
        User user = new User();
        convertDtoToObject(userDto, user);
        userRepository.save(user);
    }

    public User getById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() ->  new RuntimeException("User not found"));
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User update(UserDto userDto, Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        convertDtoToObject(userDto, user);
        userRepository.save(user);
        return user;
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }

    public void convertDtoToObject(UserDto userDto, User user){
        BeanUtils.copyProperties(userDto, user, "roles", "password");
        user.setPassword(userDto.getPassword());
        Set<Role> roles = roleRepository.findAllByIds(userDto.getRoles());
        user.setCreationDate(LocalDateTime.now());
        user.setRoles(roles);
    }
}
