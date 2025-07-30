package com.ayrton.AgroGestor.controller;

import com.ayrton.AgroGestor.dto.CatalogDTO;
import com.ayrton.AgroGestor.model.Catalog;
import com.ayrton.AgroGestor.service.CatalogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/catalogs")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @PostMapping
    public ResponseEntity<Catalog> createCatalog(@Valid @RequestBody CatalogDTO dto){
        Catalog catalog = catalogService.createCatalog(dto);
        return new ResponseEntity<>(catalog, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Catalog>> getAllCatalogs(){
        List<Catalog> catalogs = catalogService.getAllCatalogs();
        return new ResponseEntity<>(catalogs, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<Catalog>> getById(@Valid @PathVariable String id){
        Optional<Catalog> catalog = catalogService.getById(id);
        return new ResponseEntity<>(catalog, HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<List<Catalog>> getByName(@Valid @RequestParam String name){
        List<Catalog> catalogs = catalogService.getByNameIgnoreCase(name);
        return new ResponseEntity<>(catalogs, HttpStatus.OK);
    }

    @GetMapping("/price")
    public ResponseEntity<List<Catalog>> getByPrice(@Valid @RequestParam double price){
        List<Catalog> catalogs = catalogService.getByPrice(price);
        return new ResponseEntity<>(catalogs, HttpStatus.OK);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Optional<Catalog>> updateCatalogById(@Valid @PathVariable String id, @RequestBody CatalogDTO dto){
        Optional<Catalog> catalog = catalogService.updateCatalogById(id, dto);

        if (!catalog.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(catalog);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteCatalog(@PathVariable String id){
        if (catalogService.deleteCatalog(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
