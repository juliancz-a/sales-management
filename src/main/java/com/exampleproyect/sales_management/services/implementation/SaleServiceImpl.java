package com.exampleproyect.sales_management.services.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exampleproyect.sales_management.domain.models.CartItem;
import com.exampleproyect.sales_management.domain.models.entities.Product;
import com.exampleproyect.sales_management.domain.models.entities.Sale;
import com.exampleproyect.sales_management.domain.models.entities.SaleDetails;
import com.exampleproyect.sales_management.domain.models.entities.User;
import com.exampleproyect.sales_management.domain.repositories.ProductRepository;
import com.exampleproyect.sales_management.domain.repositories.SaleRepository;
import com.exampleproyect.sales_management.domain.repositories.UserRepository;
import com.exampleproyect.sales_management.dto.SaleDto;
import com.exampleproyect.sales_management.mappers.SaleMapper;
import com.exampleproyect.sales_management.services.SaleService;
import com.exampleproyect.sales_management.utils.AuthenticationUtil;

@Service
public class SaleServiceImpl implements SaleService{

    @Autowired
    SaleRepository repository;

    @Autowired 
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired 
    AuthenticationUtil authUtil;


    @Override
    @Transactional(readOnly=true)
    public List<SaleDto> findAll() {
        return SaleMapper.listToDto((List<Sale>) repository.findAll());
    }

    @Override
    @Transactional(readOnly=true)
    public Optional<SaleDto> findById(Long id) {
        return Optional.of(SaleMapper.toDto(repository.findById(id).orElseThrow()));
    }

    @Override
    public Optional<SaleDto> findByIdAndUser(Long id) {

        Optional<User> optionalUserDb = userRepository.findById(getUserId());

        if(optionalUserDb.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(SaleMapper.toDto(repository.findByIdAndUser(id, optionalUserDb.orElseThrow()).orElseThrow()));
    }


    @Override
    @Transactional
    public SaleDto save(List<CartItem> cartItems) {
        Sale sale = new Sale();
        
        Optional<User> optionalUserDb = userRepository.findById(getUserId());

        User userDb = optionalUserDb.orElseThrow();
        sale.setUser(userDb);
        Sale savedSale = repository.save(sale);

        //DTO to ENTITY
        List<SaleDetails> saleDetailsList = cartItems.stream().map(cartItem -> {
            Product product = productRepository.findById(cartItem.getId())
                    .orElseThrow();

            SaleDetails saleDetails = new SaleDetails();
            saleDetails.setSale(savedSale); 
            saleDetails.setProduct(product);
            saleDetails.setProductQuantity(cartItem.getQuantity());

            return saleDetails;

        }).collect(Collectors.toList());

        sale.setSaleDetails(saleDetailsList);
        
        return SaleMapper.toDto(repository.save(savedSale));
    
    }

    @Override
    @Transactional
    public Optional<Sale> delete(Long id) {
        Optional<Sale> optionalSaleDb = repository.findById(id);

        optionalSaleDb.ifPresent(saleDb ->
            repository.delete(saleDb));

        return optionalSaleDb;
    }

    private Long getUserId() {
        return authUtil.getCurrentUserId().orElseThrow();
    }


}
