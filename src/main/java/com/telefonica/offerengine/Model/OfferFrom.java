package com.telefonica.offerengine.Model;

import javax.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfferFrom {

    @Size(min = 8, message = "Como mínimo debe introducir 8 carácteres.")
    @NotBlank(message = "El campo fecha de inicio no debe estar vacio.")
    private String datestart;

    @Size(min = 8, message = "Como mínimo debe introducir 8 carácteres.")
    @NotBlank(message = "El campo fecha final no debe estar vacio.")
    private String dateend;
}
