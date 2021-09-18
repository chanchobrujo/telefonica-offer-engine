package com.telefonica.offerengine.Controller;

import com.telefonica.offerengine.Cache.OffersCache;
import com.telefonica.offerengine.Data.Offer;
import com.telefonica.offerengine.Model.OfferFrom;
import com.telefonica.offerengine.Service.OfferService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/offer")
public class OfferController {

    @Autowired
    private OfferService service;

    @Autowired
    private OffersCache cache;

    @GetMapping("/cacheByCode/{code}")
    public ResponseEntity<Optional<Offer>> findByCode(@PathVariable("code") String code) {
        return ResponseEntity.accepted().body(cache.getOffer(code)); 
    } 

    @GetMapping("/cacheAll/")
    public ResponseEntity<List<Offer>> findAll( ) {
        return ResponseEntity.accepted().body(cache.getAll()); 
    } 

    @PostMapping("/addofferbyline/{id}")
    public ResponseEntity<Map<String, Object>> postMethodName(
        @PathVariable("id") int idlinemobile,
        @RequestBody @Valid OfferFrom model,
        BindingResult bindinResult
    ) {
        if (bindinResult.hasErrors()) return service.BindingResultErrors(bindinResult);
        return service
            .save(idlinemobile, model)
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
