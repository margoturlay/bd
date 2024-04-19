package by.softclub.menu_project.repository;

import by.softclub.menu_project.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface DishRepository extends JpaRepository<Dish, Long> {
    @Query("select r from Dish r where r.id in :ids")
    List<Dish> findAllByIds(List<Long> ids);

    @Query(value = "SELECT * FROM dish d WHERE d.id = :id", nativeQuery = true)
    Optional<Dish> findById(@Param("id") Long id);

    @Query(value = "SELECT * FROM dish", nativeQuery = true)
    List<Dish> findAll();

    @Query(value = "DELETE FROM dish d WHERE d.id = :id", nativeQuery = true)
    void deleteById(@Param("id") Long id);
}
