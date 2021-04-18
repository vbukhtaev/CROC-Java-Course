package com.company.parsers.xml.handlers;

import com.company.model.Good;
import com.company.parsers.Tag;
import org.xml.sax.Attributes;

import java.util.Objects;

/**
 * Класс, описывающий обработчик событий для парсера XML файла с товарами,
 * работающего по методу SAX.
 */
public class GoodsHandler extends MyHandler {

    /**
     * ID товара.
     */
    private Integer goodID = null;
    /**
     * Название товара.
     */
    private String goodName = null;

    /**
     * Название текущего тега.
     */
    private Tag currentTag;
    /**
     * Определяет, находимся мы в теге &lt;good&gt;&lt;/good&gt; или нет.
     */
    private boolean isGood = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        this.currentTag = Tag.valueOf(qName.toUpperCase());

        // Если наткнулись на тег <good></good>.
        if (currentTag.equals(Tag.GOOD)) {
            isGood = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        this.currentTag = Tag.valueOf(qName.toUpperCase());

        // Если вышли из тега <good></good>.
        if (currentTag.equals(Tag.GOOD)) {

            isGood = false;

            this.goodsMap.put(goodID, new Good(goodName));
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

        // Если мы в теге <good></good> - присваиваем значения переменным.
        if (isGood) {

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
    }
