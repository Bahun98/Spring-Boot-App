package spring_boot.demo.service;

import spring_boot.demo.models.entities.test;
import spring_boot.demo.dtos.test_dto;

import java.util.List;
import java.util.Optional;

public interface testService {
    test save(test_dto test);
    List<test_dto> findAll();
    void delete(Long id);
    Optional<test_dto> findById(Long id);
    Optional<test_dto> findByName(String name);
}
