package parkinglot;

/**
 * Created by Tom on 04/11/2016.
 */

import java.util.*;
import java.lang.*;
import java.text.*;

public class DateTime {

    private String date;
    private String time;

    //DateTime constructor
    public DateTime(String date, String time) {
        this.date = date;
        this.time = time;
    }

    //setters
    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    //getters

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }


    //this method equals the date when user chooses to select vehicles on the specified day
    public boolean dateEquals(String day) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date newDateTime = sdf.parse(day);
            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal1.setTime(newDateTime);
            cal2.setTime(getDateTimeAsDate());
            boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                    && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
            return sameDay;
        } catch (ParseException ex) {
            System.err.println("Wrong" + ex);
        }
        return false;
    }

    //method to read system current time and calculate the difference between user input
    public long hours() {

        Date startDate;
        startDate = getDateTimeAsDate();
        Date date = new Date();
        long secs = (date.getTime() - startDate.getTime()) / 1000;
        long hours = secs / 3600;
        return hours;


    }





    // method to create date and time and parse it in to string
    public Date getDateTimeAsDate() {
        Date dateTime = null;
        try {
            DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
            String dateInString = getDate() + " " + getTime();
            dateTime = sdf.parse(dateInString);
        } catch (ParseException ex) {
            System.err.println("Wrong" + ex);
        }
        return dateTime;
    }

}
