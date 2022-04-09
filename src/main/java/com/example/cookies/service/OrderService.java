package com.example.cookies.service;

import com.example.cookies.model.Order;
import com.example.cookies.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public void add(Order newOrder){
        orderRepository.save(newOrder);
    }

    public Order get(Integer orderId){
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        return orderOptional.orElse(null);
    }

    public void update(Integer orderId, Order order){
        Order orderToUpdate = this.get(orderId);

        if(order.getTimestamp() != 0)
            orderToUpdate.setTimestamp(order.getTimestamp());

        orderRepository.save(orderToUpdate);
    }

    public void delete(Integer orderId){
        Order orderToDelete = this.get(orderId);
        orderRepository.delete(orderToDelete);
    }
}
