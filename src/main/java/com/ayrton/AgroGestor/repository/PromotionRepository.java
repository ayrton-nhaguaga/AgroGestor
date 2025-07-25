package com.ayrton.AgroGestor.repository;

import com.ayrton.AgroGestor.model.Promotion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PromotionRepository extends MongoRepository<Promotion, String> {

    @Override
    Optional<Promotion> findById(String id);

    @Override
    List<Promotion> findAll();

    List<Promotion> findByTitleIgnoreCase(String title);

    List<Promotion> findByDiscountPercent(double discountPercent);

    List<Promotion> findByCodeIgnoreCase(String code);

    List<Promotion> findByValidFrom(LocalDateTime validFrom);

    List<Promotion> findByValidTo(LocalDateTime validTo);

    List<Promotion> findByActive(boolean active);
}
