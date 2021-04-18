package com.company.parsers.xml.handlers;

import com.company.model.Good;
import com.company.model.Seller;
import com.company.model.StockEntry;
import com.company.parsers.Tag;
import org.xml.sax.Attributes;

import java.util.Objects;

/**
 * Класс, описывающий обработчик событий для парсера XML файла
 * с данными по наличию товаров у продавцов, работающего по методу SAX.
 */
public class StockHandler extends MyHandler {

    /**
     * ID продавца.
     */
    private Integer sellerID = null;
    /**
     * ID товара.
     */
    private Integer goodID = null;
    /**
     * Количество товара в наличии.
     */
    private Integer goodAmount = null;
    /**
     * Цена товара.
     */
    private Integer goodPrice = null;

    /**
     * Название текущего тега.
     */
    private Tag currentTag;
    /**
     * Определяет, находимся мы в теге &lt;entry&gt;&lt;/entry&gt; или нет.
     */
    private boolean isEntry = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        this.currentTag = Tag.valueOf(qName.toUpperCase());

        // Если наткнулись на тег <entry></entry>.
        if (currentTag.equals(Tag.ENTRY)) {
            isEntry = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        this.currentTag = Tag.valueOf(qName.toUpperCase());

        // Если вышли из тега <entry></entry>.
        if (currentTag.equals(Tag.ENTRY)) {

            isEntry = false;

            Seller seller = this.sellersMap.get(sellerID);
            Objects.requireNonNull(seller, "Продавец с ID = " + sellerID + " не найден!");

            Good good = this.goodsMap.get(goodID);
            Objects.requireNonNull(good, "Товар с ID = " + goodID + " не найден!");

            this.stockList.add(new StockEntry(seller, good, goodAmount, goodPrice));
        }

        this.currentTag = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) {

        if (currentTag == null) {
            return;
        }

        // Считываем контент тега.
        String propertyValue = new String(ch, start, length).trim();

        // Если мы в теге <entry></entry> - присваиваем значения переменным.
        if (isEntry) {

            switch (currentTag) {
                case SELLER_ID:
                    sellerID = Integer.parseInt(propertyValue);
                    Objects.requireNonNull(sellerID, "ID продавца не распознан!");
                    break;

                case GOOD_ID:
                    goodID = Integer.parseInt(propertyValue);
                    Objects.requireNonNull(goodID, "ID товара не распознан!");
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
}
