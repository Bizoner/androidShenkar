package com.shenkar.gadyezra;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BirthNameDate {
    private final String name;
    private final Calendar date;

    public BirthNameDate(String name,Calendar date) {

        this.name = name;
        this.date = date;
    }
    public Calendar getDate() {
        return this.date;
    }

    public String getName() {
        return this.name;
    }

    public String getNameDate(){
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formatDate = df.format(date);
        return (name + ' ' + formatDate);
    }
}
