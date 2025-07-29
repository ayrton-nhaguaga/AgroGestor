package com.ayrton.AgroGestor.controller;


import com.ayrton.AgroGestor.dto.ProductDTO;
import com.ayrton.AgroGestor.enums.ProductType;
import com.ayrton.AgroGestor.model.Product;
import com.ayrton.AgroGestor.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid  @RequestBody ProductDTO dto){
        Product product = productService.createProduct(dto);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<Product>> getById(@PathVariable String id){
        Optional<Product> product = productService.getById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Product>> getByNameIgnoreCase(@Valid @RequestParam String name){
        List<Product> products = productService.getByNameIgnoreCase(name);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/price/{price}")
    public ResponseEntity<List<Product>> getByPrice(@Valid @RequestParam double price){
        List<Product> products = productService.getByPrice(price);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/product-type/productType")
    public ResponseEntity<List<Product>> getByProductType(@RequestParam ProductType productType){
        List<Product> products = productService.getByProductType(productType);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Optional<Product>> updateProduct(@PathVariable String id, @Valid @RequestBody ProductDTO dto){
        Optional<Product> product = productService.updateProduct(id, dto);

        if (!product.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable String id){
        if (productService.deleteProductById(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


}
