package com.company.writers;

import com.company.model.Good;
import com.company.model.Seller;
import com.company.parsers.Tag;
import com.company.utilities.Pair;
import com.sun.xml.txw2.output.IndentingXMLStreamWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * Класс, описывающий сущность, записывающую результаты заданий в XML файл методом SAX.
 */
public class MySaxWriter {

    /**
     * Шаблон для приведения даты к строке.
     */
    private static final DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("dd MMMM yyyy");

    /**
     * Шаблон для форматирования чисел с плавающей запятой.
     */
    private static final DecimalFormat floatPattern = new DecimalFormat( "##.##" );

    /**
     * Путь к директории с выходными файлами.
     */
    private static final String directory = "src\\com\\company\\Files\\Output";

    /**
     * Записывает в файл ассоциативный массив, ключами которого являются объекты класса {@code Good},
     * а значениями - пары ключ-значение,
     * где ключи - объекты класса {@code Seller} и значения - объекты класса {@code Integer}.
     *
     * @param map ассоциативный массив
     * @param fileName название выходного файла
     * @param rootTag название тега корневого элемента
     * @param integerTag название тега для целочисленного значения
     */
    public void writeTripleMap(Map<Good, Pair<Seller, Integer>> map, String fileName, Tag rootTag, Tag integerTag) {

        File file = new File(directory, fileName);
        XMLStreamWriter writer = getWriter(file);

        try {
            writer.writeStartDocument();
            writer.writeStartElement(rootTag.toString());

            for (Map.Entry<Good, Pair<Seller, Integer>> entry : map.entrySet()) {

                writer.writeStartElement(Tag.ENTRY.toString());

                writeGood(writer, entry.getKey());
                writeSeller(writer, entry.getValue().getKey());
                writeInteger(writer, entry.getValue().getValue(), integerTag);

                writer.writeEndElement();
            }

            writer.writeEndElement();
            writer.writeEndDocument();

        } catch (XMLStreamException e) {
            System.err.println("Выброшено XMLStreamException при попытке записи в файл " +
                    "\"" + file + "\"!");
            System.exit(-1);
        }
    }

    /**
     * Записывает в файл ассоциативный массив, ключами которого являются объекты класса {@code Good},
     * а значениями - объекты класса {@code Integer}.
     *
     * @param map ассоциативный массив
     * @param fileName название выходного файла
     * @param rootTag название тега корневого элемента
     * @param integerTag название тега для целочисленного значения
     */
    public void writeGoodsMap(Map<Good, Integer> map, String fileName, Tag rootTag, Tag integerTag) {

        File file = new File(directory, fileName);
        XMLStreamWriter writer = getWriter(file);

        try {
            writer.writeStartDocument();
            writer.writeStartElement(rootTag.toString());

            for (Map.Entry<Good, Integer> entry : map.entrySet()) {

                writer.writeStartElement(Tag.ENTRY.toString());

                writeGood(writer, entry.getKey());
                writeInteger(writer, entry.getValue(), integerTag);

                writer.writeEndElement();
            }

            writer.writeEndElement();
            writer.writeEndDocument();

        } catch (XMLStreamException e) {
            System.err.println("Выброшено XMLStreamException при попытке записи в файл " +
                    "\"" + file + "\"!");
            System.exit(-1);
        }
    }

    /**
     * Записывает в файл ассоциативный массив, ключами которого являются объекты класса {@code Seller},
     * а значениями - объекты класса {@code Integer}.
     *
     * @param map ассоциативный массив
     * @param fileName название выходного файла
     * @param rootTag название тега корневого элемента
     * @param integerTag название тега для целочисленного значения
     */
    public void writeSellersMap(Map<Seller, Integer> map, String fileName, Tag rootTag, Tag integerTag) {

        File file = new File(directory, fileName);
        XMLStreamWriter writer = getWriter(file);

        try {
            writer.writeStartDocument();
            writer.writeStartElement(rootTag.toString());

            for (Map.Entry<Seller, Integer> entry : map.entrySet()) {

                writer.writeStartElement(Tag.ENTRY.toString());

                writeSeller(writer, entry.getKey());
                writeInteger(writer, entry.getValue(), integerTag);

                writer.writeEndElement();
            }

            writer.writeEndElement();
            writer.writeEndDocument();

        } catch (XMLStreamException e) {
            System.err.println("Выброшено XMLStreamException при попытке записи в файл " +
                    "\"" + file + "\"!");
            System.exit(-1);
        }
    }

    /**
     * Записывает в файл ассоциативный массив, ключами которого являются объекты класса {@code LocalDate},
     * а значениями - объекты класса {@code Integer}.
     *
     * @param map ассоциативный массив
     * @param fileName название выходного файла
     * @param rootTag название тега корневого элемента
     * @param integerTag название тега для целочисленного значения
     */
    public void writeDatesIntegerMap(Map<LocalDate, Integer> map, String fileName, Tag rootTag, Tag integerTag) {

        File file = new File(directory, fileName);
        XMLStreamWriter writer = getWriter(file);

        try {
            writer.writeStartDocument();
            writer.writeStartElement(rootTag.toString());

            for (Map.Entry<LocalDate, Integer> entry : map.entrySet()) {

                writer.writeStartElement(Tag.ENTRY.toString());

                writeDate(writer, entry.getKey());
                writeInteger(writer, entry.getValue(), integerTag);

                writer.writeEndElement();
            }

            writer.writeEndElement();
            writer.writeEndDocument();

        } catch (XMLStreamException e) {
            System.err.println("Выброшено XMLStreamException при попытке записи в файл " +
                    "\"" + file + "\"!");
            System.exit(-1);
        }
    }

    /**
     * Записывает в файл ассоциативный массив, ключами которого являются объекты класса {@code LocalDate},
     * а значениями - объекты класса {@code Double}.
     *
     * @param map ассоциативный массив
     * @param fileName название выходного файла
     * @param rootTag название тега корневого элемента
     * @param floatTag название тега для значения с плавающей запятой
     */
    public void writeDatesFloatMap(Map<LocalDate, Double> map, String fileName, Tag rootTag, Tag floatTag) {

        File file = new File(directory, fileName);
        XMLStreamWriter writer = getWriter(file);

        try {
            writer.writeStartDocument();
            writer.writeStartElement(rootTag.toString());

            for (Map.Entry<LocalDate, Double> entry : map.entrySet()) {

                writer.writeStartElement(Tag.ENTRY.toString());

                writeDate(writer, entry.getKey());
                writeDouble(writer, entry.getValue(), floatTag);

                writer.writeEndElement();
            }

            writer.writeEndElement();
            writer.writeEndDocument();

        } catch (XMLStreamException e) {
            System.err.println("Выброшено XMLStreamException при попытке записи в файл " +
                    "\"" + file + "\"!");
            System.exit(-1);
        }
    }

    /**
     * Записывает в файл тег &lt;date&gt;&lt;/date&gt;, контентом которого является переданная дата.
     *
     * @param writer записывающий объект
     * @param localDate дата
     * @throws XMLStreamException
     */
    private void writeDate(XMLStreamWriter writer, LocalDate localDate) throws XMLStreamException {
        writer.writeStartElement(Tag.DATE.toString());
        writer.writeCharacters(localDate.format(datePattern));
        writer.writeEndElement();
    }

    /**
     * Записывает в файл тег &lt;good&gt;&lt;/good&gt;, контентом которого является переданный товар.
     *
     * @param writer записывающий объект
     * @param good товар
     * @throws XMLStreamException
     */
    private void writeGood(XMLStreamWriter writer, Good good) throws XMLStreamException {
        writer.writeStartElement(Tag.GOOD.toString());
        writer.writeCharacters(good.toString());
        writer.writeEndElement();
    }

    /**
     * Записывает в файл тег &lt;seller&gt;&lt;/seller&gt;, контентом которого является переданный продавец.
     *
     * @param writer записывающий объект
     * @param seller продавец
     * @throws XMLStreamException
     */
    private void writeSeller(XMLStreamWriter writer, Seller seller) throws XMLStreamException {
        writer.writeStartElement(Tag.SELLER.toString());
        writer.writeCharacters(seller.toString());
        writer.writeEndElement();
    }

    /**
     * Записывает в файл тег,
     * контентом которого является переданное целое число.
     *
     * @param writer записывающий объект
     * @param number целое число
     * @param tag название тега для целочисленного значения
     * @throws XMLStreamException
     */
    private void writeInteger(XMLStreamWriter writer, Integer number, Tag tag) throws XMLStreamException {
        writer.writeStartElement(tag.toString());
        writer.writeCharacters(number.toString());
        writer.writeEndElement();
    }

    /**
     * Записывает в файл тег,
     * контентом которого является переданное число с плавающей запятой.
     *
     * @param writer записывающий объект
     * @param number число с плавающей запятой
     * @param tag название тега для значения с плавающей запятой
     * @throws XMLStreamException
     */
    private void writeDouble(XMLStreamWriter writer, Double number, Tag tag) throws XMLStreamException {
        writer.writeStartElement(tag.toString());
        writer.writeCharacters(floatPattern.format(number));
        writer.writeEndElement();
    }

    /**
     * Возвращает объект, записывающий результаты заданий в файл.
     *
     * @param file файл для записи
     * @return объект, записывающий результаты заданий в файл
     */
    private XMLStreamWriter getWriter(File file) {
        XMLOutputFactory factory = XMLOutputFactory.newFactory();
        XMLStreamWriter writer = null;

        try {
            writer = new IndentingXMLStreamWriter(factory.createXMLStreamWriter(new FileOutputStream(file)));

        } catch (XMLStreamException e) {
            System.err.println("Выброшено XMLStreamException при попытке записи в файл " +
                    "\"" + file + "\"!");
            System.exit(-1);

        } catch (FileNotFoundException e) {
            System.err.println("Выброшено FileNotFoundException при попытке записи в файл " +
                    "\"" + file + "\"!");
            System.exit(-1);
        }

        return writer;
    }
}
