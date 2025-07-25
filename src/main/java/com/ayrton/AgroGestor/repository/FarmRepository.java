package com.ayrton.AgroGestor.repository;

import com.ayrton.AgroGestor.model.Farm;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FarmRepository extends MongoRepository<Farm, String> {

    @Override
    Optional<Farm> findById(String id);

    @Override
    List<Farm> findAll();

    List<Farm> findByNameIgnoreCase(String name);

    List<Farm> findByLocationIgnoreCase(String location);

    List<Farm> findBySizeHectares(double sizeHectares);

    List<Farm> findByOwnerId(String ownerId);
}
