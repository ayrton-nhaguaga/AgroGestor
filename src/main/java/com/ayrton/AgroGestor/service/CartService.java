package com.ayrton.AgroGestor.service;

import com.ayrton.AgroGestor.enums.SaleStatus;
import com.ayrton.AgroGestor.model.Cart;
import com.ayrton.AgroGestor.model.CartItem;
import com.ayrton.AgroGestor.model.Sale;
import com.ayrton.AgroGestor.model.Stock;
import com.ayrton.AgroGestor.repository.CartRepository;
import com.ayrton.AgroGestor.repository.SaleRepository;
import com.ayrton.AgroGestor.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final SaleRepository saleRepository;
    private final StockRepository stockRepository;

    public Cart getOrCreateCart(String userId) {
        return cartRepository.findByUserIdAndCheckedOutFalse(userId)
                .orElseGet(() -> cartRepository.save(Cart.builder()
                        .userId(userId)
                        .items(new ArrayList<>())
                        .createdAt(LocalDateTime.now())
                        .checkedOut(false)
                        .build()));
    }

    public Cart addItemToCart(String userId, CartItem newItem) {
        Cart cart = getOrCreateCart(userId);
        cart.getItems().add(newItem);
        return cartRepository.save(cart);
    }

    public Cart removeItemFromCart(String userId, String productId) {
        Cart cart = getOrCreateCart(userId);
        cart.getItems().removeIf(item -> item.getProductId().equals(productId));
        return cartRepository.save(cart);
    }

    public List<Sale> checkout(String userId, String buyerName) {
        Cart cart = getOrCreateCart(userId);

        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Carrinho está vazio.");
        }

        List<Sale> sales = new ArrayList<>();

        for (CartItem item : cart.getItems()) {
            double total = item.getQuantity() * item.getPricePerUnit();

            // Verifica e atualiza o estoque
            Stock stock = stockRepository.findByProductIdAndFarmId(item.getProductId(), userId)
                    .orElseThrow(() -> new RuntimeException("Estoque não encontrado para o produto: " + item.getProductId()));

            if (stock.getQuantity() < item.getQuantity()) {
                throw new RuntimeException("Estoque insuficiente para o produto: " + item.getProductId());
            }

            stock.setQuantity(stock.getQuantity() - item.getQuantity());
            stockRepository.save(stock);

            // Cria a venda
            Sale sale = Sale.builder()
                    .productId(item.getProductId())
                    .buyerName(buyerName)
                    .amount(item.getQuantity())
                    .pricePerUnit(item.getPricePerUnit())
                    .totalPrice(total)
                    .saleDate(LocalDateTime.now())
                    .status(SaleStatus.CONCLUIDA)
                    .build();

            saleRepository.save(sale);
            sales.add(sale);
        }

        // Finaliza o carrinho
        cart.setCheckedOut(true);
        cartRepository.save(cart);

        return sales;
    }
}
