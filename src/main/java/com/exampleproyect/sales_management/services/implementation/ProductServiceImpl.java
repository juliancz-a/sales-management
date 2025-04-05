package com.exampleproyect.sales_management.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exampleproyect.sales_management.domain.models.entities.Product;
import com.exampleproyect.sales_management.domain.repositories.ProductRepository;
import com.exampleproyect.sales_management.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository repository;

    @Override
    @Transactional(readOnly=true)
    public List<Product> findAll() {
        return (List<Product>) repository.findAll();
    }

    @Override
    @Transactional(readOnly=true)
    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    @Transactional
    public Optional<Product> update(Long id, Product product) {

        Optional<Product> optionalProductDb = findById(id);

        if(optionalProductDb.isPresent()){
            Product productDb = optionalProductDb.orElseThrow();

            productDb.setName(product.getName());
            productDb.setDescription(product.getDescription());
            productDb.setPrice(product.getPrice());

            return Optional.of(repository.save(productDb));
        }

        return optionalProductDb;
    }

    @Override
    @Transactional
    public Optional<Product> delete(Long id) {
        Optional<Product> optionalProductDb = findById(id);

        optionalProductDb.ifPresent(productDb ->
            repository.delete(productDb));

        return optionalProductDb;
    }



}
