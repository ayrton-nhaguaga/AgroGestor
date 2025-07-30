package com.ayrton.AgroGestor.controller;

import com.ayrton.AgroGestor.dto.ResourceUsageDTO;
import com.ayrton.AgroGestor.model.ResourceUsage;
import com.ayrton.AgroGestor.service.ResourceUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resource-usage")
public class ResourceUsageController {

    @Autowired
    private ResourceUsageService resourceUsageService;

    @PostMapping
    public ResponseEntity<?> registerResourceUsage(@RequestBody ResourceUsageDTO dto) {
        try {
            ResourceUsage createdUsage = resourceUsageService.registerUsage(dto);
            return ResponseEntity.ok(createdUsage);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateResourceUsage(@PathVariable String id, @RequestBody ResourceUsageDTO dto) {
        try {
            return resourceUsageService.updateRegisterUsage(id, dto)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

