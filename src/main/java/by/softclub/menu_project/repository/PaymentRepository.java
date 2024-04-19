package by.softclub.menu_project.repository;

import by.softclub.menu_project.entity.Payment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query(value = "SELECT * FROM payment p WHERE p.id = :id", nativeQuery = true)
    Optional<Payment> findById(@Param("id") Long id);

    @Query(value = "SELECT * FROM payment", nativeQuery = true)
    List<Payment> findAll();

    @Override
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM payment p WHERE p.id = :id", nativeQuery = true)
    void deleteById(@Param("id") Long id);
}
