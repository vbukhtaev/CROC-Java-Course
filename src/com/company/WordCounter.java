package com.company;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class describing an entity that counts words in a file.
 */
public class WordCounter {

    /**
     * The file in which the words will be counted.
     */
    private File file;

    /**
     * Constructs and initializes a {@code WordCounter} with the specified file.
     * @param file the file of the newly constructed {@code WordCounter}
     */
    public WordCounter(File file) {
        this.file = file;
    }

    /**
     * Constructs and initializes a {@code WordCounter} with the specified file path.
     * @param filePath the file path of the newly constructed {@code WordCounter}
     */
    public WordCounter(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Returns words amount in a text file.
     *
     * @return words amount in a text file
     * @throws IOException if a {@code WordCounter} path is illegal
     */
    public int getAmount() throws IOException {

        int wordsAmount = 0;

        // Считываем текст из файла.
        try (BufferedReader reader = new BufferedReader(
                new FileReader(this.file))
        ) {

            String currentLine;

            // Считываем построчно.
            while ((currentLine = reader.readLine()) != null) {
                wordsAmount += countWordsInString(currentLine);
            }

        }

        return wordsAmount;
    }

    /**
     * Returns words amount in a string.
     *
     * @param string string
     * @return words amount in a string
     */
    private static int countWordsInString(String string) {

        // '? - для учета английских слов-сокращений, таких как "it's", "isn't" и т.п.
        // -? - для учета составных сложных слов, таких как "плащ-палатка", "IT-специалист" и т.п.
        // Pattern.UNICODE_CHARACTER_CLASS - для поддержки кириллицы.
        Pattern wordPattern = Pattern.compile("\\b\\w+'?-?\\w*\\b", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = wordPattern.matcher(string);

        int wordsAmount = 0;
        while (matcher.find()) {
            wordsAmount++;
        }

        return wordsAmount;
    }
}