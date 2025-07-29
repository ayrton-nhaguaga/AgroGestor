package com.ayrton.AgroGestor.controller;

import com.ayrton.AgroGestor.dto.PromotionDTO;
import com.ayrton.AgroGestor.model.Promotion;
import com.ayrton.AgroGestor.service.PromotionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/promotions")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @PostMapping
    public ResponseEntity<Promotion> createPromotion(@Valid @RequestBody PromotionDTO dto){
        Promotion promotion = promotionService.createPromotion(dto);
        return new ResponseEntity<>(promotion, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Promotion>> getAllPromotions(){
        List<Promotion> promotions = promotionService.getAllPromotions();
        return new ResponseEntity<>(promotions, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<Promotion>> getById(@PathVariable String id){
        Optional<Promotion> promotion = promotionService.getById(id);
        return new ResponseEntity<>(promotion, HttpStatus.OK);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Promotion>> getByTitleIgnoreCase(@Valid @RequestParam String title){
        List<Promotion> promotions = promotionService.getByTitleIgnoreCase(title);
        return new ResponseEntity<>(promotions, HttpStatus.OK);
    }

    @GetMapping("/discount_percent/{discountPercent}")
    public ResponseEntity<List<Promotion>> getByDiscountPercent(@Valid @RequestParam double discountPercent){
        List<Promotion> promotions = promotionService.getByDiscountPercent(discountPercent);
        return new ResponseEntity<>(promotions, HttpStatus.OK);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<List<Promotion>> getByCodeIgnoreCase(@Valid @RequestParam String code){
        List<Promotion> promotions = promotionService.getByCodeIgnoreCase(code);
        return new ResponseEntity<>(promotions, HttpStatus.OK);
    }

    @GetMapping("/valid_to/{validTo}")
    public ResponseEntity<List<Promotion>> getByValidTo(@RequestParam LocalDateTime validTo){
        List<Promotion> promotions = promotionService.getByValidTo(validTo);
        return new ResponseEntity<>(promotions, HttpStatus.OK);
    }

    @GetMapping("active/{active}")
    public ResponseEntity<List<Promotion>> getByActive(@RequestParam boolean active){
        List<Promotion> promotions = promotionService.getByActive(active);
        return new ResponseEntity<>(promotions, HttpStatus.OK);
    }


}
