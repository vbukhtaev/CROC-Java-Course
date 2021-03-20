package com.company;

import java.io.IOException;

public class Main {

    /**
     * Homework #4
     *
     * @author Bukhtaev Vladislav
     * <p>
     * Задание:
     * </p>
     * В текстовом файле слова могут быть разделены одним или несколькими пробелами,
     * или символами перевода строки.
     * Необходимо реализовать программу, считающую количество слов в файле и выводящую результат на экран.
     * Путь к файлу задается первым аргументом командной строки (args[0]).
     * В случае, если аргумент не задан – кидать IllegalArgumentException.
     * При ошибке открытия файла сообщать об этом в консоль без вывода стектрейса.
     */

    public static void main(String[] args) {

        if (args.length == 0) {
            throw new IllegalArgumentException("Путь к файлу не задан!");
        }

        WordCounter wordCounter = new WordCounter(args[0]);

        try {
            System.out.println("Слов в файле: " + wordCounter.getAmount());
        } catch (IOException e) {
            System.out.println("Ошибка открытия указанного файла!");
        }
    }
}