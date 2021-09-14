package com.telefonica.offerengine.Model;

import javax.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LineMobileFrom {

    @Size(
        min = 13,
        max = 13,
        message = "El número debe tener el siguieten formato: +51 xxxyyyzzz."
    )
    @NotBlank(message = "El campo número de teléfono no debe estar vacio.")
    private String numberphone;

    @NotBlank(message = "El campo tipo de plan no debe estar vacio.")
    private String typeplane;

    @NotBlank(message = "El campo nombre de plan no debe estar vacio.")
    private String nameplane;
}
