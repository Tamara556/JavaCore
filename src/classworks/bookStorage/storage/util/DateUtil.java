package classworks.bookStorage.storage.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy");

    public static Date fromStringToDate(String dateStr) throws ParseException {
        return SDF.parse(dateStr);
    }

    public static String fromDateToString(Date date) {
        return SDF.format(date);
    }

    public static Date fromYearToDate(int year) throws ParseException {
        // Defaulting to January 1st of the given year
        String dateStr = "01-01-" + year;
        return SDF.parse(dateStr);
    }
}
