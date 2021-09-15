package com.telefonica.offerengine.Logic;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyFunctions {

    public static Date convertStringToDate(String stringdate) {
        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return format1.parse(stringdate);
        } catch (Exception e) {
            return null;
        }
    }

    public static String convertDateToString(Date date) {
        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
        return format1.format(date);
    }

    public static int compareDate(String hora1, String hora2) {
        try {
            SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");

            Date comparar1 = format1.parse(hora1);
            Date comparar2 = format1.parse(hora2);

            return comparar1.compareTo(comparar2);
        } catch (Exception e) {
            return 0;
        }
    }
}
