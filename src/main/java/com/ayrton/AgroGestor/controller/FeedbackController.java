package com.ayrton.AgroGestor.controller;


import com.ayrton.AgroGestor.dto.FeedbackDTO;
import com.ayrton.AgroGestor.model.Feedback;
import com.ayrton.AgroGestor.service.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<Feedback> createFeedback(@Valid @RequestBody FeedbackDTO dto){
        Feedback feedback = feedbackService.createFeedback(dto);
        return new ResponseEntity<>(feedback, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Feedback>> getAllFeedbacks(){
        List<Feedback> feedbacks = feedbackService.getAllFeedbacks();
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<Feedback>> getById(@PathVariable String id){
        Optional<Feedback> feedback = feedbackService.getById(id);
        return new ResponseEntity<>(feedback, HttpStatus.OK);
    }

    @GetMapping("/userId/{userId}")
    public ResponseEntity<List<Feedback>> getByUserId(@PathVariable String userId){
        List<Feedback> feedbacks = feedbackService.getByUserId(userId);
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }

    @GetMapping("/rating/{rating}")
    public ResponseEntity<List<Feedback>> getByRating(@RequestBody int rating){
        List<Feedback> feedbacks = feedbackService.getByRating(rating);
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }

    @GetMapping("/saleId/{saleId}")
    public ResponseEntity<List<Feedback>> getBySaleId(@PathVariable String saleId){
        List<Feedback> feedbacks = feedbackService.getBySaleId(saleId);
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable String id){
        if (feedbackService.deleteFeedback(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
