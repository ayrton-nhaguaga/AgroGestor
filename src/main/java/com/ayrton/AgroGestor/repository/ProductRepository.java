package com.ayrton.AgroGestor.repository;

import com.ayrton.AgroGestor.enums.ProductType;
import com.ayrton.AgroGestor.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    @Override
    Optional<Product> findById(String id);

    @Override
    List<Product> findAll();

    List<Product> findByNameIgnoreCase(String name);

    List<Product> findByProductType(ProductType productType);

    List<Product> findByUnitIgnoreCase(String unit);
}
