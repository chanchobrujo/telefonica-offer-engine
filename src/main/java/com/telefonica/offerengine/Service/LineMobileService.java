package com.telefonica.offerengine.Service;

import com.telefonica.offerengine.Constant.Constants;
import com.telefonica.offerengine.Data.Customer;
import com.telefonica.offerengine.Data.LineMobile;
import com.telefonica.offerengine.Interface.CustomerRepository;
import com.telefonica.offerengine.Interface.LineMobileRepository;
import com.telefonica.offerengine.Model.LineMobileFrom;
import com.telefonica.offerengine.Model.ResponseBody;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

@Slf4j
@Service
public class LineMobileService {

    @Autowired
    private LineMobileRepository linerepository;

    @Autowired
    private CustomerService customerService;

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

    public List<LineMobile> findAll() {
        return linerepository.findAll();
    }

    public Optional<LineMobile> findByIdlinemobile(int id) {
        return linerepository
            .findById(id)
            .map(mapper -> {
                return Optional.of(mapper);
            })
            .orElseGet(() -> {
                return Optional.empty();
            });
    }

    public Optional<ResponseBody> save(int idcustomer, LineMobileFrom model) {
        HttpStatus status = HttpStatus.ACCEPTED;
        String message = Constants.Messages.CORRECT_DATA;

         

        if (customerService.findByIdcustomer(idcustomer).isPresent()) {
            log.info("GAAAAAAAAAAAA");  
        }

        return Optional.of(new ResponseBody(message, status));
    }
}
