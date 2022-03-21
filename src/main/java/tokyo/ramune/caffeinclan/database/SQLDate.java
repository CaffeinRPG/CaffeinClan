package tokyo.ramune.caffeinclan.database;

import java.util.Calendar;

public class SQLDate {

    private Calendar cal = Calendar.getInstance();
    private int year;
    private int month;
    private int dayOfMonth;

    public SQLDate(int year, int month, int dayOfMonth) {
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
    }

    public SQLDate(String sqlDate) {
        year = sqlDate.charAt(0) + sqlDate.charAt(1) + sqlDate.charAt(2) + sqlDate.charAt(3);
        month = sqlDate.charAt(5) + sqlDate.charAt(6);
        dayOfMonth =  + sqlDate.charAt(8) +  + sqlDate.charAt(9);
    }

    public SQLDate() {
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH) + 1;
        dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
    }

    public String getDateNow() {
        if (String.valueOf(cal.get(Calendar.MONTH)).length() == 1) {
            return cal.get(Calendar.YEAR) + "-0" + cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.DAY_OF_MONTH);
        } else {
            return cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.DAY_OF_MONTH);
        }
    }

    public String getDate() {
        if (String.valueOf(month).length() == 1) {
            return year + "-0" + month + "-" + dayOfMonth;
        } else {
            return year + "-" + month + "-" + dayOfMonth;
        }
    }

    public String toString() {
        if (String.valueOf(month).length() == 1) {
            return year + "-0" + month + "-" + dayOfMonth;
        } else {
            return year + "-" + month + "-" + dayOfMonth;
        }
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }
}
