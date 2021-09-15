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
}
