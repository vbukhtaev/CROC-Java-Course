package com.company.Parsers.JSONParsers;

import com.company.Model.Good;
import com.company.Model.Sale;
import com.company.Model.Seller;
import com.company.Model.StockEntry;
import com.company.Parsers.MyParser;
import com.company.Parsers.Tag;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Класс, описывающий парсер JSON файла, использующий библиотеку JSON.simple.
 */
public class MySimpleParser extends MyParser {

    /**
     * Создает парсер JSON файла, использующий библиотеку JSON.simple.
     * @param goods файл с товарами
     * @param sellers файл с продавцами
     * @param stock файл с данными по наличию товаров у продавцов
     * @param sales файл с продажами
     */
    public MySimpleParser(File goods, File sellers, File stock, File sales) {
        super(goods, sellers, stock, sales);
    }

    @Override
    public Map<Integer, Good> parseGoods() {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(this.goods)) {

            JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);

            for (Object item : jsonArray) {

                JSONObject jsonObject = (JSONObject) item;

                Integer goodID = (int) (long) jsonObject.get(Tag.ID.toString());
                Objects.requireNonNull(goodID, "ID товара не распознан!");

                String goodName = (String) jsonObject.get(Tag.NAME.toString());
                Objects.requireNonNull(goodName, "Название товара не распознано!");

                this.goodsMap.put(goodID, new Good(goodName));
            }

            // TODO переработать обработку исключений.
        } catch (FileNotFoundException e) {
            System.err.println("Файл с товарами \"" + this.goods + "\" не найден!");
            System.exit(-1);

        } catch (IOException e) {
            System.err.println("Выброшено IOException при попытке распарсить файл " +
                    "с товарами \"" + this.goods + "\"!");
            System.exit(-1);

        } catch (ParseException e) {
            System.err.println("Выброшено ParseException при попытке распарсить файл " +
                    "с товарами \"" + this.goods + "\"!");
            System.exit(-1);
        }

        return this.goodsMap;
    }

    @Override
    public Map<Integer, Seller> parseSellers() {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(this.sellers)) {

            JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);

            for (Object item : jsonArray) {

                JSONObject jsonObject = (JSONObject) item;

                Integer sellerID = (int) (long) jsonObject.get(Tag.ID.toString());
                Objects.requireNonNull(sellerID, "ID продавца не распознан!");

                String firstName = (String) jsonObject.get(Tag.FIRST_NAME.toString());
                Objects.requireNonNull(firstName, "Имя продавца не распознано!");

                String lastName = (String) jsonObject.get(Tag.LAST_NAME.toString());
                Objects.requireNonNull(lastName, "Фамилия продавца не распознана!");

                this.sellersMap.put(sellerID, new Seller(firstName, lastName));
            }

            // TODO переработать обработку исключений.
        } catch (FileNotFoundException e) {
            System.err.println("Файл с продавцами \"" + this.sellers + "\" не найден!");
            System.exit(-1);

        } catch (IOException e) {
            System.err.println("Выброшено IOException при попытке распарсить файл " +
                    "с продавцами \"" + this.sellers + "\"!");
            System.exit(-1);

        } catch (ParseException e) {
            System.err.println("Выброшено ParseException при попытке распарсить файл " +
                    "с продавцами \"" + this.sellers + "\"!");
            System.exit(-1);
        }

        return this.sellersMap;
    }

    @Override
    public List<StockEntry> parseStock() {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(this.stock)) {

            JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);

            for (Object item : jsonArray) {

                JSONObject jsonObject = (JSONObject) item;

                Integer sellerID = (int) (long) jsonObject.get(Tag.SELLER_ID.toString());
                Objects.requireNonNull(sellerID, "ID продавца не распознан!");
                Seller seller = this.sellersMap.get(sellerID);
                Objects.requireNonNull(seller, "Продавец с ID = " + sellerID + " не найден!");

                Integer goodID = (int) (long) jsonObject.get(Tag.GOOD_ID.toString());
                Objects.requireNonNull(goodID, "ID товара не распознан!");
                Good good = this.goodsMap.get(goodID);
                Objects.requireNonNull(good, "Товар с ID = " + goodID + " не найден!");

                Integer stockAmount = (int) (long) jsonObject.get(Tag.AMOUNT.toString());
                Objects.requireNonNull(stockAmount, "Количество товара в наличии не распознано!");

                Integer goodPrice = (int) (long) jsonObject.get(Tag.PRICE.toString());
                Objects.requireNonNull(goodPrice, "Цена товара не распознана!");

                this.stockList.add(new StockEntry(seller, good, stockAmount, goodPrice));
            }

            // TODO переработать обработку исключений.
        } catch (FileNotFoundException e) {
            System.err.println("Файл с информацией о наличии товаров \"" + this.stock + "\" не найден!");
            System.exit(-1);

        } catch (IOException e) {
            System.err.println("Выброшено IOException при попытке распарсить файл " +
                    "с информацией о наличии товаров \"" + this.stock + "\"!");
            System.exit(-1);

        } catch (ParseException e) {
            System.err.println("Выброшено ParseException при попытке распарсить файл " +
                    "с информацией о наличии товаров \"" + this.stock + "\"!");
            System.exit(-1);
        }

        return this.stockList;
    }

    @Override
    public Map<Integer, Sale> parseSales() {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(this.sales)) {

            JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);

            for (Object item : jsonArray) {

                JSONObject jsonObject = (JSONObject) item;

                Integer saleID = (int) (long) jsonObject.get(Tag.ID.toString());
                Objects.requireNonNull(saleID, "ID продажи не распознан!");

                Integer sellerID = (int) (long) jsonObject.get(Tag.SELLER_ID.toString());
                Objects.requireNonNull(sellerID, "ID продавца не распознан!");
                Seller seller = this.sellersMap.get(sellerID);
                Objects.requireNonNull(seller, "Продавец с ID = " + sellerID + " не найден!");

                Integer goodID = (int) (long) jsonObject.get(Tag.GOOD_ID.toString());
                Objects.requireNonNull(goodID, "ID товара не распознан!");
                Good good = this.goodsMap.get(goodID);
                Objects.requireNonNull(good, "Товар с ID = " + goodID + " не найден!");

                Integer soldAmount = (int) (long) jsonObject.get(Tag.AMOUNT.toString());
                Objects.requireNonNull(soldAmount, "Количество проданного товара не распознано!");

                LocalDate date = LocalDate.parse((String) jsonObject.get(Tag.DATE.toString()));
                Objects.requireNonNull(date, "Дата продажи не распознана!");

                this.salesMap.put(saleID, new Sale(seller, good, soldAmount, date));
            }

            // TODO переработать обработку исключений.
        } catch (FileNotFoundException e) {
            System.err.println("Файл с продажами \"" + this.sales + "\" не найден!");
            System.exit(-1);

        } catch (IOException e) {
            System.err.println("Выброшено IOException при попытке распарсить файл " +
                    "с продажами \"" + this.sales + "\"!");
            System.exit(-1);

        } catch (ParseException e) {
            System.err.println("Выброшено ParseException при попытке распарсить файл " +
                    "с продажами \"" + this.sales + "\"!");
            System.exit(-1);
        }

        return this.salesMap;
    }
}