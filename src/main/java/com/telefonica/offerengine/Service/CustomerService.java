package com.telefonica.offerengine.Service;

import static com.telefonica.offerengine.Logic.MyFunctions.*;

import com.telefonica.offerengine.Constant.Constants;
import com.telefonica.offerengine.Data.*;
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

    @Autowired
    private OfferService offerservice;

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
        return repository.findById(id).map(Optional::of).orElseGet(Optional::empty);
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

    public List<LineMobile> findLineMobileByDocument(
        String typedocument,
        String numberdocument
    ) {
        Optional<Customer> customer = repository
            .findAll()
            .stream()
            .filter(
                c ->
                    c.getTypedocument().equals(typedocument) &&
                    c.getNumberdocument().equals(numberdocument)
            )
            .findFirst();
        if (customer.isPresent()) {
            return customer.get().getLineMobile().stream().collect(Collectors.toList());
        } else {
            return null;
        }
    }

    public List<Customer> getOffersByDates(String datestart, String dateend) {
        List<Integer> offerIds = offerservice
            .findAll()
            .stream()
            .filter(
                o -> {
                    String date1 = convertDateToString(o.getDatestart());
                    return (
                        compareDate(date1, datestart) > -1 &&
                        compareDate(date1, dateend) < 1
                    );
                }
            )
            .map(Offer::getIdoffer)
            .collect(Collectors.toList());

        return repository
            .findAll()
            .stream()
            .filter(l -> l.getLineMobile().size() >= 3)
            .filter(
                pl ->
                    !pl
                        .getLineMobile()
                        .stream()
                        .filter(opl -> opl.getState())
                        .filter(opl -> opl.getOffer().size() > 0)
                        .filter(
                            opl -> {
                                return !opl
                                    .getOffer()
                                    .stream()
                                    .filter(
                                        off -> {
                                            return !offerIds
                                                .stream()
                                                .filter(idd -> idd == off.getIdoffer())
                                                .collect(Collectors.toList())
                                                .isEmpty();
                                        }
                                    )
                                    .collect(Collectors.toSet())
                                    .isEmpty();
                            }
                        )
                        .collect(Collectors.toList())
                        .isEmpty()
            )
            .collect(Collectors.toList());
    }
}
