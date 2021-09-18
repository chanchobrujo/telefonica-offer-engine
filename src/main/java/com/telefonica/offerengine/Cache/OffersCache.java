package com.telefonica.offerengine.Cache;

import java.util.Optional;

import com.telefonica.offerengine.Data.Offer;
import com.telefonica.offerengine.Service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class OffersCache {
    @Autowired
    private OfferService service;

    @Cacheable(value = "offerscache", key = "#code")
    public Optional<Offer> getOffer(String code) { 
        return service.findByCode(code);
    }
}
