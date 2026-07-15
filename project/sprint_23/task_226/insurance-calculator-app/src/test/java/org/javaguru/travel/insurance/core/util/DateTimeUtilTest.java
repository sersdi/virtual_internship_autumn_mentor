package org.javaguru.travel.insurance.core.util;

import org.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeUtilTest {

    private DateTimeUtil dateTimeUtil = new DateTimeUtil();

    @Test
    public void shouldDaysBetweenBeZero() {
        Date date1 = createDate("01.01.2023");
        Date date2 = createDate("01.01.2023");
        var daysBetween = dateTimeUtil.getDaysBetween(date1, date2);
        assertEquals(0L, daysBetween);
    }

    @Test
    public void shouldDaysBetweenBePositive() {
        Date date1 = createDate("01.01.2023");
        Date date2 = createDate("10.01.2023");
        var daysBetween = dateTimeUtil.getDaysBetween(date1, date2);
        assertEquals(9L, daysBetween);
    }

    @Test
    public void shouldDaysBetweenBeNegative() {
        Date date1 = createDate("10.01.2023");
        Date date2 = createDate("01.01.2023");
        var daysBetween = dateTimeUtil.getDaysBetween(date1, date2);
        assertEquals(-9L, daysBetween);
    }

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}