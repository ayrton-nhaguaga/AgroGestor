package com.ayrton.AgroGestor.service;

import com.ayrton.AgroGestor.dto.ProductDTO;
import com.ayrton.AgroGestor.enums.ProductType;
import com.ayrton.AgroGestor.model.Product;
import com.ayrton.AgroGestor.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(ProductDTO dto){
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setProductType(dto.getProductType());
        product.setUnit(dto.getUnit());
        return productRepository.save(product);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> getById(String id){
        return productRepository.findById(id);
    }

    public List<Product> getByNameIgnoreCase(String name){
        return productRepository.findByNameIgnoreCase(name);
    }

    public List<Product> getByPrice(double price){
        return productRepository.findByPrice(price);
    }

    public List<Product> getByProductType(ProductType productType){
        return productRepository.findByProductType(productType);
    }

    public List<Product> getByUnit(String unit){
        return productRepository.findByUnitIgnoreCase(unit);
    }


    public Optional<Product> updateProduct(String id, ProductDTO dto) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(dto.getName());
                    product.setDescription(dto.getDescription());
                    product.setProductType(dto.getProductType());
                    product.setUnit(dto.getUnit());
                    return productRepository.save(product);
                });
    }



    public boolean deleteProductByName(String id){
       return productRepository.findById(id)
               .map(product -> {
                   productRepository.delete(product);
                   return true;
               })
               .orElse(false);
    }
}
