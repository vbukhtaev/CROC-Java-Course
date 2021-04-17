package com.company;

import com.company.Model.Good;
import com.company.Model.Sale;
import com.company.Model.Seller;
import com.company.Model.StockEntry;
import com.company.Utilities.Pair;

import java.time.LocalDate;
import java.util.*;

/**
 * Класс, описывающий анализатор данных.
 */
public class DataAnalyzer {

    /**
     * Ассоциативный товаров.
     */
    private final Map<Integer, Good> goodsMap;
    /**
     * Ассоциативный массив продавцов.
     */
    private final Map<Integer, Seller> sellersMap;
    /**
     * Список записей наличия товаров.
     */
    private final List<StockEntry> stockList;
    /**
     * Ассоциативный массив продаж.
     */
    private final Map<Integer, Sale> salesMap;

    /**
     * Создает анализатор данных.
     *
     * @param goodsMap ассоциативный массив товаров
     * @param sellersMap ассоциативный массив продавцов
     * @param stockList список записей наличия
     * @param salesMap  ассоциативный массив продаж
     */
    public DataAnalyzer(
            Map<Integer, Good> goodsMap,
            Map<Integer, Seller> sellersMap,
            List<StockEntry> stockList,
            Map<Integer, Sale> salesMap
    ) {
        this.goodsMap = goodsMap;
        this.sellersMap = sellersMap;
        this.stockList = stockList;
        this.salesMap = salesMap;
    }

    /**
     * <p>Задание 1.</p>
     * Для каждого товара возвращает продавца, у которого в наличии наибольшее количество этого товара,
     * и само количество этого товара у него в наличии.
     *
     * @return ассоциативный массив, ключами которого являются товары,
     * а значения представляют из себя пару &lt;продавец-максимальное количество товара&gt;
     */
    public Map<Good, Pair<Seller, Integer>> getMaxStockAndSellerByGoods() {

        Map<Good, Pair<Seller, Integer>> maxStockMap = new HashMap<>();

        // Считаем суммарное количество товара в наличии для каждого продавца.
        this.stockList.forEach(stockEntry -> {

            // Текущий товар.
            Good currentGood = stockEntry.getGood();

            // Текущий продавец.
            Seller currentSeller = stockEntry.getSeller();

            // Текущее количество товара в наличии.
            Integer currentAmount = stockEntry.getGoodAmount();

            // Пара с начальным значением количества товара.
            Pair<Seller, Integer> defaultPair = new Pair<>(currentSeller, Integer.MIN_VALUE);

            // Старое количество товара в наличии.
            Integer oldAmount = maxStockMap.getOrDefault(currentGood, defaultPair).getValue();

            if (currentAmount > oldAmount) {
                maxStockMap.put(currentGood, new Pair<>(currentSeller, currentAmount));
            }
        });

        return maxStockMap;
    }

    /**
     * <p>Задание 2.</p>
     * Для каждого товара возвращает продавца, у которого наименьшая цена на этот товар,
     * и саму цену на этот товар у этого продавца.
     *
     * @return ассоциативный массив, ключами которого являются товары,
     * а значения представляют из себя пару &lt;продавец-минимальная цена товара&gt;
     */
    public Map<Good, Pair<Seller, Integer>> getMinPriceAndSellerByGoods() {

        Map<Good, Pair<Seller, Integer>> minPriceMap = new HashMap<>();

        this.stockList.forEach(stockEntry -> {

            // Текущий товар.
            Good currentGood = stockEntry.getGood();

            // Текущий продавец.
            Seller currentSeller = stockEntry.getSeller();

            // Текущая цена на товар.
            Integer currentPrice = stockEntry.getGoodPrice();

            // Пара с начальным значением количества товара.
            Pair<Seller, Integer> defaultPair = new Pair<>(currentSeller, Integer.MAX_VALUE);

            // Старая цена на товар.
            Integer oldPrice = minPriceMap.getOrDefault(currentGood, defaultPair).getValue();

            if (currentPrice < oldPrice) {
                minPriceMap.put(currentGood, new Pair<>(currentSeller, currentPrice));
            }
        });

        return minPriceMap;
    }

    /**
     * <p>Задание 3.</p>
     * Для каждого товара возвращает суммарное количество товара этого типа в наличии.
     *
     * @return ассоциативный массив, ключами которого являются товары,
     * а значениями - суммарное количество этого товара в наличии
     */
    public Map<Good, Integer> getTotalStockAmountByGoods() {

        Map<Good, Integer> totalStockAmount = new HashMap<>();

        this.stockList.forEach(stockEntry -> {

            // Текущий товар.
            Good currentGood = stockEntry.getGood();

            Integer oldAmount = totalStockAmount.getOrDefault(currentGood, 0);

            // Суммируем старое и текущее количество товара в наличии.
            Integer newAmount = oldAmount + stockEntry.getGoodAmount();

            totalStockAmount.put(currentGood, newAmount);
        });

        return totalStockAmount;
    }

    /**
     * <p>Задание 4.</p>
     * Для каждого товара возвращает суммарное количество проданных товаров этого типа.
     *
     * @return ассоциативный массив, ключами которого являются товары,
     * а значениями - суммарное количество проданных товаров этого типа
     */
    public Map<Good, Integer> getTotalSoldAmountByGoods() {

        Map<Good, Integer> totalSoldAmount = new HashMap<>();

        this.salesMap.values().forEach(sale -> {

            // Текущий товар.
            Good currentGood = sale.getGood();

            Integer oldAmount = totalSoldAmount.getOrDefault(currentGood, 0);

            // Суммируем старое и текущее количество проданного товара.
            Integer newAmount = oldAmount + sale.getSoldAmount();

            totalSoldAmount.put(currentGood, newAmount);
        });

        return totalSoldAmount;
    }

    /**
     * <p>Задание 5.</p>
     * Возвращает топ продавцов, продавших наибольшее количество товаров,
     * в количестве {@code count}.
     *
     * @param count требуемое количество продавцов
     * @return ассоциативный массив, ключами которого являются продавцы,
     * а значениями - суммарное количество проданного ими товара
     */
    public Map<Seller, Integer> getTopSellers(int count) {

        if (count <= 0) {
            throw new IllegalArgumentException("Количество продавцов не положительно : " + count);
        }

        Map<Seller, Integer> totalSoldAmount = new HashMap<>();
        Map<Seller, Integer> topSellers = new LinkedHashMap<>();

        // Считаем суммарное количество проданного товара для каждого продавца.
        this.salesMap.values().forEach(sale -> {

            // Текущий продавец.
            Seller currentSeller = sale.getSeller();

            Integer oldAmount = totalSoldAmount.getOrDefault(currentSeller, 0);

            // Суммируем старое и текущее количество проданного товара.
            Integer newAmount = oldAmount + sale.getSoldAmount();

            totalSoldAmount.put(currentSeller, newAmount);
        });

        // Отбираем самых продуктивных продавцов в количестве count.
        totalSoldAmount.entrySet().stream()
                // Сортируем по убыванию числа продаж.
                .sorted((entry1, entry2) -> (entry1.getValue() - entry2.getValue()) * (-1))
                .limit(count)
                .forEach(entry -> topSellers.put(entry.getKey(), entry.getValue()));

        return topSellers;
    }

    /**
     * <p>Задание 6.</p>
     * Возвращает топ товаров с наибольшим количеством продаж, в количестве {@code count}.
     *
     * @param count требуемое количество товаров
     * @return ассоциативный массив, ключами которого являются товары,
     * а значениями - суммарное количество продаж этого товара
     */
    public Map<Good, Integer> getTopSoldGoods(int count) {

        if (count <= 0) {
            throw new IllegalArgumentException("Количество товаров не положительно : " + count);
        }

        Map<Good, Integer> totalSoldAmount = new HashMap<>();
        Map<Good, Integer> topGoods = new LinkedHashMap<>();

        // Считаем суммарное количество проданного товара для каждого типа товара.
        this.salesMap.values().forEach(sale -> {

            // Текущий товар.
            Good currentGood = sale.getGood();

            Integer oldAmount = totalSoldAmount.getOrDefault(currentGood, 0);

            // Суммируем старое и текущее количество проданного товара.
            Integer newAmount = oldAmount + sale.getSoldAmount();

            totalSoldAmount.put(currentGood, newAmount);
        });

        // Отбираем самые продаваемые товары в количестве count.
        totalSoldAmount.entrySet().stream()
                // Сортируем по убыванию числа продаж.
                .sorted((entry1, entry2) -> (entry1.getValue() - entry2.getValue()) * (-1))
                .limit(count)
                .forEach(entry -> topGoods.put(entry.getKey(), entry.getValue()));

        return topGoods;
    }

    /**
     * <p>Задание 7.</p>
     * Возвращает суммарное количество проданных товаров в день.
     *
     * @return ассоциативный массив, ключами которого являются даты,
     * а значениями - суммарное количество проданных товаров
     */
    public Map<LocalDate, Integer> getTotalSoldAmountByDates() {

        Map<LocalDate, Integer> totalSoldAmount = new TreeMap<>();

        this.salesMap.values().forEach(sale -> {

            // Текущая дата.
            LocalDate currentDate = sale.getSaleDate();

            Integer oldAmount = totalSoldAmount.getOrDefault(currentDate, 0);

            // Суммирует старое и текущее количество проданного товара.
            Integer newAmount = oldAmount + sale.getSoldAmount();

            totalSoldAmount.put(currentDate, newAmount);
        });

        return totalSoldAmount;
    }

    /**
     * <p>Задание 8.</p>
     * Возвращает топ дней, в которые было продано наибольшее количество товаров, в количестве {@code count}.
     *
     * @param count требуемое количество дат.
     * @return ассоциативный массив, ключами которого являются даты,
     * а значениями - суммарное количество проданных товаров
     */
    public Map<LocalDate, Integer> getTopSoldDates(int count) {

        if (count <= 0) {
            throw new IllegalArgumentException("Количество дат не положительно : " + count);
        }

        Map<LocalDate, Integer> totalSoldAmount = new HashMap<>();
        Map<LocalDate, Integer> topDates = new LinkedHashMap<>();

        this.salesMap.values().forEach(sale -> {

            // Текущая дата.
            LocalDate currentDate = sale.getSaleDate();

            Integer oldAmount = totalSoldAmount.getOrDefault(currentDate, 0);

            // Суммирует старое и текущее количество проданного товара.
            Integer newAmount = oldAmount + sale.getSoldAmount();

            totalSoldAmount.put(currentDate, newAmount);
        });

        // Отбираем самые продаваемые товары в количестве count.
        totalSoldAmount.entrySet().stream()
                // Сортируем по убыванию числа продаж.
                .sorted((entry1, entry2) -> (entry1.getValue() - entry2.getValue()) * (-1))
                .limit(count)
                .forEach(entry -> topDates.put(entry.getKey(), entry.getValue()));

        return topDates;
    }

    /**
     * <p>Задание 9.</p>
     * Возвращает среднее количество проданных товаров в день.
     *
     * @return ассоциативный массив, ключами которого являются даты, а значениями - среднее количество проданных товаров
     */
    public Map<LocalDate, Double> getAverageSoldAmountByGoods() {

        Map<LocalDate, Integer> count = new HashMap<>();
        Map<LocalDate, Double> aveAmount = new TreeMap<>();

        // Считаем количество записей для каждой даты.
        this.salesMap.values().forEach(sale -> {

            LocalDate date = sale.getSaleDate();
            Integer newCount = count.getOrDefault(date, 0) + 1;
            count.put(date, newCount);
        });

        // Считаем среднее количество проданного товара для каждой даты.
        this.salesMap.values()
                .forEach(sale -> {
                    LocalDate date = sale.getSaleDate();
                    Double currentAmount = aveAmount.getOrDefault(date, 0.0);
                    Double newAmount = currentAmount + sale.getSoldAmount() / (double) count.get(date);
                    aveAmount.put(date, newAmount);
                });

        return aveAmount;
    }
}
