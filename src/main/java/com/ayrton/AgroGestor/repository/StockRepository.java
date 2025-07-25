package com.ayrton.AgroGestor.repository;

import com.ayrton.AgroGestor.model.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends MongoRepository<Stock, String> {

    @Override
    Optional<Stock> findById(String id);

    @Override
    List<Stock> findAll();

    List<Stock> findByProductId(String productId);

    List<Stock> findByQuantityLessThan(int quantity);

    List<Stock> findByProductPriceBetween(double min, double max);
}
