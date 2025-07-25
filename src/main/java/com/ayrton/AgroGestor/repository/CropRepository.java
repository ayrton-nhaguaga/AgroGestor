package com.ayrton.AgroGestor.repository;

import com.ayrton.AgroGestor.model.Crop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CropRepository extends MongoRepository<Crop, String> {

    @Override
    Optional<Crop> findById(String id);

    @Override
    List<Crop> findAll();

    List<Crop> findByFieldId(String fieldId);

    List<Crop> findByCultureIgnoreCase(String culture);

    List<Crop> findByPlantedDate(LocalDate plantedDate);


}
