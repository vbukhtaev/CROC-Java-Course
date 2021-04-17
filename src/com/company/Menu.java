package com.company;

import com.company.Model.Good;
import com.company.Model.Sale;
import com.company.Model.Seller;
import com.company.Model.StockEntry;
import com.company.Parsers.JSONParsers.MyJacksonReader;
import com.company.Parsers.JSONParsers.MySimpleParser;
import com.company.Parsers.MyParser;
import com.company.Parsers.Tag;
import com.company.Parsers.XMLParsers.MyDomParser;
import com.company.Parsers.XMLParsers.MySaxParser;
import com.company.Utilities.Input;
import com.company.Utilities.Pair;
import com.company.Writers.MySaxWriter;

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
    private static final MySaxWriter writer = new MySaxWriter();

    /**
     * Директория с входными JSON файлами.
     */
    private static final String jsonDirectory = "src\\com\\company\\Files\\Input\\JSON";
    /**
     * Директория с входными XML файлами.
     */
    private static final String xmlDirectory = "src\\com\\company\\Files\\Input\\XML";

    /**
     * Метод для запуска меню.
     */
    public static void start() {

        System.out.println("\nВыберите тип входных файлов: \n[1] - JSON \n[2] - XML");
        switch (Input.inputInteger("Ваш выбор: ", 2)) {

            case 1:
                goodsFile = new File(jsonDirectory, "goods.json");
                sellersFile = new File(jsonDirectory, "sellers.json");
                stockFile = new File(jsonDirectory, "stock.json");
                salesFile = new File(jsonDirectory, "sales.json");

                parser = chooseJsonParser();
                break;

            case 2:
                goodsFile = new File(xmlDirectory, "goods.xml");
                sellersFile = new File(xmlDirectory, "sellers.xml");
                stockFile = new File(xmlDirectory, "stock.xml");
                salesFile = new File(xmlDirectory, "sales.xml");

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
                writer.writeTripleMap(mapOne, "Task1.xml", Tag.STOCK, Tag.AMOUNT);
                break;

            case 2:
                Map<Good, Pair<Seller, Integer>> mapTwo = analyzer.getMinPriceAndSellerByGoods();

                // Выводим результат.
                writer.writeTripleMap(mapTwo, "Task2.xml", Tag.STOCK, Tag.PRICE);
                break;

            case 3:
                Map<Good, Integer> mapThree = analyzer.getTotalStockAmountByGoods();

                // Выводим результат.
                writer.writeGoodsMap(mapThree, "Task3.xml", Tag.STOCK, Tag.AMOUNT);
                break;

            case 4:
                Map<Good, Integer> mapFour = analyzer.getTotalSoldAmountByGoods();

                // Выводим результат.
                writer.writeGoodsMap(mapFour, "Task4.xml", Tag.SALES, Tag.AMOUNT);
                break;

            case 5:
                Map<Seller, Integer> mapFive = analyzer.getTopSellers(5);

                // Выводим результат.
                writer.writeSellersMap(mapFive, "Task5.xml", Tag.SALES, Tag.AMOUNT);
                break;

            case 6:
                Map<Good, Integer> mapSix = analyzer.getTopSoldGoods(5);

                // Выводим результат.
                writer.writeGoodsMap(mapSix, "Task6.xml", Tag.SALES, Tag.AMOUNT);
                break;

            case 7:
                Map<LocalDate, Integer> mapSeven = analyzer.getTotalSoldAmountByDates();

                // Выводим результат.
                writer.writeDatesIntegerMap(mapSeven, "Task7.xml", Tag.SALES, Tag.AMOUNT);
                break;

            case 8:
                Map<LocalDate, Integer> mapEight = analyzer.getTopSoldDates(5);

                // Выводим результат.
                writer.writeDatesIntegerMap(mapEight, "Task8.xml", Tag.SALES, Tag.AMOUNT);
                break;

            case 9:
                Map<LocalDate, Double> mapNine = analyzer.getAverageSoldAmountByGoods();

                // Выводим результат.
                writer.writeDatesFloatMap(mapNine, "Task9.xml", Tag.SALES, Tag.AMOUNT);
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
