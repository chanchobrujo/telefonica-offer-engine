package com.telefonica.offerengine;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.spring.web.plugins.Docket; 
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;

@Slf4j
@EnableSwagger2
@SpringBootApplication
public class OfferEngineApplication {

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
