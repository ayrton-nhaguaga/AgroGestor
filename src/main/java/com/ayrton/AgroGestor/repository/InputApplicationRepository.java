package com.ayrton.AgroGestor.repository;

import com.ayrton.AgroGestor.model.InputApplication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface InputApplicationRepository extends MongoRepository<InputApplication, String> {
    @Override
    Optional<InputApplication> findById(String id);

    @Override
    List<InputApplication> findAll();

    List<InputApplication> findByCropId(String cropId);

    List<InputApplication> findByProductContainingIgnoreCase(String product);

    List<InputApplication> findByApplicationDate(LocalDate applicationDate);

    List<InputApplication> findByEmployeeContainingIgnoreCase(String employee);
}
