package com.unq.integrador.test.publication;

import com.unq.integrador.publication.PricePeriod;
import org.junit.Before;
import org.junit.Test;
import org.mockito.cglib.core.Local;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PricePeriodTest {

    private PricePeriod pricePeriod;
    private Integer startDay;
    private Integer startMonth;
    private Integer endDay;
    private Integer endMonth;
    private Float price;

    @Before
    public void setUp() {
        startDay = 10;
        startMonth = 1;
        endDay = 25;
        endMonth = 3;
        price = new Float(500f);
        pricePeriod = new PricePeriod(startMonth, startDay, endMonth, endDay, price);
    }

    @Test
    public void getPrice() {
        assertEquals(price, pricePeriod.getPrice());
    }

    @Test
    public void getStartMonth() {
        assertEquals(startMonth, pricePeriod.getStartMonth());
    }

    @Test
    public void getStartDay() {
        assertEquals(startDay, pricePeriod.getStartDay());
    }

    @Test
    public void getEndMonth() {
        assertEquals(endMonth, pricePeriod.getEndMonth());
    }

    @Test
    public void getEndDay() {
        assertEquals(endDay, pricePeriod.getEndDay());
    }

    @Test
    public void testDateIsInPeriod() {
        LocalDate date = LocalDate.parse("2017-01-12");
        assertTrue(pricePeriod.isInPeriod(date));
    }

    @Test
    public void testDateIsInPeriodAndEqualsStartDayAndStartMonth() {
        LocalDate date = LocalDate.parse("2017-01-10");
        assertTrue(pricePeriod.isInPeriod(date));
    }

    @Test
    public void testDateIsInPeriodAndEqualsEndDayAndEndtMonth() {
        LocalDate date = LocalDate.parse("2017-03-25");
        assertTrue(pricePeriod.isInPeriod(date));
    }

    @Test
    public void testDateIsNotInPricePeriod() {
        assertFalse(pricePeriod.isInPeriod(LocalDate.parse("2017-04-10")));
        assertFalse(pricePeriod.isInPeriod(LocalDate.parse("2017-01-09")));
    }

}
