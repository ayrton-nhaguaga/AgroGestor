package com.ayrton.AgroGestor.controller;

import com.ayrton.AgroGestor.dto.StockDTO;
import com.ayrton.AgroGestor.model.Stock;
import com.ayrton.AgroGestor.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping("/{productId}")
    public ResponseEntity<?> createOrUpdateStock(
            @PathVariable String productId,
            @RequestBody StockDTO stockDto,
            @RequestParam int quantity) {
        try {
            Stock stock = stockService.createStock(productId, stockDto, quantity);
            return ResponseEntity.ok(stock);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Stock>> getAllStocks() {
        return ResponseEntity.ok(stockService.getAllSTock());
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getStockByProductId(@PathVariable String productId) {
        return stockService.getByProductId(productId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/quantity/less-than")
    public ResponseEntity<List<Stock>> getStockWithQuantityLessThan(@RequestParam int value) {
        return ResponseEntity.ok(stockService.getByQuantityLessThan(value));
    }

    @GetMapping("/product-price-range")
    public ResponseEntity<List<Stock>> getStocksByProductPriceRange(
            @RequestParam double min,
            @RequestParam double max) {
        return ResponseEntity.ok(stockService.findStocksByProductPriceBetween(min, max));
    }

    @PutMapping("/{id}/quantity")
    public ResponseEntity<?> updateStockQuantity(
            @PathVariable String id,
            @RequestParam int quantity) {
        return stockService.updateStockQuantity(id, quantity)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStock(@PathVariable String id) {
        boolean deleted = stockService.deleteStock(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

