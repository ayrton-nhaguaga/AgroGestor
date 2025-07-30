package com.ayrton.AgroGestor.service;

import com.ayrton.AgroGestor.dto.SaleDTO;
import com.ayrton.AgroGestor.enums.SaleStatus;
import com.ayrton.AgroGestor.model.*;
import com.ayrton.AgroGestor.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;
    private final StockRepository stockRepository;
    private final PromotionRepository promotionRepository;
    private final PromotionService promotionService;
    private final ProductRepository productRepository;

    public Sale createSale(SaleDTO dto) {
        if (dto.getAmount() <= 0) {
            throw new RuntimeException("A quantidade da venda deve ser maior que zero.");
        }

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + dto.getProductId()));

        Optional<Stock> optionalStock = stockRepository.findByProductId(product.getId());

        if (optionalStock.isEmpty()) {
            throw new RuntimeException("Estoque não encontrado para o produto: " + product.getId());
        }

        Stock stock = optionalStock.get();

        if (stock.getQuantity() < dto.getAmount()) {
            throw new RuntimeException("Estoque insuficiente. Disponível: " + stock.getQuantity());
        }

        stock.setQuantity(stock.getQuantity() - dto.getAmount());
        stock.setLastRestockDate(LocalDateTime.now());
        stockRepository.save(stock);

        double finalUnitPrice = dto.getPricePerUnit();

        if (dto.getPromotionCode() != null && !dto.getPromotionCode().isBlank()) {
            finalUnitPrice = applyPromotionToPrice(dto.getPricePerUnit(), dto.getPromotionCode());
        }

        Sale sale = Sale.builder()
                .productId(dto.getProductId())
                .buyerName(dto.getBuyerName())
                .amount(dto.getAmount())
                .pricePerUnit(finalUnitPrice)
                .totalPrice(dto.getAmount() * finalUnitPrice)
                .saleDate(LocalDateTime.now())
                .status(SaleStatus.CONCLUIDA)
                .build();

        return saleRepository.save(sale);
    }

    public double applyPromotionToPrice(double price, String promotionCode) {
        List<Promotion> promotions = promotionRepository.findByCodeIgnoreCase(promotionCode);

        for (Promotion promotion : promotions) {
            if (promotion.isActive() && isValidNow(promotion)) {
                double discount = price * (promotion.getDiscountPercent() / 100.0);
                promotion.setActive(false); // marcar como usada
                promotionRepository.save(promotion);
                return price - discount;
            }
        }
        return price;
    }

    private boolean isValidNow(Promotion promotion) {
        LocalDateTime now = LocalDateTime.now();
        return (promotion.getValidFrom().isBefore(now) || promotion.getValidFrom().isEqual(now)) &&
                (promotion.getValidTo().isAfter(now) || promotion.getValidTo().isEqual(now));
    }

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public Optional<Sale> getById(String id) {
        return saleRepository.findById(id);
    }

    public List<Sale> findBySaleDateBetween(LocalDate startDate, LocalDate endDate) {
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);
        return saleRepository.findBySaleDateDateBetween(startDateTime, endDateTime);
    }

    public List<Sale> getByBuyerName(String buyerName) {
        return saleRepository.findByBuyerName(buyerName);
    }

    public List<Sale> getByProductId(String productId) {
        return saleRepository.findByProductId(productId);
    }

}
