package com.example.cookies.service;

import com.example.cookies.model.Customer;
import com.example.cookies.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class LoginService {
    @Autowired
    CustomerService customerService;

    public Customer verifyUser(String username, String password){
        List<Customer> registeredCustomers = customerService.getAll();

        for(Customer c : registeredCustomers){
            if(c.getUsername().equals(username) && c.getPassword().equals(password)){
                return c;
            }
        }

        return null;
    }
}
