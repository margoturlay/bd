package by.softclub.menu_project.repository;

import by.softclub.menu_project.entity.Restaurant;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Query("select r from Restaurant r where r.id in :ids")
    List<Restaurant> findAllByIds(List<Long> ids);

    @Query(value = "SELECT * FROM restaurant r WHERE r.id = :id", nativeQuery = true)
    Optional<Restaurant> findById(@Param("id") Long id);

    @Query(value = "SELECT * FROM restaurant", nativeQuery = true)
    List<Restaurant> findAll();

    @Override
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM restaurant r WHERE r.id = :id", nativeQuery = true)
    void deleteById(@Param("id") Long id);
}
