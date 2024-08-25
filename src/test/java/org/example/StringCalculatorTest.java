package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class StringCalculatorTest {

    @Test
    public void testEmptyString() {
        assertEquals(0, StringCalculator.add(""));
    }

    @Test
    public void testSingleNumber() {
        assertEquals(1, StringCalculator.add("1"));
        assertEquals(5, StringCalculator.add("5"));
    }

    @Test
    public void testTwoNumbers() {
        assertEquals(6, StringCalculator.add("1,5"));
        assertEquals(3, StringCalculator.add("1\n2"));
    }

    @Test
    public void testMultipleNumbers() {
        assertEquals(6, StringCalculator.add("1,2,3"));
        assertEquals(15, StringCalculator.add("1\n2,3\n4,5"));
    }




}

