package com.spicytown.utils.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by yunlongxu on 4/29/16.
 */
public class DateData {

    private DateFormat dateFormat;
    private Date date;
    private Calendar cal;
    private TimeZone timeZone = TimeZone.getTimeZone("America/Los_Angeles");

    public DateData() {
        date = new Date();
    }

    private void setTimeZone() {
        dateFormat.setTimeZone(timeZone);
    }

    // get current date
    public String getCurrentDate() {
        dateFormat = new SimpleDateFormat("yyyyMMdd");
        setTimeZone();
        return dateFormat.format(date);
    }

    // get current time in minutes
    public int getCurrentTime() {
        dateFormat = new SimpleDateFormat("HH:mm");
        setTimeZone();
        String time = dateFormat.format(date);
        return minConverter(time);
    }

    // this method is help to convert to minutes
    public int minConverter(String time) {
        String[] timeSplit = time.split(":");
        int hour = Integer.parseInt(timeSplit[0]);
        int min = Integer.parseInt(timeSplit[1]);
        return hour * 60 + min;
    }

    public String dateFormatter(String date){
        date = date.substring(0, 4) + "/" + date.substring(4, 6) + "/" + date.substring(6, 8);
        return date;
    }

    public String timeFormatter(int time){
        int hr_int = time/60;
        String am_pm = "AM";
        if(hr_int>12){
            am_pm = "PM";
            hr_int-=12;
        }
        String hr = Integer.toString(hr_int);
        if(hr.length()<2){
            hr = "0" + hr;
        }
        String min = Integer.toString(time%60);
        if(min.length()<2){
            min = "0" + min;
        }

        return hr+":"+min + am_pm;
    }
}
