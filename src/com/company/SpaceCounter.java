package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Класс, описывающий сущность, которая считает количество повторений символа в строке.
 */
public class SpaceCounter {

    /**
     * Считает количество повторений символа в строке.
     *
     * @param source    исходная строка
     * @param template  символ
     * @param timeLimit лимит времени для ожидания
     * @param unit      единицы измерения времени
     * @throws ExecutionException   если осуществляется попытка получения результата задачи,
     *                              которая была прервана исключением
     * @throws InterruptedException если текущий поток был прерван во время ожидания
     */
    public static void count(String source, String template, long timeLimit, TimeUnit unit) throws ExecutionException, InterruptedException {

        final long startTime = System.currentTimeMillis();

        String[] subStrings = split(source, 4);

        ExecutorService executor = Executors.newCachedThreadPool();

        // Список, хранящий количество провторений символа для каждой подстроки.
        List<Future<Integer>> localResults = new ArrayList<>();

        // Передаем отдельную задачу для каждой подстроки в thread pool.
        // При этом сохраняем локальный результат в наш список localResults.
        for (String subString : subStrings) {

            localResults.add(executor.submit(new Callable<Integer>() {

                @Override
                public Integer call() {
                    int localCount = 0;

                    for (int i = 0; i < subString.length(); i++) {
                        if (Matcher.match(String.valueOf(subString.charAt(i)), template)) {
                            localCount++;
                        }
                    }

                    return localCount;
                }
            }));
        }

        executor.shutdown();

        boolean isFinished = executor.awaitTermination(timeLimit, unit);

        if (isFinished) {
            int totalCount = 0;

            // Собираем результаты воедино.
            for (Future<Integer> result : localResults) {
                totalCount += result.get();
            }

            System.out.println("Count of space: " + totalCount);
            final long takenTime = System.currentTimeMillis() - startTime;
            System.out.println("It took " + takenTime / 1000.0 + " seconds.");

        } else {
            /*
                Если потоки thread pool'a не успели отработать и завершиться
                за отведенное время (не успели досчитать), то результат нас не интересует - он не актуален.
             */
            System.out.println("Time is out!");
        }
    }

    /**
     * Делит исходную строку на массив подстрок.
     *
     * <blockquote>Например,
     * <pre>{@code
     *      String[] substrings = split("Some example of a line", 4);
     *      // substrings returned is: [Some,  exa, mple,  of , a li, ne]
     * }</pre></blockquote>
     *
     * @param source исходная строка
     * @param length длина подстроки
     * @return массив подстрок исходной строки
     */
    private static String[] split(String source, int length) {

        if (length <= 0) {
            throw new IllegalArgumentException("Длина подстроки не положительная!");
        }

        int size = (int) Math.ceil((double) source.length() / (double) length);
        String[] result = new String[size];

        for (int i = 0; i < result.length; i++) {
            result[i] = source.substring(i * length, Math.min(source.length(), (i + 1) * length));
        }

        return result;
    }
}
