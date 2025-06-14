package spring_boot.demo.service;

import jakarta.transaction.Transactional;
import spring_boot.demo.models.entities.test;
import spring_boot.demo.models.repository.test_repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class test_service_implementation  implements testService {

    private final test_repository test_repository;

    public test_service_implementation(spring_boot.demo.models.repository.test_repository testRepository) {
        test_repository = testRepository;
    }

    @Override
    public test save(test test) {
        return test_repository.save(test);
    }

    @Override
    public List<test> findAll() {
        return test_repository.findAll();
    }

    @Override
    public void delete(Long id) {
      test delete =  test_repository.findById(id).orElse(null);
        assert delete != null;
        test_repository.delete(delete);
    }

    @Override
    public Optional<test> findById(Long id) {
        return test_repository.findById(id);
    }

    @Override
    public Optional<test> findByName(String name) {
        return test_repository.findByName(name);
    }
}
