package com.fertifa.app.converters;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {
    public static String convertDateWithRegex(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            return sdf.format(new Date(convertStringToTimestamp(date).getTime()));
        } catch (Exception e) {
            System.out.println("Can Not convert time stamp in Date Converter class");
            return "";
        }
    }

    public static String convertDateWithRegex(Timestamp date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            return sdf.format(new Date(date.getTime()));
        } catch (Exception e) {
            System.out.println("Can Not convert time stamp in Date Converter class");
            return date.toString();
        }
    }

    public static Timestamp convertStringToTimestamp(String dateString) {

        SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
        Timestamp timestamp = null;
        Date parsedDate;
        try {
            parsedDate = dateFormat.parse(dateString);
            timestamp = new java.sql.Timestamp(parsedDate.getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }
}
