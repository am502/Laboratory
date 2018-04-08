package ru.kpfu.itis.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public abstract class DateUtil {
    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    /**
     *   This method is used to determine the date range for the month previous to the current month.
     *
     *   @param date the Date that the date range is to be based on
     *   @return an array of two Date objects, the first of which is the start date, the second the end date
     */
    public static Date getPreviousMonth(final Date date){
        Date monthAgo = new Date();
        final GregorianCalendar c = makeCalendar(date);

        c.roll(Calendar.MONTH,-1);
        c.set(Calendar.DAY_OF_MONTH,1);
        return c.getTime();
    }


    private static GregorianCalendar makeCalendar(Date date){
        final GregorianCalendar c = (GregorianCalendar)GregorianCalendar.getInstance();
        if(date != null){
            c.setTime(date);
        }
        return(c);
    }

}