package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
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

        // Удаляем все одинарные точки.
        directories.removeIf((dir) -> dir.equals("."));

        // Список для всех ".." из начала исходного пути.
        List<String> unknownParents = new ArrayList<>();

        ListIterator<String> iterator = directories.listIterator();

        while (iterator.hasNext()) {

            if (iterator.next().equals("..")) {

                // Удаляем ".."
                iterator.previous();
                iterator.remove();

                // Удаляем предшествующую директорию.
                if (iterator.hasPrevious()) {

                    // Корневую папку удалять нельзя.
                    if (!isRoot(iterator.previous())) {
                        iterator.remove();
                    }

                } else {
                    // Если выходим слишком высоко по иерархии.
                    unknownParents.add("..");
                }
            }
        }

        // Возвращаем все ".." из начала исходного пути.
        directories.addAll(0, unknownParents);

        // Если в нормализованном пути только корневая папка, то добавляем "\"
        String last = directories.get(directories.size() - 1);

        if (isRoot(last)) {
            directories.set(0, last + "\\");
        }

        return String.join("\\", directories);
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
