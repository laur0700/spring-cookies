package com.example.cookies.controller;

import com.example.cookies.model.Customer;
import com.example.cookies.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping(path = "/add-order/customer-id/{customerId}")
    public void addOrder(@PathVariable Integer customerId){
        customerService.addOrder(customerId);
    }

    @PostMapping(path = "/create")
    public void create(@RequestBody Customer newCustomer){
        customerService.add(newCustomer);
    }

    @GetMapping(path = "/id/{customerId}")
    public Customer read(@PathVariable Integer customerId){
        return customerService.get(customerId);
    }

    @PostMapping(path = "update/id/{customerId}")
    public void update(@PathVariable Integer customerId, @RequestBody Customer customer){
        customerService.update(customerId, customer);
    }

    @DeleteMapping(path = "delete/id/{customerId}")
    public void delete(@PathVariable Integer customerId){
        customerService.delete(customerId);
    }
}
