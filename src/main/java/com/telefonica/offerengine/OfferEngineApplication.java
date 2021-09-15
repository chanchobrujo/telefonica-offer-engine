package com.telefonica.offerengine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@EnableSwagger2
@SpringBootApplication
public class OfferEngineApplication {

    // 1) localhost:9565/reports/getLineMobilebydocument/CEX/159369085
    // 2) localhost:9565/reports/getOffersByDates/01-12-2000/15-12-2000
    @Bean
    public Docket APIRESTOFFERENGINE() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(OfferEngineApplication.class, args);
        log.info("API OFFER ENGINE ENABLE");
    }
}
