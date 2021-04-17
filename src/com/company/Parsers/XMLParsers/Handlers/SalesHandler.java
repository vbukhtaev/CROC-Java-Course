package com.company.Parsers.XMLParsers.Handlers;

import com.company.Model.Good;
import com.company.Model.Sale;
import com.company.Model.Seller;
import com.company.Parsers.Tag;
import org.xml.sax.Attributes;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Класс, описывающий обработчик событий для парсера XML файла с продажами,
 * работающего по методу SAX.
 */
public class SalesHandler extends MyHandler {

    /**
     * ID продажи.
     */
    private Integer saleID = null;
    /**
     * ID продавца.
     */
    private Integer sellerID = null;
    /**
     * ID товара.
     */
    private Integer goodID = null;
    /**
     * Количество проданного товара.
     */
    private Integer soldAmount = null;
    /**
     * Дата продажи.
     */
    private LocalDate date = null;

    /**
     * Название текущего тега.
     */
    private Tag currentTag;
    /**
     * Определяет, находимся мы в теге &lt;sale&gt;&lt;/sale&gt; или нет.
     */
    private boolean isSale = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        this.currentTag = Tag.valueOf(qName.toUpperCase());

        // Если наткнулись на тег <sale></sale>.
        if (currentTag.equals(Tag.SALE)) {
            isSale = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        this.currentTag = Tag.valueOf(qName.toUpperCase());

        // Если вышли из тега <sale></sale>.
        if (currentTag.equals(Tag.SALE)) {

            isSale = false;

            Seller seller = this.sellersMap.get(sellerID);
            Objects.requireNonNull(seller, "Продавец с ID = " + sellerID + " не найден!");

            Good good = this.goodsMap.get(goodID);
            Objects.requireNonNull(good, "Товар с ID = " + goodID + " не найден!");

            this.salesMap.put(saleID, new Sale(seller, good, soldAmount, date));
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

        // Если мы в теге <sale></sale> - присваиваем значения переменным.
        if (isSale) {

            switch (currentTag) {
                case ID:
                    saleID = Integer.parseInt(propertyValue);
                    Objects.requireNonNull(saleID, "ID продажи не распознан!");
                    break;

                case SELLER_ID:
                    sellerID = Integer.parseInt(propertyValue);
                    Objects.requireNonNull(sellerID, "ID продавца не распознан!");
                    break;

                case GOOD_ID:
                    goodID = Integer.parseInt(propertyValue);
                    Objects.requireNonNull(goodID, "ID товара не распознан!");
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
}
