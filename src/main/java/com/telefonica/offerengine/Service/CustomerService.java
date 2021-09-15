package com.telefonica.offerengine.Service;

import com.telefonica.offerengine.Constant.Constants;
import com.telefonica.offerengine.Data.Customer;
import com.telefonica.offerengine.Interface.CustomerRepository;
import com.telefonica.offerengine.Logic.MyFunctions;
import com.telefonica.offerengine.Model.*;
import java.util.*;
import java.util.stream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

@Service
@Transactional 
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public ResponseEntity<Map<String, Object>> BindingResultErrors(
        BindingResult bindinResult
    ) {
        ResponseBody response = new ResponseBody(
            bindinResult
                .getAllErrors()
                .stream()
                .findFirst()
                .get()
                .getDefaultMessage()
                .toString(),
            HttpStatus.INTERNAL_SERVER_ERROR
        );

        return ResponseEntity.internalServerError().body(response.getResponse());
    }

    public List<Customer> findAll() {
        return repository.findAll();
    }

    public Optional<Customer> findByIdcustomer(int id) {
        return repository
            .findById(id)
            .map(mapper -> {
                return Optional.of(mapper);
            })
            .orElseGet(Optional::empty);
    }

    public Optional<ResponseBody> register(CustomerFrom model) {
        Customer customer = new Customer(
            model.getFirtsname(),
            model.getLastname(),
            model.getTypedocument(),
            model.getNumberdocument(),
            MyFunctions.convertStringToDate(model.getDateborn())
        );
        return save(customer);
    }

    public Optional<ResponseBody> save(Customer customer) {
        HttpStatus status = HttpStatus.ACCEPTED;
        String message = Constants.Messages.CORRECT_DATA;

        if (
            Constants.TYPE_DOCUMENT
                .stream()
                .filter(c -> c.equals(customer.getTypedocument()))
                .collect(Collectors.toList())
                .isEmpty() ||
            repository.existsByNumberdocument(customer.getNumberdocument())
        ) {
            status = HttpStatus.NOT_ACCEPTABLE;
            message = Constants.Messages.INVALID_DATA;
        } else {
            repository.save(customer);
        }

        return Optional.of(new ResponseBody(message, status));
    }
}
