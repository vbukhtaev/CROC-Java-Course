package com.company;

import com.company.model.Good;
import com.company.model.Sale;
import com.company.model.Seller;
import com.company.model.StockEntry;
import com.company.parsers.json.MyJacksonReader;
import com.company.parsers.json.MySimpleParser;
import com.company.parsers.MyParser;
import com.company.parsers.Tag;
import com.company.parsers.xml.MyDomParser;
import com.company.parsers.xml.MySaxParser;
import com.company.utilities.Input;
import com.company.utilities.Pair;
import com.company.writers.MySaxWriter;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Класс, описывающий меню.
 */
public class Menu {

    /**
     * Файл с товарами.
     */
    private static File goodsFile = null;
    /**
     * Файл с продавцами.
     */
    private static File sellersFile = null;
    /**
     * Файл с данными по наличию товаров у продавцов.
     */
    private static File stockFile = null;
    /**
     * Файл с продажами.
     */
    private static File salesFile = null;

    /**
     * Парсер файлов.
     */
    private static MyParser parser = null;

    /**
     * Объект, записывающий результаты заданий в файл.
     */
    private static final MySaxWriter WRITER = new MySaxWriter();

    /**
     * Директория с входными JSON файлами.
     */
    private static final String JSON_DIRECTORY = "src\\com\\company\\Files\\Input\\JSON";
    /**
     * Директория с входными XML файлами.
     */
    private static final String XML_DIRECTORY = "src\\com\\company\\Files\\Input\\XML";

    /**
     * Метод для запуска меню.
     */
    public static void start() {

        System.out.println("\nВыберите тип входных файлов: \n[1] - JSON \n[2] - XML");
        switch (Input.inputInteger("Ваш выбор: ", 2)) {

            case 1:
                goodsFile = new File(JSON_DIRECTORY, "goods.json");
                sellersFile = new File(JSON_DIRECTORY, "sellers.json");
                stockFile = new File(JSON_DIRECTORY, "stock.json");
                salesFile = new File(JSON_DIRECTORY, "sales.json");

                parser = chooseJsonParser();
                break;

            case 2:
                goodsFile = new File(XML_DIRECTORY, "goods.xml");
                sellersFile = new File(XML_DIRECTORY, "sellers.xml");
                stockFile = new File(XML_DIRECTORY, "stock.xml");
                salesFile = new File(XML_DIRECTORY, "sales.xml");

                parser = chooseXmlParser();
                break;
        }

        // Получение данных из файлов.
        Map<Integer, Good> goodsMap = parser.parseGoods();
        Map<Integer, Seller> sellersMap = parser.parseSellers();
        List<StockEntry> stockList = parser.parseStock();
        Map<Integer, Sale> salesMap = parser.parseSales();

        DataAnalyzer analyzer = new DataAnalyzer(goodsMap, sellersMap, stockList, salesMap);

        chooseTask(analyzer);
    }

    /**
     * Метод, предназначенный для выбора задания.
     * @param analyzer анализатор данных
     */
    private static void chooseTask(DataAnalyzer analyzer) {

        System.out.println("\nВыберите задание:" +
                "\n[1] - Для каждого товара вывести в файл продавца, у которого в наличии наибольшее количество этого товара, " +
                "\n      и само количество этого товара у него в наличии." +
                "\n[2] - Для каждого товара вывести в файл продавца, у которого наименьшая цена на этот товар, " +
                "\n      и саму цену на этот товар у этого продавца." +
                "\n[3] - Для каждого товара вывести в файл общее количество товара этого типа в наличии." +
                "\n[4] - Для каждого товара вывести в файл общее количество проданных товаров этого типа." +
                "\n[5] - Вывести в файл топ 5 продавцов, продавших наибольшее количество товаров." +
                "\n[6] - Вывести в файл топ 5 товаров с наибольшим количеством продаж." +
                "\n[7] - Вывести в файл распределение общего количества продаж по датам." +
                "\n[8] - Вывести в файл топ 5 дат, в которые было продано наибольшее количество товаров." +
                "\n[9] - Вывести в файл среднее количество проданных товаров в день."
        );

        switch (Input.inputInteger("Ваш выбор: ", 9)) {

            case 1:
                Map<Good, Pair<Seller, Integer>> mapOne = analyzer.getMaxStockAndSellerByGoods();

                // Выводим результат.
                WRITER.writeTripleMap(mapOne, "Task1.xml", Tag.STOCK, Tag.AMOUNT);
                break;

            case 2:
                Map<Good, Pair<Seller, Integer>> mapTwo = analyzer.getMinPriceAndSellerByGoods();

                // Выводим результат.
                WRITER.writeTripleMap(mapTwo, "Task2.xml", Tag.STOCK, Tag.PRICE);
                break;

            case 3:
                Map<Good, Integer> mapThree = analyzer.getTotalStockAmountByGoods();

                // Выводим результат.
                WRITER.writeGoodsMap(mapThree, "Task3.xml", Tag.STOCK, Tag.AMOUNT);
                break;

            case 4:
                Map<Good, Integer> mapFour = analyzer.getTotalSoldAmountByGoods();

                // Выводим результат.
                WRITER.writeGoodsMap(mapFour, "Task4.xml", Tag.SALES, Tag.AMOUNT);
                break;

            case 5:
                Map<Seller, Integer> mapFive = analyzer.getTopSellers(5);

                // Выводим результат.
                WRITER.writeSellersMap(mapFive, "Task5.xml", Tag.SALES, Tag.AMOUNT);
                break;

            case 6:
                Map<Good, Integer> mapSix = analyzer.getTopSoldGoods(5);

                // Выводим результат.
                WRITER.writeGoodsMap(mapSix, "Task6.xml", Tag.SALES, Tag.AMOUNT);
                break;

            case 7:
                Map<LocalDate, Integer> mapSeven = analyzer.getTotalSoldAmountByDates();

                // Выводим результат.
                WRITER.writeDatesIntegerMap(mapSeven, "Task7.xml", Tag.SALES, Tag.AMOUNT);
                break;

            case 8:
                Map<LocalDate, Integer> mapEight = analyzer.getTopSoldDates(5);

                // Выводим результат.
                WRITER.writeDatesIntegerMap(mapEight, "Task8.xml", Tag.SALES, Tag.AMOUNT);
                break;

            case 9:
                Map<LocalDate, Double> mapNine = analyzer.getAverageSoldAmountByGoods();

                // Выводим результат.
                WRITER.writeDatesFloatMap(mapNine, "Task9.xml", Tag.SALES, Tag.AMOUNT);
                break;
        }
    }

    /**
     * Метод, предназначенный для выбора парсера JSON файла.
     * @return парсер JSON файла
     */
    private static MyParser chooseJsonParser() {

        System.out.println("\nВыберите тип JSON парсера: \n[1] - JSON.simple \n[2] - Jackson");

        switch (Input.inputInteger("Ваш выбор: ", 2)) {

            case 1:
                parser = new MySimpleParser(goodsFile, sellersFile, stockFile, salesFile);
                break;
            case 2:
                parser = new MyJacksonReader(goodsFile, sellersFile, stockFile, salesFile);
                break;
        }

        return parser;
    }

    /**
     * Метод, предназначенный для выбора парсера XML файла.
     * @return парсер XML файла
     */
    private static MyParser chooseXmlParser() {

        System.out.println("\nВыберите тип XML парсера: \n[1] - DOM \n[2] - SAX");

        switch (Input.inputInteger("Ваш выбор: ", 2)) {

            case 1:
                parser = new MyDomParser(goodsFile, sellersFile, stockFile, salesFile);
            break;
            case 2:
                parser = new MySaxParser(goodsFile, sellersFile, stockFile, salesFile);
            break;
        }

        return parser;
    }
}
