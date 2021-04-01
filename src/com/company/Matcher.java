package com.company;

import java.util.Objects;

/**
 * Вечно сонный Matcher.
 */
public class Matcher {

    /**
     * Проверяет наличие в строке {@code str} символа {@code character}.
     * @param str исходная строка
     * @param character символ
     * @return {@code true} в случае обнаружения в строке {@code str} символа {@code character}.
     */
    public static boolean match(String str, String character) {
        Objects.requireNonNull(character);
        if (character.length() != 1) {
            throw new IllegalArgumentException("Template must have one character");
        }

        long delay = 100;
        // корректная обработка InterruptedException для метода, который не предполагает проброс этого исключения
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return str.contains(character);
    }
}
