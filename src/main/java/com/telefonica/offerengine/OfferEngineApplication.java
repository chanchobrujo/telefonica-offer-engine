package com.telefonica.offerengine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class OfferEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(OfferEngineApplication.class, args);
        log.info("API OFFER ENGINE ENABLE");
    }
}
