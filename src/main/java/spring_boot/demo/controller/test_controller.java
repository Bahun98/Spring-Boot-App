package spring_boot.demo.controller;

import spring_boot.demo.helper.errorException;
import spring_boot.demo.models.entities.test;
import spring_boot.demo.service.test_service_implementation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class test_controller {
    private final test_service_implementation test_service;

    public test_controller(test_service_implementation testService) {
        test_service = testService;
    }
    @PostMapping
    public ResponseEntity<test> save(@RequestBody test test) {
        Optional<test> existing = test_service.findByName(test.getName());
        if (existing.isPresent()) {
            throw new errorException("Duplicate Name");
        }
        test testResult= test_service.save(test);
        return ResponseEntity.ok(testResult);
    }
    @GetMapping
    public ResponseEntity<List<test>> getAll() {
        List<test> listTestResult= test_service.findAll();
        return ResponseEntity.ok(listTestResult);
    }
    @GetMapping("/{id}")
    public ResponseEntity<test> getTest(@PathVariable Long id) {
        Optional<test> result = test_service.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }
    @GetMapping("/name/{name}")
    public ResponseEntity<test> getTestByName(@PathVariable String name) {
        Optional<test> result = test_service.findByName(name);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
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
