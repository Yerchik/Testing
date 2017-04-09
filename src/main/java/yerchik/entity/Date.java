package yerchik.entity;

import java.util.Calendar;

/**
 * Created by Yerchik on 04.04.2017.
 */
public class Date {
    public static String date(){
        Calendar c = Calendar.getInstance();
        int year = c.get(c.YEAR);
        int month = c.get(c.MONTH) + 1;
        int day = c.get(c.DAY_OF_MONTH);
        String date = day + " " + month + " " + year;
        return date;
    }
}
