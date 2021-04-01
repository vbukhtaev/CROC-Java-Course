package com.company;

import java.util.Objects;

public class Matcher {

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
