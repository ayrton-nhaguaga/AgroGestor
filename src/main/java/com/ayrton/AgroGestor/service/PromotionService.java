package com.ayrton.AgroGestor.service;

import com.ayrton.AgroGestor.dto.PromotionDTO;
import com.ayrton.AgroGestor.model.Promotion;
import com.ayrton.AgroGestor.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    public Promotion createPromotion(PromotionDTO dto){
        Promotion promotion = new Promotion();
        promotion.setTitle(dto.getTitle());
        promotion.setDescription(dto.getDescription());
        promotion.setDiscountPercent(dto.getDiscountPercent());
        promotion.setCode(dto.getCode());
        promotion.setValidFrom(dto.getValidFrom());
        promotion.setValidTo(dto.getValidTo());
        promotion.setActive(dto.isActive());
        return  promotionRepository.save(promotion);
    }

    public List<Promotion> getAllPromotions(){
        return promotionRepository.findAll();
    }

    public Optional<Promotion> getById(String id){
        return promotionRepository.findById(id);
    }

    public List<Promotion> getByTitleIgnoreCase(String title){
        return promotionRepository.findByTitleIgnoreCase(title);
    }

    public List<Promotion> getByDiscountPercent(double discountPercent){
        return promotionRepository.findByDiscountPercent(discountPercent);
    }

    public List<Promotion> getByCodeIgnoreCase(String code){
        return promotionRepository.findByCodeIgnoreCase(code);
    }

    public List<Promotion> getByValidFrom(LocalDateTime validFrom){
        return promotionRepository.findByValidFrom(validFrom);
    }

    public List<Promotion> getByValidTo(LocalDateTime validTo){
        return promotionRepository.findByValidTo(validTo);
    }

    public List<Promotion> getByActive(boolean active){
        return promotionRepository.findByActive(active);
    }

    public boolean applyPromotion(String code) {
        List<Promotion> promotions = promotionRepository.findByCodeIgnoreCase(code);

        for (Promotion promotion : promotions) {
            if (promotion.isActive() && isValidNow(promotion)) {
                promotion.setActive(false);
                promotionRepository.save(promotion);
                return true;
            }
        }

        return false;
    }

    private boolean isValidNow(Promotion promotion) {
        LocalDateTime now = LocalDateTime.now();
        return (promotion.getValidFrom().isBefore(now) || promotion.getValidFrom().isEqual(now)) &&
                (promotion.getValidTo().isAfter(now) || promotion.getValidTo().isEqual(now));
    }

    public Optional<Promotion> updatePromotion(String id, PromotionDTO dto){
        return promotionRepository.findById(id)
                .map(promotion -> {
                    promotion.setTitle(dto.getTitle());
                    promotion.setDescription(dto.getDescription());
                    promotion.setDiscountPercent(dto.getDiscountPercent());
                    promotion.setCode(dto.getCode());
                    promotion.setValidTo(dto.getValidTo());
                    promotion.setActive(dto.isActive());
                    return promotionRepository.save(promotion);
                });
    }

    public boolean deletePromotion(String id){
        return promotionRepository.findById(id)
                .map(promotion -> {
                    promotionRepository.delete(promotion);
                    return true;
                })
                .orElse(false);
    }
}
