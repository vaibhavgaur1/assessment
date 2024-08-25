package org.example;

import org.example.exception.NegativeNumberException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringCalculator {

    public static int add(String numbers) throws IllegalArgumentException {
        if (numbers == null || numbers.isEmpty()) {
            return 0;
        }
        String delimiter = ",|\n";

        if (numbers.startsWith("//")) {
            int newlineIndex = numbers.indexOf('\n');
            String delimiterPart = numbers.substring(2, newlineIndex);
            delimiter = extractDelimiter(delimiterPart);
            numbers = numbers.substring(newlineIndex + 1).replace("\n", delimiter);
        }

        String[] splitNumbers = numbers.split(delimiter);
        int total = 0;
        List<Integer> negatives = new ArrayList<>();

        for (String number : splitNumbers) {
            if (!number.isEmpty()) {
                int value = parseNumber(number);
                if (value < 0) {
                    negatives.add(value);
                } else {
                    total += value;
                }
            }
        }
        if (!negatives.isEmpty()) {
            throw new NegativeNumberException("negative numbers not allowed " +
                    String.join(", ", negatives.toString().replaceAll("[\\[\\]\\s]", "")));
        }

        return total;
    }

    private static String extractDelimiter(String delimiterPart) {
        Matcher matcher = Pattern.compile("^\\[(.*)\\]$").matcher(delimiterPart);
        if (matcher.matches()) {
            return Pattern.quote(matcher.group(1));
        }
        return Pattern.quote(delimiterPart);
    }

    private static int parseNumber(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format: " + number, e);
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println(add(""));
            System.out.println(add("1"));
            System.out.println(add("1,5"));
            System.out.println(add("1\n2,3"));
            System.out.println(add("//;\n1;2"));
            System.out.println(add("//[***]\n1***2***3"));
            System.out.println(add("//[;]\n1;2;3"));
            System.out.println(add("//[;]\n1;-2;-3"));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

