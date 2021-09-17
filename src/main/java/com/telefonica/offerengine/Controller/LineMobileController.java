package com.telefonica.offerengine.Controller;

import com.telefonica.offerengine.Model.LineMobileFrom;
import com.telefonica.offerengine.Service.LineMobileService;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/linemobile")
public class LineMobileController {

    @Autowired
    private LineMobileService service;

    @PostMapping("/addlinebycustomer/{id}")
    public ResponseEntity<Map<String, Object>> addlinebycustomer(
        @PathVariable("id") int id,
        @RequestBody @Valid LineMobileFrom model,
        BindingResult bindinResult
    ) {
        if (bindinResult.hasErrors()) return service.BindingResultErrors(bindinResult);

        return service
            .save(id, model)
            .map(
                mapper -> {
                    return ResponseEntity
                        .status(mapper.getStatus())
                        .body(mapper.getResponse());
                }
            )
            .get();
    }

    @PutMapping("/cancellinemobile/{id}")
    public ResponseEntity<Map<String, Object>> cancellinemobile(
        @PathVariable("id") int id
    ) {
        return service
            .cancellinemobile(id)
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
