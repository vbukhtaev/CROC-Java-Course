package com.company;

public class Main {

    /**
     * Homework #1
     * @version 1.0
     * @author Bukhtaev Vladislav
     */

    /*
        Задание.
        Написать программу, которая выводит на экран числа от 1 до 100.
        При этом вместо чисел, кратных трем, программа должна выводить слово "Fizz",
        а вместо чисел, кратных пяти - слово "Buzz".
        Если число кратно и 3, и 5, то программа должна выводить слово "FizzBuzz".
     */

    public static void main(String[] args) {

        for (int i = 1; i <= 100; i++) {

            StringBuilder output = new StringBuilder();

            if (i % 3 == 0) {
                output.append("Fizz");
            }
            if (i % 5 == 0) {
                output.append("Buzz");
            }

            // Чтобы не проверять 2 условия кратности.
            // if ((i % 3 != 0) && (i % 5 != 0)) {
            if (output.length() == 0) {
                output.append(i);
            }

            System.out.println(output);
        }

    }
    /*
        Была мысль создать объект класса StringBuilder перед циклом,
        и очищать его методом .setLength(0) или .delete(0, output.length()) в начале каждой итерации.
        Чтобы не создавать объект 100 раз. Стоит ли так делать?
     */
}
