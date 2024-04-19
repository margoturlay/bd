package by.softclub.menu_project.repository;

import by.softclub.menu_project.entity.user.Role;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("select r from Role r where r.id in :ids")
    Set<Role> findAllByIds(Set<Long> ids);

    @Query(value = "SELECT * FROM role_ r WHERE r.id = :id", nativeQuery = true)
    Optional<Role> findById(@Param("id") Long id);

    @Query(value = "SELECT * FROM role_", nativeQuery = true)
    List<Role> findAll();

    @Override
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM role_ r WHERE r.id = :id", nativeQuery = true)
    void deleteById(@Param("id") Long id);
}
