package com.company.Parsers.XMLParsers;

import com.company.Model.Good;
import com.company.Model.StockEntry;
import com.company.Model.Sale;
import com.company.Model.Seller;
import com.company.Parsers.XMLParsers.Handlers.*;
import com.company.Parsers.MyParser;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Класс, описывающий парсер XML файла, работающий по методу SAX.
 */
public class MySaxParser extends MyParser {

    /**
     * Создает парсер XML файла, работающий по методу SAX.
     * @param goods файл с товарами
     * @param sellers файл с продавцами
     * @param stock файл с данными по наличию товаров у продавцов
     * @param sales файл с продажами
     */
    public MySaxParser(File goods, File sellers, File stock, File sales) {
        super(goods, sellers, stock, sales);
    }

    @Override
    public Map<Integer, Good> parseGoods() {

        GoodsHandler handler = new GoodsHandler();
        parse(this.goods, handler);

        // Получаем распарсенные товары.
        this.goodsMap = handler.getGoodsMap();
        return handler.getGoodsMap();
    }

    @Override
    public Map<Integer, Seller> parseSellers() {

        SellersHandler handler = new SellersHandler();
        parse(this.sellers, handler);

        // Получаем распарсенных продавцов.
        this.sellersMap = handler.getSellersMap();
        return handler.getSellersMap();
    }

    @Override
    public List<StockEntry> parseStock() {

        StockHandler handler = new StockHandler();

        // Задаем товары и продавцов.
        handler.setGoodsMap(this.goodsMap);
        handler.setSellersMap(this.sellersMap);

        parse(this.stock, handler);
        this.stockList = handler.getStockList();

        return handler.getStockList();
    }

    @Override
    public Map<Integer, Sale> parseSales() {

        SalesHandler handler = new SalesHandler();

        // Задаем товары и продавцов.
        handler.setGoodsMap(this.goodsMap);
        handler.setSellersMap(this.sellersMap);

        parse(this.sales, handler);
        this.salesMap = handler.getSalesMap();

        return handler.getSalesMap();
    }

    /**
     * Возвращает обработчик событий с результатом парсинга внутри него.
     * @param file файл для парсинга
     * @param handler обработчик событий
     */
    private void parse(File file, DefaultHandler handler) {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser;

        try {
            parser = factory.newSAXParser();
            parser.parse(file, handler);

            // TODO переработать обработку исключений.
        } catch (ParserConfigurationException e) {
            System.err.println("Выброшено ParserConfigurationException при попытке распарсить файл " +
                    "\"" + file + "\"!");
            System.exit(-1);

        } catch (SAXException e) {
            System.err.println("Выброшено SAXException при попытке распарсить файл " +
                    "\"" + file + "\"!");
            System.exit(-1);

        } catch (IOException e) {
            System.err.println("Выброшено IOException при попытке распарсить файл " +
                    "\"" + file + "\"!");
            System.exit(-1);
        }

    }
}
