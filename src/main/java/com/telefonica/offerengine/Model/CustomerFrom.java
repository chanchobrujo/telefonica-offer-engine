package com.telefonica.offerengine.Model;

import javax.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerFrom {

    @NotBlank(message = "El campo nombre no debe estar vacio.")
    private String firtsname;

    @NotBlank(message = "El campo apellido no debe estar vacio.")
    private String lastname;

    @Size(min = 3, message = "Como mínimo debe introducir 3 carácteres.")
    @NotBlank(message = "El campo tipo de documento no debe estar vacio.")
    private String typedocument;

    @Size(min = 7, message = "Como mínimo debe introducir 7 carácteres.")
    @NotBlank(message = "El campo número de documento no debe estar vacio.")
    private String numberdocument;

    @Size(min = 8, message = "Como mínimo debe introducir 8 carácteres.")
    @NotBlank(message = "El campo fecha de nacimiento no debe estar vacio.")
    private String dateborn;
}
