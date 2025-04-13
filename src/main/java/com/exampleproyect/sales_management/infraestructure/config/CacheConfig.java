package com.exampleproyect.sales_management.infraestructure.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
@EnableCaching
public class CacheConfig {

    public static final String PRODUCTS_CACHE_NAME = "products";
    public static final Long MAX_CACHED_PRODUCTS = 50L;
    

    @Bean
    CacheManager cacheManager() {

        List<CaffeineCache> caches = new ArrayList<>();
        caches.add(buildCache(PRODUCTS_CACHE_NAME, MAX_CACHED_PRODUCTS));
        
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(caches);
        return cacheManager;
    }

    private static CaffeineCache buildCache(String name, Long maxSize) {
        return new CaffeineCache(name, Caffeine.newBuilder()
        .maximumSize(MAX_CACHED_PRODUCTS).build());
    }

}
