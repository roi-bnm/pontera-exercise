package com.exercise.ponteraexercise;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static final String DATE_FORMAT = "ddMMyyyy";
    public static Date generateDate(String dateString) {
        // Create a date formatter using your format string
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

        // Parse the given date string into a Date object.
        Date myDate = null;
        try {
            myDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        // Use the Calendar class to subtract one day
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(myDate);
        //calendar.add(Calendar.DAY_OF_YEAR, -1);

        // Use the date formatter to produce a formatted date string
        return calendar.getTime();
    }
}
