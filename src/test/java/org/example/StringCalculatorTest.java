package org.example;

import org.example.exception.NegativeNumberException;
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

    @Test
    public void testCustomDelimiter() {
        assertEquals(3, StringCalculator.add("//;\n1;2"));
        assertEquals(6, StringCalculator.add("//[***]\n1***2***3"));
        assertEquals(6, StringCalculator.add("//[;]\n1;2;3"));
    }

    @Test(expected = NegativeNumberException.class)
    public void testNegativeNumbers() {
        StringCalculator.add("//;\n1;-2;3");
    }

    @Test
    public void testNegativeNumbersExceptionMessage() {
        try {
            StringCalculator.add("//;\n1;-2;-3");
        } catch (NegativeNumberException e) {
            assertEquals("negative numbers not allowed -2,-3", e.getMessage());
        }
    }
}

