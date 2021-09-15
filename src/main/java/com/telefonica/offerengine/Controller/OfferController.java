package com.telefonica.offerengine.Controller;

import java.util.Map;

import javax.validation.Valid;

import com.telefonica.offerengine.Model.OfferFrom;
import com.telefonica.offerengine.Service.OfferService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    
    @PostMapping("/addlinebycustomer/{id}")
    public ResponseEntity<Map<String, Object>> postMethodName(
        @PathVariable("id") int id,
        @RequestBody @Valid OfferFrom model,
        BindingResult bindinResult
    ) {
        return null;
    }
}
