package com.fertifa.app.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeController {
    private static Calendar cal = Calendar.getInstance();

    public static Timestamp getTodayDate(){
        return new Timestamp(new Date().getTime());
    }

    public static Timestamp getLateMonthsDate(int period){
        Timestamp timestamp = getTodayDate();
        cal.setTimeInMillis(timestamp.getTime());
        cal.add(Calendar.MONTH, period);
        return new Timestamp(cal.getTime().getTime());
    }

    public static Timestamp getNullDate() {
        return null;
    }

    public static Timestamp convertStringToTimestamp(String str_date) {
        try {
            DateFormat formatter;
            formatter = new SimpleDateFormat("dd-MM-yyyy");
            Date date = formatter.parse(str_date);
            java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());

            return timeStampDate;
        } catch (ParseException e) {
            System.out.println("Exception :" + e);
            return null;
        }
    }

    public static String convertTimestampToString(Timestamp timestamp){
        return  Timestamp.valueOf(String.valueOf(timestamp)).toString();

    }
}
