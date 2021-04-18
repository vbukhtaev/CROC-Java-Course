package com.company.parsers;

import com.company.model.Good;
import com.company.model.Sale;
import com.company.model.Seller;
import com.company.model.StockEntry;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс, описывающий парсер файла.
 */
public abstract class MyParser {

    /**
     * Файл с товарами.
     */
    protected final File goods;
    /**
     * Файл с продавцами.
     */
    protected final File sellers;
    /**
     * Файл с данными по наличию товаров у продавцов.
     */
    protected final File stock;
    /**
     * Файл с продажами.
     */
    protected final File sales;

    /**
     * Ассоциативный массив товаров.
     */
    protected Map<Integer, Good> goodsMap = new HashMap<>();
    /**
     * Ассоциативный массив продавцов.
     */
    protected Map<Integer, Seller> sellersMap = new HashMap<>();
    /**
     * Список записей наличия.
     */
    protected List<StockEntry> stockList = new ArrayList<>();
    /**
     * Ассоциативный массив продаж.
     */
    protected Map<Integer, Sale> salesMap = new HashMap<>();


    /**
     * Создает парсер файла.
     * @param goods файл с товарами
     * @param sellers файл с продавцами
     * @param stock файл с данными по наличию товаров у продавцов
     * @param sales файл с продажами
     */
    public MyParser(File goods, File sellers, File stock, File sales) {
        this.goods = goods;
        this.sellers = sellers;
        this.stock = stock;
        this.sales = sales;
    }

    /**
     * Считывает из файла ассоциативный массив товаров.
     * @return ассоциативный массив товаров
     */
    public abstract Map<Integer, Good> parseGoods();

    /**
     * Считывает из файла ассоциативный массив продавцов.
     * @return ассоциативный массив продавцов
     */
    public abstract Map<Integer, Seller> parseSellers();

    /**
     * Считывает из файла список записей наличия.
     * @return список записей наличия
     */
    public abstract List<StockEntry> parseStock();

    /**
     * Считывает из файла ассоциативный массив продаж.
     * @return ассоциативный массив продаж
     */
    public abstract Map<Integer, Sale> parseSales();
}
