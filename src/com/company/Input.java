package com.company;

import java.util.Scanner;

public class Input {

    public static int inputInteger(String displayText, int bound) {

        int number;
        try {
            System.out.print(displayText);
            number = Integer.parseInt(new Scanner(System.in).nextLine());
            if (number >= 1 && number <= bound) {
                return number;
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ввод! (введите целое число от 1 до " + bound + ").\n");
            number = inputInteger(displayText, bound);
        }
        return number;
    }

    public static String inputString(String displayText) {

        String line;
        try {
            System.out.print(displayText);
            line = new Scanner(System.in).nextLine();
            if (line.equals("")) {
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            System.out.println("Некорректный ввод! (введите непустую строку).\n");
            line = inputString(displayText);
        }
        return line;
    }
}
