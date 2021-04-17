package com.company.Parsers.XMLParsers.Handlers;

import com.company.Model.Good;
import com.company.Model.Sale;
import com.company.Model.Seller;
import com.company.Model.StockEntry;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс, описывающий обработчик событий для парсера XML файла, работающего по методу SAX.
 */
public class MyHandler extends DefaultHandler {

    /**
     * Ассоциативный массив товаров.
     */
    protected Map<Integer, Good> goodsMap = new HashMap<>();

    /**
     * Ассоциативный массив продавцов.
     */
    protected Map<Integer, Seller> sellersMap = new HashMap<>();

    /**
     * Список записей наличия товаров.
     */
    protected List<StockEntry> stockList = new ArrayList<>();

    /**
     * Ассоциативный массив продаж.
     */
    protected Map<Integer, Sale> salesMap = new HashMap<>();

    /**
     * Возвращает ассоциативный массив товаров.
     *
     * @return ассоциативный массив товаров
     */
    public Map<Integer, Good> getGoodsMap() {
        return goodsMap;
    }

    /**
     * Задает ассоциативный массив товаров.
     */
    public void setGoodsMap(Map<Integer, Good> goodsMap) {
        this.goodsMap = goodsMap;
    }

    /**
     * Возвращает ассоциативный массив продавцов.
     *
     * @return ассоциативный массив продавцов
     */
    public Map<Integer, Seller> getSellersMap() {
        return sellersMap;
    }

    /**
     * Задает ассоциативный массив продавцов.
     */
    public void setSellersMap(Map<Integer, Seller> sellersMap) {
        this.sellersMap = sellersMap;
    }

    /**
     * Возвращает список записей наличия.
     *
     * @return список записей наличия
     */
    public List<StockEntry> getStockList() {
        return stockList;
    }

    /**
     * Возвращает ассоциативный массив продаж.
     *
     * @return ассоциативный массив продаж
     */
    public Map<Integer, Sale> getSalesMap() {
        return salesMap;
    }
}
