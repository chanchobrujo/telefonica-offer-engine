package com.telefonica.offerengine.Controller;

import java.util.List;

import com.telefonica.offerengine.Data.Customer;
import com.telefonica.offerengine.Service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService service;

    @GetMapping(value="/")
    public List<Customer> findAll() {
        return service.findAll();
    }
    
}
