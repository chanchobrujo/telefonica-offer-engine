package com.telefonica.offerengine.Service;

import com.telefonica.offerengine.Data.Offer;
import com.telefonica.offerengine.Interface.OfferRepository; 
import com.telefonica.offerengine.Model.ResponseBody;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

@Service
@Transactional
public class OfferService {

    @Autowired
    private OfferRepository repository;

    @Autowired
    private LineMobileService lineMobileservice;

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

    public List<Offer> findAll() {
        return repository.findAll();
    }

    public Optional<Offer> findByIdoffer(int id) {
        return repository.findByIdoffer(id).map(Optional::of).orElseGet(Optional::empty);
    }

    public Optional<Offer> findByCode(String code) {
        return repository.findByCode(code).map(Optional::of).orElseGet(Optional::empty);
    }

    public Optional<ResponseBody> save(int idlinemobile, Optional<Offer> model) {
        if (model.isEmpty()) {
            return Optional.of(
                new ResponseBody(
                    "Oferta no encontrada en el catálogo.",
                    HttpStatus.NOT_FOUND
                )
            );
        }
        return lineMobileservice.addOffer(idlinemobile, model.get());
    }
}
