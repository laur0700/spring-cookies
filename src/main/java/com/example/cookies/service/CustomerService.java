package com.example.cookies.service;

import com.example.cookies.model.Customer;
import com.example.cookies.model.Order;
import com.example.cookies.repository.CustomerRepository;
import com.example.cookies.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

    public void addOrder(Integer customerId){
        Customer customer = this.get(customerId);
        Order newOrder = new Order();

        newOrder.setTimestamp(System.currentTimeMillis());

        customer.getOrders().add(newOrder);

        orderRepository.save(newOrder);
        this.update(customerId, customer);
    }

    public void add(Customer newCustomer){
        customerRepository.save(newCustomer);
    }

    public List<Customer> getAll(){
        return customerRepository.findAll();
    }

    public Customer get(Integer customerId){
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        return customerOptional.orElse(null);
    }

    public void update(Integer customerId, Customer customer){
        Customer customerToUpdate = this.get(customerId);

        if(customer.getUsername() != null)
            customerToUpdate.setUsername(customer.getUsername());

        if(customer.getPassword() != null)
            customerToUpdate.setPassword(customer.getPassword());

        if(customer.getCity() != null)
            customerToUpdate.setCity(customer.getCity());

        if(customer.getCountry() != null)
            customerToUpdate.setCountry(customer.getCountry());

        if(customer.getOrders() != null){
            customerToUpdate.setOrders(customer.getOrders());
        }

        customerRepository.save(customerToUpdate);
    }

    public void delete(Integer customerId){
        Customer customerToDelete = this.get(customerId);
        customerRepository.delete(customerToDelete);
    }

}
