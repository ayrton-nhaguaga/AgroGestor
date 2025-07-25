package com.ayrton.AgroGestor.repository;

import com.ayrton.AgroGestor.model.ResourceUsage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ResourceUsageRepository extends MongoRepository<ResourceUsage, String> {

    @Override
    Optional<ResourceUsage> findById(String id);

    @Override
    List<ResourceUsage> findAll();

    List<ResourceUsage> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
}
