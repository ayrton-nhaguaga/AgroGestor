package com.ayrton.AgroGestor.repository;

import com.ayrton.AgroGestor.model.Field;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FieldRepository extends MongoRepository<Field, String> {

    @Override
    Optional<Field> findById(String id);

    @Override
    List<Field> findAll();

    List<Field> findByNameIgnoreCase(String name);

    List<Field> findByFarmId(String farmId);

    List<Field> findByArea(double area);

}
