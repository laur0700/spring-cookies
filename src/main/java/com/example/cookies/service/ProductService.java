package com.example.cookies.service;

import com.example.cookies.model.Product;
import com.example.cookies.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public void add(Product newProduct){
        productRepository.save(newProduct);
    }

    public Product get(Integer productId){
        Optional<Product> productOptional = productRepository.findById(productId);
        return productOptional.orElse(null);
    }

    public void update(Integer productId, Product product){
        Product productToUpdate = this.get(productId);

        if(product.getPrice() != 0)
            productToUpdate.setPrice(product.getPrice());

        productRepository.save(productToUpdate);
    }

    public void delete(Integer productId){
        Product productToDelete = this.get(productId);
        productRepository.delete(productToDelete);
    }
}
