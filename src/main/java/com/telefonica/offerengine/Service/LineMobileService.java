package com.telefonica.offerengine.Service;

import com.telefonica.offerengine.Constant.Constants;
import com.telefonica.offerengine.Data.Customer;
import com.telefonica.offerengine.Data.LineMobile;
import com.telefonica.offerengine.Data.Offer;
import com.telefonica.offerengine.Interface.LineMobileRepository;
import com.telefonica.offerengine.Model.*;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

@Service
@Transactional
public class LineMobileService {

    @Autowired
    private LineMobileRepository linerepository;

    @Autowired
    private CustomerService customerService;

    private HttpStatus status = HttpStatus.ACCEPTED;
    private String message = Constants.Messages.CORRECT_DATA;

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
        return linerepository.findById(id).map(Optional::of).orElseGet(Optional::empty);
    }

    public Optional<ResponseBody> save(int idcustomer, LineMobileFrom model) {
        if (customerService.findByIdcustomer(idcustomer).isPresent()) {
            Customer customer = customerService.findByIdcustomer(idcustomer).get();
            Set<LineMobile> LinemobileList = new HashSet<>();
            if (
                Constants.LINE_MOBILE_TYPES
                    .stream()
                    .filter(c -> c.equals(model.getTypeplane()))
                    .collect(Collectors.toList())
                    .isEmpty() ||
                Constants.LINE_MOBILE_PLANES
                    .stream()
                    .filter(c -> c.equals(model.getNameplane()))
                    .collect(Collectors.toList())
                    .isEmpty() ||
                linerepository.existsByNumberphone(model.getNumberphone())
            ) {
                status = HttpStatus.NOT_ACCEPTABLE;
                message = Constants.Messages.INVALID_DATA;
            } else {
                status = HttpStatus.ACCEPTED;
                message = Constants.Messages.CORRECT_DATA;
                LineMobile line = new LineMobile(
                    model.getNumberphone(),
                    model.getTypeplane(),
                    model.getNameplane()
                );

                linerepository.save(line);

                LinemobileList.add(line);
                LinemobileList.addAll(customer.getLineMobile());

                customer.setLineMobile(LinemobileList);
                customerService.save(customer);
            }
        } else {
            status = HttpStatus.NOT_FOUND;
            message = Constants.Messages.CLIENT_NOT_FOUND;
        }

        return Optional.of(new ResponseBody(message, status));
    }

    public Optional<ResponseBody> addOffer(int idlinemobile, Offer offer) {
        Optional<LineMobile> linemobile = linerepository.findByIdlinemobile(idlinemobile);
        if (linemobile.isPresent()) {
            status = HttpStatus.ACCEPTED;
            message = Constants.Messages.CORRECT_DATA;

            Set<Offer> offerList = linemobile.get().getOffer();
            offerList.add(offer);

            linemobile.get().setOffer(offerList);
            linerepository.save(linemobile.get());
        } else {
            status = HttpStatus.NOT_FOUND;
            message = Constants.Messages.INVALID_DATA;
        }
        return Optional.of(new ResponseBody(message, status));
    }

    public Optional<ResponseBody> cancellinemobile(int idlinemobile) {
        Optional<LineMobile> linemobile = linerepository.findByIdlinemobile(idlinemobile);
        if (linemobile.isPresent()) {
            status = HttpStatus.ACCEPTED;
            message = Constants.Messages.CORRECT_DATA;

            linemobile.get().setState(false);
            linerepository.save(linemobile.get());
        } else {
            status = HttpStatus.NOT_FOUND;
            message = Constants.Messages.INVALID_DATA;
        }
        return Optional.of(new ResponseBody(message, status));
    }
}
