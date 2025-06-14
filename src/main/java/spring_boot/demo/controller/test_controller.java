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
        Optional<test> existing = test_service.findByName(test.getName());
        if (existing.isPresent()) {
            throw new errorException("Duplicate Name");
        }
        test testResult= test_service.save(test);
        return ResponseEntity.ok(modelMapper.map(testResult, test_dto.class));
    }
    @GetMapping
    public ResponseEntity<List<test_dto>> getAll() {
        List<test> listTestResult= test_service.findAll();

        List<test_dto> resultListTest = new ArrayList<>();
        for (test test : listTestResult) {
            resultListTest.add(modelMapper.map(test, test_dto.class));
        }
        return ResponseEntity.ok(resultListTest);
    }
    @GetMapping("/{id}")
    public ResponseEntity<test_dto> getTest(@PathVariable Long id) {
        Optional<test> result = test_service.findById(id);
        return result.map(test -> ResponseEntity.ok(modelMapper.map(test, test_dto.class))).orElseGet(() -> ResponseEntity.notFound().build());

    }
    @GetMapping("/name/{name}")
    public ResponseEntity<test_dto> getTestByName(@PathVariable String name) {
        Optional<test> result = test_service.findByName(name);
        return result.map(test -> ResponseEntity.ok(modelMapper.map(test,test_dto.class))).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<test> deleteTest(@PathVariable Long id) {
        Optional<test> result = test_service.findById(id);
        if (result.isPresent()) {
            test_service.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
