package com.ayrton.AgroGestor.controller;

import com.ayrton.AgroGestor.dto.SaleDTO;
import com.ayrton.AgroGestor.model.Sale;
import com.ayrton.AgroGestor.service.SaleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;


    @PostMapping
    public ResponseEntity<Sale> createSale(@Valid @RequestBody SaleDTO dto) {
        Sale sale = saleService.createSale(dto);
        return ResponseEntity.ok(sale);
    }


    @GetMapping
    public ResponseEntity<List<Sale>> getAllSales() {
        return ResponseEntity.ok(saleService.getAllSales());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Sale> getById(@PathVariable String id) {
        return saleService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/buyer")
    public ResponseEntity<List<Sale>> getByBuyerName(@RequestParam String name) {
        return ResponseEntity.ok(saleService.getByBuyerName(name));
    }


    @GetMapping("/product")
    public ResponseEntity<List<Sale>> getByProductId(@RequestParam String productId) {
        return ResponseEntity.ok(saleService.getByProductId(productId));
    }


    @GetMapping("/date-range")
    public ResponseEntity<List<Sale>> getByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end
    ) {
        return ResponseEntity.ok(saleService.findBySaleDateBetween(start, end));
    }
}