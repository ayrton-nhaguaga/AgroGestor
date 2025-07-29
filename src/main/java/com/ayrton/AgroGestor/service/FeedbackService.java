package com.ayrton.AgroGestor.service;

import com.ayrton.AgroGestor.dto.FeedbackDTO;
import com.ayrton.AgroGestor.model.Feedback;
import com.ayrton.AgroGestor.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public Feedback createFeedback(FeedbackDTO dto){
        Feedback feedback = new Feedback();
        feedback.setUserId(dto.getUserId());
        feedback.setSaleId(dto.getSaleId());
        feedback.setRating(dto.getRating());
        feedback.setComment(dto.getComment());
        feedback.setCreatedAt(dto.getCreatedAt());
        return feedbackRepository.save(feedback);
    }

    public List<Feedback> getAllFeedbacks(){
        return feedbackRepository.findAll();
    }

    public Optional<Feedback> getById(String id){
        return feedbackRepository.findById(id);
    }

    public List<Feedback> getByUserId(String userId){
        return feedbackRepository.findByUserId(userId);
    }


    public List<Feedback> getByRating(int rating){
        return feedbackRepository.findByRating(rating);
    }

    public List<Feedback> getBySaleId(String saleId){
        return feedbackRepository.findBySaleId(saleId);
    }

    public boolean deleteFeedback(String id) {
       return feedbackRepository.findById(id)
               .map(feedback -> {
                   feedbackRepository.delete(feedback);
                   return true;
               })
               .orElse(false);
    }
}
