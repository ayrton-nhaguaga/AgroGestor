package com.ayrton.AgroGestor.service;

import com.ayrton.AgroGestor.dto.StockDTO;
import com.ayrton.AgroGestor.model.Product;
import com.ayrton.AgroGestor.model.Stock;
import com.ayrton.AgroGestor.repository.ProductRepository;
import com.ayrton.AgroGestor.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ProductRepository productRepository;

    public Stock createStock(String productId, StockDTO stockDto, int quantity) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) {
            throw new RuntimeException("Produto não encontrado: " + productId);
        }

        // Verifica se já existe um estoque para esse produto e fazenda
        Optional<Stock> existingStock = stockRepository.findByProductIdAndFarmId(productId, stockDto.getFarmId());

        if (existingStock.isPresent()) {
            // Atualiza a quantidade existente
            Stock stock = existingStock.get();
            stock.setQuantity(stock.getQuantity() + quantity);
            return stockRepository.save(stock);
        } else {
            // Cria um novo estoque
            Stock newStock = new Stock();
            newStock.setFarmId(stockDto.getFarmId());
            newStock.setProductId(productId);
            newStock.setQuantity(quantity);
            return stockRepository.save(newStock);
        }
    }

    public List<Stock> getAllSTock(){
        return stockRepository.findAll();
    }

    public Optional<Stock> getByProductId(String productId){
        return stockRepository.findByProductId(productId);
    }

    public  List<Stock> getByQuantityLessThan(int quantity){
        return stockRepository.findByQuantityLessThan(quantity);
    }

    public List<Stock> findStocksByProductPriceBetween(double min, double max) {
        //  Buscar produtos com preço no intervalo
        List<Product> productsInRange = productRepository.findByPriceBetween(min, max);

        //  Extrair os IDs desses produtos
        List<String> productIds = productsInRange.stream()
                .map(Product::getId)
                .collect(Collectors.toList());

        // Buscar os estoques que correspondem a esses produtos
        return stockRepository.findByProductIdIn(productIds);
    }

    public Optional<Stock> updateStockQuantity(String stockId, int newQuantity) {
        return stockRepository.findById(stockId)
                .map(stock -> {
                    stock.setQuantity(newQuantity);
                    return stockRepository.save(stock);
                });
    }


    public boolean deleteStock(String id){
        return stockRepository.findById(id)
                .map(stock -> {
                    stockRepository.delete(stock);
                    return true;
                })
                .orElse(false);

    }
}
