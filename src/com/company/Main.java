package com.company;

import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    /**
     * <strong>Homework #6</strong>
     * <p>
     * Задание:
     * </p>
     * Реализовать функцию, выполняющую "нормализацию" заданного пути,
     * то есть, удаляющую из него лишние директории с учетом переходов "." и "..".
     * <p>
     * В виде строки задан относительный путь в файловой системе, в котором:
     * <ul><li>"." означает текущую директорию;
     * <li>".." означает родительскую директорию по отношению к текущей;
     * <li>"/" используется в качестве разделителя директорий.
     * @author Bukhtaev Vladislav
     */

    public static void main(String[] args) {

        System.out.print("Введите путь: ");
        String path = new Scanner(System.in).nextLine();

        System.out.println("Path.normalize(): " + Paths.get(path).normalize());
        System.out.println("Мое решение:      " + PathNormalizer.normalize(path));
    }
}