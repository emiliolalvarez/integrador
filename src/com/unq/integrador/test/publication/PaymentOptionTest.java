package com.unq.integrador.test.publication;

import com.unq.integrador.publication.PaymentOption;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PaymentOptionTest {

    private PaymentOption paymentOption;
    private String name;

    @Before
    public void setUp() {
        name = "Credit card";
        paymentOption = new PaymentOption(name);
    }

    @Test
    public void testGeTName() {
        assertEquals(name, paymentOption.getName());
    }
}
