package com.telefonica.offerengine.Service;

import com.telefonica.offerengine.Constant.Constants;
import com.telefonica.offerengine.Data.Customer;
import com.telefonica.offerengine.Interface.CustomerRepository;
import com.telefonica.offerengine.Logic.MyFunctions;
import com.telefonica.offerengine.Model.CustomerFrom;
import com.telefonica.offerengine.Model.ResponseBody;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public ResponseEntity<Map<String, Object>> BindingResultErrors(BindingResult bindinResult) {
        ResponseBody response = new ResponseBody(
            bindinResult.getAllErrors().stream().findFirst().get().getDefaultMessage().toString(),
            HttpStatus.NOT_ACCEPTABLE
        );

        return ResponseEntity.internalServerError().body(response.getResponse());
    }

    public List<Customer> findAll() {
        return repository.findAll();
    }

    public Optional<Customer> findByIdcustomer(int id) throws Exception {
        return Optional.of(
            repository
                .findAll()
                .stream()
                .filter(c -> c.getIdcustomer() == id)
                .findFirst()
                .orElseThrow(() -> new Exception("Customer Not Found"))
        );
    }

    public ResponseBody save(CustomerFrom model) {
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        String message = Constants.Messages.REPET_DATA;

        Customer customer = new Customer(
            model.getFirtsname(),
            model.getLastname(),
            model.getTypedocument(),
            model.getNumberdocument(),
            MyFunctions.convertStringToDate(model.getDateborn())
        );

        repository.save(customer);

        return new ResponseBody(message, status);
    }
}
