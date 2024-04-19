package by.softclub.menu_project.repository;

import by.softclub.menu_project.entity.user.RolePrivilege;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RolePrivilegeRepository extends JpaRepository<RolePrivilege, Long> {
    @Query("select r from RolePrivilege r where r.id in :ids")
    Set<RolePrivilege> findAllByIds(Set<Long> ids);

    @Query(value = "SELECT * FROM privilege_ rp WHERE rp.id = :id", nativeQuery = true)
    Optional<RolePrivilege> findById(@Param("id") Long id);

    @Query(value = "SELECT * FROM privilege_", nativeQuery = true)
    List<RolePrivilege> findAll();

    @Override
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM privilege_ rp WHERE rp.id = :id", nativeQuery = true)
    void deleteById(@Param("id") Long id);
}
