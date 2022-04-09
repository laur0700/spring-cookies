package com.example.cookies.controller;

import com.example.cookies.model.Order;
import com.example.cookies.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping(path = "/create")
    public void create(@RequestBody Order newOrder){
        orderService.add(newOrder);
    }

    @GetMapping(path = "/id/{orderId}")
    public Order read(@PathVariable Integer orderId){
        return orderService.get(orderId);
    }

    @PostMapping(path = "update/id/{orderId}")
    public void update(@PathVariable Integer orderId, @RequestBody Order order){
        orderService.update(orderId, order);
    }

    @DeleteMapping(path = "delete/id/{orderId}")
    public void delete(@PathVariable Integer orderId){
        orderService.delete(orderId);
    }
}
