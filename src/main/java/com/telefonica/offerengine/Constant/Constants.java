package com.telefonica.offerengine.Constant;

import java.util.*;

public enum Constants {
    ;

    public static class Messages {

        public static final String CORRECT_DATA = "Datos registrados correctamente.";
        public static final String INVALID_DATA = "Datos inválidos o repetidos.";

        public static final String CLIENT_NOT_FOUND = "Cliente no econtrado.";
        public static final String CLIENT_DELETED_SUCCESS =
            "Datos eliminados correctamente.";
    }

    public static List<String> TYPE_DOCUMENT = Arrays.asList("DNI", "CEX");

    public static List<String> LINE_MOBILE_STATES = Arrays.asList("Activo", "Cancelado");
    public static List<String> LINE_MOBILE_TYPES = Arrays.asList("Postpago", "Prepago");
    public static List<String> LINE_MOBILE_PLANES = Arrays.asList(
        "Plan Prepago",
        "Plan S/ 29.90",
        "Plan S/ 39.90",
        "Plan S/ 49.90",
        "Plan S/ 65.90"
    );
}
