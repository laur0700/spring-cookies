package com.example.cookies.controller;

import com.example.cookies.model.Product;
import com.example.cookies.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping(path = "/create")
    public void create(@RequestBody Product newProduct){
        productService.add(newProduct);
    }

    @GetMapping(path = "/id/{productId}")
    public Product read(@PathVariable Integer productId){
        return productService.get(productId);
    }

    @PostMapping(path = "update/id/{productId}")
    public void update(@PathVariable Integer productId, @RequestBody Product product){
        productService.update(productId, product);
    }

    @DeleteMapping(path = "delete/id/{productId}")
    public void delete(@PathVariable Integer productId){
        productService.delete(productId);
    }
}