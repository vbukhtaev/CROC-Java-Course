package com.company.utilities;

import java.util.Scanner;

/**
 * Класс со статическим методами для ввода информации.
 */
public class Input {

    /**
     * Метод для ввода целого положительного числа в пределах от 1 до {@code bound}
     * с последующей проверкой корректности.
     *
     * @param displayText отображаемый текст
     * @param bound максимальное возможное значение
     * @return целое положительного число в пределах от 1 до {@code bound}
     */
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

    /**
     * Метод для ввода непустой строки с последующей проверкой корректности.
     *
     * @param displayText отображаемый текст
     * @return введенную непустую строку
     */
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
