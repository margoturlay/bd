package by.softclub.menu_project.repository;

import by.softclub.menu_project.entity.Order;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select r from Order r where r.id in :ids")
    List<Order> findAllByIds(List<Long> ids);

    @Query(value = "SELECT * FROM order_ o WHERE o.id = :id", nativeQuery = true)
    Optional<Order> findById(@Param("id") Long id);

    @Query(value = "SELECT * FROM order_", nativeQuery = true)
    List<Order> findAll();

    @Override
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM order_ o WHERE o.id = :id", nativeQuery = true)
    void deleteById(@Param("id") Long id);
}
