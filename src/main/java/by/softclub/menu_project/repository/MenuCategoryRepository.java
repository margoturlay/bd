package by.softclub.menu_project.repository;

import by.softclub.menu_project.entity.MenuCategory;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.Optional;

public interface MenuCategoryRepository extends JpaRepository<MenuCategory, Long> {
    @Query("select r from MenuCategory r where r.id in :ids")
    List<MenuCategory> findAllByIds(List<Long> ids);

    @Query(value = "SELECT * FROM menu_category mc WHERE mc.id = :id", nativeQuery = true)
    Optional<MenuCategory> findById(@Param("id") Long id);

    @Query(value = "SELECT * FROM menu_category", nativeQuery = true)
    List<MenuCategory> findAll();

    @Override
    @Query(value = "DELETE FROM menu_category mc WHERE mc.id = :id", nativeQuery = true)
    void deleteById(@Param("id") Long id);
}
