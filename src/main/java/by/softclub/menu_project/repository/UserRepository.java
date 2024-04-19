package by.softclub.menu_project.repository;

import by.softclub.menu_project.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByMail(String email);
}
