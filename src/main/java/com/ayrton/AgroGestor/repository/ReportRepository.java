package com.ayrton.AgroGestor.repository;

import com.ayrton.AgroGestor.model.Report;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends MongoRepository<Report, String> {

    @Override
    Optional<Report> findById(String id);

    @Override
    List<Report> findAll();


}
