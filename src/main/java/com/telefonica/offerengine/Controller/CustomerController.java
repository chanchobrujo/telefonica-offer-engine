package com.telefonica.offerengine.Controller;

import com.telefonica.offerengine.Data.Customer;
import com.telefonica.offerengine.Model.CustomerFrom;
import com.telefonica.offerengine.Service.CustomerService;
import java.util.*;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping("/")
    public ResponseEntity<List<Customer>> findAll() {
        return ResponseEntity.accepted().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Customer>> findById(@PathVariable("id") int id) {
        return ResponseEntity.accepted().body(service.findByIdcustomer(id));
    }

    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> postMethodName(
        @RequestBody @Valid CustomerFrom model,
        BindingResult bindinResult
    ) {
        if (bindinResult.hasErrors()) return service.BindingResultErrors(bindinResult);

        return service
            .register(model)
            .map(
                mapper -> {
                    return ResponseEntity
                        .status(mapper.getStatus())
                        .body(mapper.getResponse());
                }
            )
            .get();
    }
}
