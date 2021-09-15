package com.telefonica.offerengine.Controller;

import com.telefonica.offerengine.Data.Customer;
import com.telefonica.offerengine.Data.LineMobile;
import com.telefonica.offerengine.Service.CustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
public class ReportsController {

    @Autowired
    private CustomerService customerservice;

    @GetMapping("/getLineMobilebydocument/{typedocument}/{numberdocument}")
    public ResponseEntity<List<LineMobile>> findLineMobileByDocument(
        @PathVariable("typedocument") String typedocument,
        @PathVariable("numberdocument") String numberdocument
    ) {
        return ResponseEntity
            .accepted()
            .body(customerservice.findLineMobileByDocument(typedocument, numberdocument));
    }

    @GetMapping("/getOffersByDates/{datestart}/{dateend}")
    public ResponseEntity<List<Customer>> getOffersByDates(
        @PathVariable("datestart") String datestart,
        @PathVariable("dateend") String dateend
    ) {
        return ResponseEntity
            .accepted()
            .body(customerservice.getOffersByDates(datestart, dateend));
    }
}
