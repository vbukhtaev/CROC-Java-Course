package com.company.parsers.xml.handlers;

import com.company.model.Seller;
import com.company.parsers.Tag;
import org.xml.sax.Attributes;

import java.util.Objects;

/**
 * Класс, описывающий обработчик событий для парсера XML файла с продавцами,
 * работающего по методу SAX.
 */
public class SellersHandler extends MyHandler {

    /**
     * ID продавца.
     */
    private Integer sellerID = null;
    /**
     * Имя продавца.
     */
    private String firstName = null;
    /**
     * Фамилия продавца.
     */
    private String lastName = null;

    /**
     * Название текущего тега.
     */
    private Tag currentTag;
    /**
     * Определяет, находимся мы в теге &lt;seller&gt;&lt;/seller&gt; или нет.
     */
    private boolean isSeller = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        this.currentTag = Tag.valueOf(qName.toUpperCase());

        // Если наткнулись на тег <seller></seller>.
        if (currentTag.equals(Tag.SELLER)) {
            isSeller = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        this.currentTag = Tag.valueOf(qName.toUpperCase());

        // Если вышли из тега <seller></seller>.
        if (currentTag.equals(Tag.SELLER)) {

            isSeller = false;

            this.sellersMap.put(sellerID, new Seller(firstName, lastName));
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

        // Если мы в теге <seller></seller> - присваиваем значения переменным.
        if (isSeller) {

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
}

