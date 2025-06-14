package spring_boot.demo.models.repository;

import spring_boot.demo.models.entities.test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface test_repository extends JpaRepository<test, Long> {
    Optional<test> findByName(String name);
}
