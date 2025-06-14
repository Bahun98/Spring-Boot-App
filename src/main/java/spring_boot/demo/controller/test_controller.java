package spring_boot.demo.controller;

import org.modelmapper.ModelMapper;
import spring_boot.demo.helper.errorException;
import spring_boot.demo.models.entities.test;
import spring_boot.demo.service.test_service_implementation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring_boot.demo.dtos.test_dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class test_controller {
    private final test_service_implementation test_service;

    private final ModelMapper modelMapper;

    public test_controller(test_service_implementation testService, ModelMapper modelMapper) {
        test_service = testService;
        this.modelMapper = modelMapper;
    }
    @PostMapping
    public ResponseEntity<test_dto> save(@RequestBody test_dto test) {
        Optional<test_dto> existing = test_service.findByName(test.getName());
        if (existing.isPresent()) {
            throw new errorException("Duplicate Name");
        }
        test testResult= test_service.save(test);
        return ResponseEntity.ok(modelMapper.map(testResult, test_dto.class));
    }
    @GetMapping
    public ResponseEntity<List<test_dto>> getAll() {
        return ResponseEntity.ok(test_service.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<test_dto> getTest(@PathVariable Long id) {
        return test_service.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<test_dto> getTestByName(@PathVariable String name) {
        return test_service.findByName(name)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<test> deleteTest(@PathVariable Long id) {
        Optional<test_dto> result = test_service.findById(id);
        if (result.isPresent()) {
            test_service.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
