package com.ayrton.AgroGestor.repository;

import com.ayrton.AgroGestor.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CartRepository extends MongoRepository<Cart, String> {
    Optional<Cart> findByUserIdAndCheckedOutFalse(String userId);
}
