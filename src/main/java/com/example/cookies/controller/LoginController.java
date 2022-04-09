package com.example.cookies.controller;

import com.example.cookies.model.Customer;
import com.example.cookies.model.Login;
import com.example.cookies.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    @GetMapping(path = "/login")
    public ResponseEntity<ResponseCookie> login(@RequestBody Login loginCredentials, @CookieValue(name = "user-id", defaultValue = "default") String userId){
        Customer customer = loginService.verifyUser(loginCredentials.getUsername(), loginCredentials.getPassword());
        if (userId.equals(customer.getId())){
            return null;
        }
        else{
            if(customer != null){
                ResponseCookie cookie = ResponseCookie.from("user-id", customer.getId().toString()).
                        httpOnly(true).secure(true).path("/cookie").maxAge(100000000).domain("localhost").build();

                System.out.println("sent cookie");
                return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).build();
            }
        }

        return null;
    }

    @GetMapping(path = "/cookie")
    public String cookie(@CookieValue(name = "user-id", defaultValue = "default") String userId){
        return userId;
    }

    @GetMapping(path = "/logout")
    public ResponseEntity<ResponseCookie> logout(){
        ResponseCookie deleteCookie = ResponseCookie.from("user-id", null).
                maxAge(0).build();

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, deleteCookie.toString()).build();
    }
}
