package com.ayrton.AgroGestor.service;

import com.ayrton.AgroGestor.dto.SaleDTO;
import com.ayrton.AgroGestor.enums.SaleStatus;
import com.ayrton.AgroGestor.model.Crop;
import com.ayrton.AgroGestor.model.Promotion;
import com.ayrton.AgroGestor.model.Sale;
import com.ayrton.AgroGestor.model.Stock;
import com.ayrton.AgroGestor.repository.CropRepository;
import com.ayrton.AgroGestor.repository.PromotionRepository;
import com.ayrton.AgroGestor.repository.SaleRepository;
import com.ayrton.AgroGestor.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private CropRepository cropRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    public Sale createSale(SaleDTO dto) {
        // 1. Validar quantidade da venda
        if (dto.getAmount() <= 0) {
            throw new RuntimeException("A quantidade da venda deve ser maior que zero.");
        }

        // 2. Buscar a safra (crop) pelo ID
        Crop crop = cropRepository.findById(dto.getCropId())
                .orElseThrow(() -> new RuntimeException("Safra (crop) não encontrada: " + dto.getCropId()));

        // 3. Buscar o estoque pelo productId da safra
        Optional<Stock> optionalStock = stockRepository.findByProductId(crop.getProductId());

        if (optionalStock.isEmpty()) {
            throw new RuntimeException("Estoque não encontrado para o produto: " + crop.getProductId());
        }

        // 4. Obter o objeto Stock do Optional
        Stock stock = optionalStock.get();

        // 5. Verificar se há estoque suficiente
        if (stock.getQuantity() < dto.getAmount()) {
            throw new RuntimeException("Estoque insuficiente. Disponível: " + stock.getQuantity());
        }

        // 6. Atualizar a quantidade do estoque
        stock.setQuantity(stock.getQuantity() - dto.getAmount());
        stockRepository.save(stock);

        // 7. Calcular o preço por unidade (com promoção, se aplicável)
        double finalUnitPrice = dto.getPricePerUnit();

        if (dto.getPromotionCode() != null && !dto.getPromotionCode().isBlank()) {
            finalUnitPrice = applyPromotionToPrice(dto.getPricePerUnit(), dto.getPromotionCode());
        }

        // 8. Criar o objeto Sale com dados do DTO e calculando o total com promoção (se houver)
        Sale sale = new Sale();
        sale.setCropId(dto.getCropId());
        sale.setBuyerName(dto.getBuyerName());
        sale.setAmount(dto.getAmount());
        sale.setPricePerUnit(finalUnitPrice);
        sale.setTotalPrice(dto.getAmount() * finalUnitPrice);
        sale.setSaleDate(LocalDateTime.now());
        sale.setStatus(SaleStatus.PENDENTE);

        // 9. Salvar e retornar a venda
        return saleRepository.save(sale);
    }


    public double applyPromotionToPrice(double price, String promotionCode) {
        List<Promotion> promotions = promotionRepository.findByCodeIgnoreCase(promotionCode);

        for (Promotion promotion : promotions) {
            if (promotion.isActive() && isValidNow(promotion)) {
                double discount = price * (promotion.getDiscountPercent() / 100.0);

                // Opcional: se quiser apenas "usar" uma vez
                promotion.setActive(false);
                promotionRepository.save(promotion);

                return price - discount;
            }
        }

        // Nenhuma promoção válida encontrada
        return price;
    }

    private boolean isValidNow(Promotion promotion) {
        LocalDateTime now = LocalDateTime.now();
        return (promotion.getValidFrom().isBefore(now) || promotion.getValidFrom().isEqual(now)) &&
                (promotion.getValidTo().isAfter(now) || promotion.getValidTo().isEqual(now));
    }


    public List<Sale> getAllSales(){
        return saleRepository.findAll();
    }

    public Optional<Sale> getById(String id){
        return saleRepository.findById(id);
    }

    public List<Sale> findBySaleDateBetween(LocalDate startDate, LocalDate endDate) {
        // Ajusta as datas para considerar o início do dia da data inicial
        LocalDateTime startDateTime = startDate.atStartOfDay(); // 00:00:00
        // Ajusta para o final do dia da data final
        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX); // 23:59:59.999999999

        return saleRepository.findBySaleDateDateBetween(startDateTime, endDateTime);
    }

    public List<Sale> getByBuyerName(String buyerName){
        return saleRepository.findByBuyerName(buyerName);
    }

    public List<Sale> getByCropId(String cropId){
        return saleRepository.findByCropId(cropId);
    }

    public List<Sale> getByTotalPrice(double totalPrice){
        return saleRepository.findByTotalPrice(totalPrice);
    }
}
