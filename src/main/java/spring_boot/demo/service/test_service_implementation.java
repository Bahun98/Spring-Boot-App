package spring_boot.demo.service;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import spring_boot.demo.models.entities.test;
import spring_boot.demo.models.repository.test_repository;
import spring_boot.demo.dtos.test_dto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class test_service_implementation  implements testService {

    private final test_repository test_repository;

    private final ModelMapper modelMapper;

    public test_service_implementation(test_repository testRepository,
                                       ModelMapper modelMapper) {
        test_repository = testRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public test save(test_dto test_dto) {
        test entity = modelMapper.map(test_dto, test.class);
        return test_repository.save(entity);
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
