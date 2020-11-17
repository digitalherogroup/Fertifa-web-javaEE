package com.fertifa.app.forrtest;

import java.sql.Date;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        long now = new Date(calendar.getTimeInMillis()).getTime();

    }
}
