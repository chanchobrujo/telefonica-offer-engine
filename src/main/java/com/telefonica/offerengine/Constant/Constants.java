package com.telefonica.offerengine.Constant;

import java.util.Arrays;
import java.util.List;

public enum Constants {
    ;

    public static class Messages {

        public static final String CORRECT_DATA = "Datos registrados correctamente.";
        public static final String INCORRECT_DATA = "Datos incorrectos.";
        public static final String INVALID_DATA = "Datos inválidos.";
        public static final String REPET_DATA = "Datos repetidos.";

        public static final String CLIENT_NOT_FOUND = "Cliente no econtrado.";
        public static final String CLIENT_DELETED_SUCCESS = "Datos eliminados correctamente.";
    }

    public static List<String> TYPE_DOCUMENT = Arrays.asList("DNI", "CEX");
}
