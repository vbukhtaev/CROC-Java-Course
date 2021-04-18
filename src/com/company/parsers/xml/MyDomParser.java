package com.company.parsers.xml;

import com.company.model.Good;
import com.company.model.Sale;
import com.company.model.Seller;
import com.company.model.StockEntry;
import com.company.parsers.MyParser;
import com.company.parsers.Tag;
import com.company.utilities.Pair;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Класс, описывающий парсер XML файла, работающий по методу DOM.
 */
public class MyDomParser extends MyParser {

    /**
     * Создает парсер XML файла, работающий по методу DOM.
     * @param goods файл с товарами
     * @param sellers файл с продавцами
     * @param stock файл с данными по наличию товаров у продавцов
     * @param sales файл с продажами
     */
    public MyDomParser(File goods, File sellers, File stock, File sales) {
        super(goods, sellers, stock, sales);
    }

    @Override
    public Map<Integer, Good> parseGoods() {

        Document document = buildDocument(this.goods);

        NodeList children = document.getFirstChild().getChildNodes();

        for (int i = 0; i < children.getLength(); i++) {

            Node currentNode = children.item(i);

            // Пропускаем сторонний текст.
            if (currentNode.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            Pair<Integer, Good> entry = parseGood(currentNode);

            this.goodsMap.put(entry.getKey(), entry.getValue());
        }

        return this.goodsMap;
    }

    @Override
    public Map<Integer, Seller> parseSellers() {

        Document document = buildDocument(this.sellers);

        NodeList children = document.getFirstChild().getChildNodes();

        for (int i = 0; i < children.getLength(); i++) {

            Node currentNode = children.item(i);

            // Пропускаем сторонний текст.
            if (currentNode.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            Pair<Integer, Seller> entry = parseSeller(currentNode);

            this.sellersMap.put(entry.getKey(), entry.getValue());
        }
        return this.sellersMap;
    }

    @Override
    public List<StockEntry> parseStock() {

        Document document = buildDocument(this.stock);

        NodeList children = document.getFirstChild().getChildNodes();

        for (int i = 0; i < children.getLength(); i++) {

            Node currentNode = children.item(i);

            // Пропускаем сторонний текст.
            if (currentNode.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            this.stockList.add(parseStockEntry(currentNode));
        }

        return this.stockList;
    }

    @Override
    public Map<Integer, Sale> parseSales() {

        Document document = buildDocument(this.sales);

        NodeList children = document.getFirstChild().getChildNodes();

        for (int i = 0; i < children.getLength(); i++) {

            Node currentNode = children.item(i);

            // Пропускаем сторонний текст.
            if (currentNode.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            Pair<Integer, Sale> entry = parseSale(currentNode);

            this.salesMap.put(entry.getKey(), entry.getValue());
        }

        return this.salesMap;
    }

    /**
     * Считывает ID и название из узла товара и создает пару Pair&lt;Integer, String&gt; ID-название.
     * @param goodNode узел товара
     * @return пару Pair&lt;Integer, String&gt; ID-название
     */
    private Pair<Integer, Good> parseGood(Node goodNode) {

        NodeList propertyList = goodNode.getChildNodes();

        Integer goodID = null;
        String goodName = null;

        for (int j = 0; j < propertyList.getLength(); j++) {

            Node currentNode = propertyList.item(j);

            if (currentNode instanceof Element) {

                String tagName = ((Element) currentNode).getTagName().toUpperCase();
                Tag currentTag = Tag.valueOf(tagName);

                String propertyValue = propertyList.item(j).getTextContent().trim();

                switch (currentTag) {

                    case ID:
                        goodID = Integer.parseInt(propertyValue);
                        Objects.requireNonNull(goodID, "ID товара не распознан!");
                        break;

                    case NAME:
                        goodName = propertyValue;
                        Objects.requireNonNull(goodName, "Название товара не распознано!");
                        break;
                }
            }
        }

        return new Pair<>(goodID, new Good(goodName));
    }

    /**
     * Считывает ID, фамилию и имя из узла продавца и создает пару Pair&lt;Integer, Seller&gt; ID-продавец.
     * @param sellerNode узел продавца
     * @return пару Pair&lt;Integer, Seller&gt; ID-продавец
     */
    private Pair<Integer, Seller> parseSeller(Node sellerNode) {

        NodeList propertyList = sellerNode.getChildNodes();

        Integer sellerID = null;
        String firstName = null;
        String lastName = null;

        for (int j = 0; j < propertyList.getLength(); j++) {

            Node currentNode = propertyList.item(j);

            if (currentNode instanceof Element) {

                String tagName = ((Element) currentNode).getTagName().toUpperCase();
                Tag currentTag = Tag.valueOf(tagName);

                String propertyValue = propertyList.item(j).getTextContent().trim();

                switch (currentTag) {

                    case ID:
                        sellerID = Integer.parseInt(propertyValue);
                        Objects.requireNonNull(sellerID, "ID продавца не распознан!");
                        break;

                    case FIRST_NAME:
                        firstName = propertyValue;
                        Objects.requireNonNull(firstName, "Имя продавца не распознано!");
                        break;

                    case LAST_NAME:
                        lastName = propertyValue;
                        Objects.requireNonNull(lastName, "Фамилия продавца не распознана!");
                        break;
                }
            }
        }

        return new Pair<>(sellerID, new Seller(firstName, lastName));
    }

    /**
     * Считывает ID продавца, ID товара, количество товара и цену товара из узла записи наличия
     * и создает запись наличия StockEntry.
     * @param stockEntryNode узел записи наличия
     * @return запись наличия StockEntry
     */
    private StockEntry parseStockEntry(Node stockEntryNode) {

        NodeList propertyList = stockEntryNode.getChildNodes();

        Seller seller = null;
        Good good = null;
        Integer goodAmount = null;
        Integer goodPrice = null;

        for (int j = 0; j < propertyList.getLength(); j++) {

            Node currentNode = propertyList.item(j);

            if (currentNode instanceof Element) {

                String tagName = ((Element) currentNode).getTagName().toUpperCase();
                Tag currentTag = Tag.valueOf(tagName);

                String propertyValue = propertyList.item(j).getTextContent().trim();

                switch (currentTag) {

                    case SELLER_ID:
                        Integer sellerID = Integer.parseInt(propertyValue);
                        Objects.requireNonNull(sellerID, "ID продавца не распознан!");

                        seller = this.sellersMap.get(sellerID);
                        Objects.requireNonNull(seller, "Продавец с ID = " + sellerID + " не найден!");
                        break;

                    case GOOD_ID:
                        Integer goodID = Integer.parseInt(propertyValue);
                        Objects.requireNonNull(goodID, "ID товара не распознан!");

                        good = this.goodsMap.get(goodID);
                        Objects.requireNonNull(good, "Товар с ID = " + goodID + " не найден!");
                        break;

                    case AMOUNT:
                        goodAmount = Integer.parseInt(propertyValue);
                        Objects.requireNonNull(goodAmount, "Количество товара в наличии не распознано!");
                        break;

                    case PRICE:
                        goodPrice = Integer.parseInt(propertyValue);
                        Objects.requireNonNull(goodPrice, "Цена товара не распознана!");
                        break;
                }
            }
        }

        return new StockEntry(seller, good, goodAmount, goodPrice);
    }

    /**
     * Считывает ID продажи, ID продавца, ID товара, количество товара и дату продажи из узла продажи
     * и создает пару Pair&lt;Integer, Sale&gt; ID-продажа.
     * @param saleNode узел прожади
     * @return пару Pair&lt;Integer, Sale&gt; ID-продажа
     */
    private Pair<Integer, Sale> parseSale(Node saleNode) {

        NodeList propertyList = saleNode.getChildNodes();

        Integer saleID = null;
        Seller seller = null;
        Good good = null;
        Integer soldAmount = null;
        LocalDate date = null;

        for (int j = 0; j < propertyList.getLength(); j++) {

            Node currentNode = propertyList.item(j);

            if (currentNode instanceof Element) {

                String tagName = ((Element) currentNode).getTagName().toUpperCase();
                Tag currentTag = Tag.valueOf(tagName);

                String propertyValue = propertyList.item(j).getTextContent().trim();

                switch (currentTag) {

                    case ID:
                        saleID = Integer.parseInt(propertyValue);
                        Objects.requireNonNull(saleID, "ID продажи не распознан!");
                        break;

                    case SELLER_ID:
                        Integer sellerID = Integer.parseInt(propertyValue);
                        Objects.requireNonNull(sellerID, "ID продавца не распознан!");

                        seller = this.sellersMap.get(sellerID);
                        Objects.requireNonNull(seller, "Продавец с ID = " + sellerID + " не найден!");
                        break;

                    case GOOD_ID:
                        Integer goodID = Integer.parseInt(propertyValue);
                        Objects.requireNonNull(goodID, "ID товара не распознан!");

                        good = this.goodsMap.get(goodID);
                        Objects.requireNonNull(good, "Товар с ID = " + goodID + " не найден!");
                        break;

                    case AMOUNT:
                        soldAmount = Integer.parseInt(propertyValue);
                        Objects.requireNonNull(soldAmount, "Количество проданного товара не распознано!");
                        break;

                    case DATE:
                        date = LocalDate.parse(propertyValue);
                        Objects.requireNonNull(date, "Дата продажи не распознана!");
                        break;
                }
            }
        }

        return new Pair<>(saleID, new Sale(seller, good, soldAmount, date));
    }

    /**
     * Открывает файл для парсинга.
     * @param file - файл для парсинга
     * @return файл, открытый для парсинга
     */
    private Document buildDocument(File file) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document document = null;

        // Открываем файл для парсинга.
        try {
            document = factory.newDocumentBuilder().parse(file);

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

        return document;
    }
}
