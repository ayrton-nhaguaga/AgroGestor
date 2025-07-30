package com.ayrton.AgroGestor.controller;

import com.ayrton.AgroGestor.dto.FarmDTO;
import com.ayrton.AgroGestor.model.Farm;
import com.ayrton.AgroGestor.service.FarmService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/farms")
public class FarmController {

    @Autowired
    private FarmService farmService;

    @PostMapping
    public ResponseEntity<Farm> createFarm(@Valid @RequestBody FarmDTO dto){
        Farm farm = farmService.createFarm(dto);
        return new ResponseEntity<>(farm, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Farm>> getAllFarms(){
        List<Farm> farms = farmService.getAllFarms();
        return new ResponseEntity<>(farms, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<Farm>> getById(@PathVariable String id){
        Optional<Farm> farm = farmService.getById(id);
        return new ResponseEntity<>(farm, HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<List<Farm>> getByName(@Valid @RequestParam String name){
        List<Farm> farms = farmService.getByName(name);
        return new ResponseEntity<>(farms, HttpStatus.OK);
    }

    @GetMapping("/location")
    public ResponseEntity<List<Farm>> getByLocation(@RequestParam String location){
        List<Farm> farms = farmService.getByLocation(location);
        return new ResponseEntity<>(farms, HttpStatus.OK);
    }

    @GetMapping("size_hectares")
    public ResponseEntity<List<Farm>> getBySizeHectares(@RequestParam double sizeHectares){
        List<Farm> farms = farmService.getBySizeHectares(sizeHectares);
        return new ResponseEntity<>(farms, HttpStatus.OK);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Optional<Farm>> updateFarm(@PathVariable String id, @Valid @RequestBody FarmDTO dto){
        Optional<Farm> farm = farmService.updateFarm(id, dto);

        if (!farm.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(farm);

    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteFarm(@PathVariable String id){
        if (farmService.deleteFarm(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
