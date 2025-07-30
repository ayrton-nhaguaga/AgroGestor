package com.ayrton.AgroGestor.controller;


import com.ayrton.AgroGestor.dto.CropDTO;
import com.ayrton.AgroGestor.model.Crop;
import com.ayrton.AgroGestor.service.CropService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/crops")
public class CropController {

    @Autowired
    private CropService cropService;

    @PostMapping
    public ResponseEntity<Crop> createCrop(@Valid @RequestBody CropDTO dto){
        Crop crop = cropService.createCrop(dto);
        return new ResponseEntity<>(crop, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Crop>> getAllCrops(){
        List<Crop> crops = cropService.getAllCrops();
        return new ResponseEntity<>(crops, HttpStatus.OK);
    }

    @GetMapping("/field_id/{fieldId}")
    public ResponseEntity<List<Crop>> getByFieldId(@PathVariable String fieldId){
        List<Crop> crops = cropService.getByFieldId(fieldId);
        return new ResponseEntity<>(crops, HttpStatus.OK);
    }

    @GetMapping("/culture")
    public ResponseEntity<List<Crop>> getByCultureIgnoreCase(@Valid @RequestParam String culture){
        List<Crop> crops = cropService.getByCultureIgnoreCase(culture);
        return new ResponseEntity<>(crops, HttpStatus.OK);
    }

    @GetMapping("/planted_date")
    public ResponseEntity<List<Crop>> getByPlantedDate(@RequestParam LocalDate plantedDate){
        List<Crop> crops = cropService.getByPlantedDate(plantedDate);
        return new ResponseEntity<>(crops, HttpStatus.OK);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Optional<Crop>> updateCrop( @PathVariable String id, @RequestBody CropDTO dto){
        Optional<Crop> crop = cropService.updateCrop(id, dto);

        if (!crop.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(crop);
    }
}
