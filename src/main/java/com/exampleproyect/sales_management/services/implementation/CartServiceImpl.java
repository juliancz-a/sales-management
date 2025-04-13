package com.exampleproyect.sales_management.services.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.exampleproyect.sales_management.domain.models.CartItem;
import com.exampleproyect.sales_management.domain.models.entities.Product;
import com.exampleproyect.sales_management.domain.repositories.ProductRepository;
import com.exampleproyect.sales_management.infraestructure.config.CacheConfig;
import com.exampleproyect.sales_management.services.CartService;
import com.exampleproyect.sales_management.utils.AuthenticationUtil;


@Service
public class CartServiceImpl implements CartService{

    @Autowired
    CacheManager cacheManager;

    @Autowired
    ProductRepository productRepository;

    @Autowired 
    AuthenticationUtil authUtil;

    @Override
    public List<CartItem> getCart() {

        Cache cache = cacheManager.getCache(CacheConfig.PRODUCTS_CACHE_NAME);
        Long userId = getUserId();


        List<CartItem> cart = cache.get(userId, List.class);

        if(cart == null) {
            cart = new ArrayList<>();
            cache.put(userId, cart);
        }
        
        return cart;
    }

    //@Cacheable(value=CacheConfig.PRODUCTS_CACHE_NAME, key="")
    @Override
    public List<CartItem> addToCart(CartItem cartItem) {
        List<CartItem> items = getCart();
        items.add(cartItem);
        cacheManager.getCache(CacheConfig.PRODUCTS_CACHE_NAME).put(getUserId(), items);

        return items;
        
    }

    @Override
    public Optional<Product> removeFromCart(Long prodId) {
        
        Optional<Product> optionalProdDb = productRepository.findById(prodId);
        optionalProdDb.ifPresent(prodDb -> {
            List<CartItem> items = getCart();
            items.removeIf(prod -> prod.getId().equals(prodDb.getId()));
        });

        return optionalProdDb;
    }

    public void clearCart() {
        cacheManager.getCache(CacheConfig.PRODUCTS_CACHE_NAME).evict(getUserId());
    }

    private Long getUserId() {
        return authUtil.getCurrentUserId().orElseThrow();
    }

    

}
