package com.company;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс, описывающий сущность, которая нормализует путь.
 */
public class PathNormalizer {

    /**
     * Возвращает исходный нормализованный путь.
     *
     * @param path исходный путь
     * @return исходный нормализованный путь
     */
    public static String normalize(String path) {

        List<String> directories = getDirectories(path);

        Deque<String> normalizedDirs = new LinkedList<>();

        // Удаляем все одинарные точки.
        directories.removeIf(directory -> directory.equals("."));

        for (String directory : directories) {

            if (directory.equals("..")) {

                if (normalizedDirs.isEmpty() || normalizedDirs.peekLast().equals("..")) {
                    normalizedDirs.offerLast(directory);

                } else if (!isRoot(normalizedDirs.peekLast())) {

                    // Удаляем предшествующую директорию,
                    // если она не является коневой.
                    normalizedDirs.pollLast();
                }

            } else {
                normalizedDirs.offerLast(directory);
            }
        }

        // Если в нормализованном пути только корневая папка, то добавляем "\"
        if (isRoot(normalizedDirs.peekLast())) {
            normalizedDirs.offerLast(normalizedDirs.pollLast() + "\\");
        }

        return String.join("\\", normalizedDirs);
    }

    /**
     * Возвращает список директорий исходного пути.
     *
     * @param path исходный путь
     * @return список директорий исходного пути {@code path}
     */
    private static List<String> getDirectories(String path) {
        /*
            Использую обратный слеш потому что
            именно его использует библиотечный метод normalize() класса Path.
         */
        String delimiter = "\\\\";

        String[] directories = path
                // Удаляем ковычки (если есть).
                .replaceAll("\"+", "")
                // Меняем разделитель директорий.
                .replaceAll("/", delimiter)
                // Делим путь на массив директорий.
                .split(delimiter);

        return new ArrayList<>(Arrays.asList(directories));
    }

    /**
     * Проверяет является ли директория {@code directory} корневой.
     *
     * @param directory директория
     * @return {@code true} если директория {@code directory} является корневой
     */
    private static boolean isRoot(String directory) {
        Pattern rootPattern = Pattern.compile("\\b[A-Za-z]:");
        Matcher matcher = rootPattern.matcher(directory);
        return matcher.find();
    }
}