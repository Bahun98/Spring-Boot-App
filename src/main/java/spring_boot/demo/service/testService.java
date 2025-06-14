package spring_boot.demo.service;

import spring_boot.demo.models.entities.test;

import java.util.List;
import java.util.Optional;

public interface testService {
    test save(test test);
    List<test> findAll();
    void delete(Long id);
    Optional<test> findById(Long id);
    Optional<test> findByName(String name);
}
