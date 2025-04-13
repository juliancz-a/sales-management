package com.exampleproyect.sales_management.services;

import java.util.List;
import java.util.Optional;

import com.exampleproyect.sales_management.domain.models.CartItem;
import com.exampleproyect.sales_management.domain.models.entities.Product;

public interface CartService {

    List<CartItem> getCart();

    List<CartItem> addToCart(CartItem cartItem);

    Optional<Product> removeFromCart(Long prodId);

    void clearCart();


}
