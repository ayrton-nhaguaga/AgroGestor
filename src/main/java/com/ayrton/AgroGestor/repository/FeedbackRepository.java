package com.ayrton.AgroGestor.repository;

import com.ayrton.AgroGestor.model.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackRepository extends MongoRepository<Feedback, String> {

    @Override
    Optional<Feedback> findById(String id);

    @Override
    List<Feedback> findAll();

    List<Feedback> findByUserId(String userId);

    List<Feedback> findByRating(int rating);
}
