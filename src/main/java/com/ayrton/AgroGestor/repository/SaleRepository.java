package com.ayrton.AgroGestor.repository;

import com.ayrton.AgroGestor.model.Sale;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SaleRepository extends MongoRepository<Sale, String> {

    @Override
    Optional<Sale> findById(String id);

    @Override
    List<Sale> findAll();

    List<Sale> findBySaleDateDateBetween(LocalDateTime start, LocalDateTime end);

    List<Sale> findByProductId(String productId);

    List<Sale> findByBuyerName(String buyerName);


}
