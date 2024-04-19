package by.softclub.menu_project.repository;

import by.softclub.menu_project.entity.RestaurantTable;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long> {
    @Query("select r from RestaurantTable r where r.id in :ids")
    List<RestaurantTable> findAllByIds(List<Long> ids);

    @Query(value = "SELECT * FROM restaurant_table rt WHERE rt.id = :id", nativeQuery = true)
    Optional<RestaurantTable> findById(@Param("id") Long id);

    @Query(value = "SELECT * FROM restaurant_table", nativeQuery = true)
    List<RestaurantTable> findAll();

    @Override
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM restaurant_table rt WHERE rt.id = :id", nativeQuery = true)
    void deleteById(@Param("id") Long id);
}
