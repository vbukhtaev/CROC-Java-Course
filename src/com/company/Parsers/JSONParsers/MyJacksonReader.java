package com.company.Parsers.JSONParsers;

import com.company.Model.Good;
import com.company.Model.POJO.GoodPojo;
import com.company.Model.POJO.SalePojo;
import com.company.Model.POJO.SellerPojo;
import com.company.Model.POJO.StockEntryPojo;
import com.company.Model.Sale;
import com.company.Model.Seller;
import com.company.Model.StockEntry;
import com.company.Parsers.MyParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Класс, описывающий парсер JSON файла, использующий библиотеку Jackson.
 */
public class MyJacksonReader extends MyParser {

    /**
     * Создает парсер JSON файла, использующий библиотеку Jackson.
     * @param goods файл с товарами
     * @param sellers файл с продавцами
     * @param stock файл с данными по наличию товаров у продавцов
     * @param sales файл с продажами
     */
    public MyJacksonReader(File goods, File sellers, File stock, File sales) {
        super(goods, sellers, stock, sales);
    }

    @Override
    public Map<Integer, Good> parseGoods() {

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        List<GoodPojo> goodPojos = new ArrayList<>();

        try {
            goodPojos = mapper.readValue(this.goods , new TypeReference<List<GoodPojo>>(){});

            // TODO переработать обработку исключений
        } catch (IOException e) {
            System.err.println("Выброшено IOException при попытке распарсить файл " +
                    "с товарами \"" + this.goods + "\"!");
            System.exit(-1);
        }

        goodPojos.forEach(entry -> {

            Integer goodID = entry.getId();
            Objects.requireNonNull(goodID, "ID товара не распознан!");

            String goodName = entry.getName();
            Objects.requireNonNull(goodName, "Название товара не распознано!");

            this.goodsMap.put(entry.getId(), new Good(entry.getName()));
        });

        return this.goodsMap;
    }

    @Override
    public Map<Integer, Seller> parseSellers() {

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        List<SellerPojo> sellerPojos = new ArrayList<>();

        try {
            sellerPojos = mapper.readValue(this.sellers , new TypeReference<List<SellerPojo>>(){});

            // TODO переработать обработку исключений
        } catch (IOException e) {
            System.err.println("Выброшено IOException при попытке распарсить файл " +
                    "с продавцами \"" + this.sellers + "\"!");
            System.exit(-1);
        }

        sellerPojos.forEach(entry -> {

            Integer sellerID = entry.getId();
            Objects.requireNonNull(sellerID, "ID продавца не распознан!");

            String firstName = entry.getFirstName();
            Objects.requireNonNull(firstName, "Имя продавца не распознано!");

            String lastName = entry.getLastName();
            Objects.requireNonNull(lastName, "Фамилия продавца не распознана!");

            this.sellersMap.put(entry.getId(), new Seller(entry.getFirstName(), entry.getLastName()));
        });

        return this.sellersMap;
    }

    @Override
    public List<StockEntry> parseStock() {

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        List<StockEntryPojo> stockPojos = new ArrayList<>();

        try {
            stockPojos = mapper.readValue(this.stock , new TypeReference<List<StockEntryPojo>>(){});

            // TODO переработать обработку исключений
        } catch (IOException e) {
            System.err.println("Выброшено IOException при попытке распарсить файл " +
                    "с информацией о наличии товаров \"" + this.stock + "\"!");
            System.exit(-1);
        }

        stockPojos.forEach(entry -> {

            Integer sellerID = entry.getSellerID();
            Objects.requireNonNull(sellerID, "ID продавца не распознан!");
            Seller seller = this.sellersMap.get(sellerID);
            Objects.requireNonNull(seller, "Продавец с ID = " + sellerID + " не найден!");

            Integer goodID = entry.getGoodID();
            Objects.requireNonNull(goodID, "ID товара не распознан!");
            Good good = this.goodsMap.get(goodID);
            Objects.requireNonNull(good, "Товар с ID = " + goodID + " не найден!");

            Integer stockAmount = entry.getGoodAmount();
            Objects.requireNonNull(stockAmount, "Количество товара в наличии не распознано!");

            Integer goodPrice = entry.getGoodPrice();
            Objects.requireNonNull(goodPrice, "Цена товара не распознана!");

            this.stockList.add(new StockEntry(seller, good, stockAmount, goodPrice));
        });

        return this.stockList;
    }

    @Override
    public Map<Integer, Sale> parseSales() {

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.registerModule(new JavaTimeModule());
        List<SalePojo> salePojos = new ArrayList<>();

        try {
            salePojos = mapper.readValue(this.sales , new TypeReference<List<SalePojo>>(){});

            // TODO переработать обработку исключений
        } catch (IOException e) {
            System.err.println("Выброшено IOException при попытке распарсить файл " +
                    "с продажами \"" + this.sales + "\"!");
            System.exit(-1);
        }

        salePojos.forEach(entry -> {

            Integer saleID = entry.getSaleID();
            Objects.requireNonNull(saleID, "ID продажи не распознан!");

            Integer sellerID = entry.getSellerID();
            Objects.requireNonNull(sellerID, "ID продавца не распознан!");
            Seller seller = this.sellersMap.get(sellerID);
            Objects.requireNonNull(seller, "Продавец с ID = " + sellerID + " не найден!");

            Integer goodID = entry.getGoodID();
            Objects.requireNonNull(goodID, "ID товара не распознан!");
            Good good = this.goodsMap.get(goodID);
            Objects.requireNonNull(good, "Товар с ID = " + goodID + " не найден!");

            Integer soldAmount = entry.getSoldAmount();
            Objects.requireNonNull(soldAmount, "Количество проданного товара не распознано!");

            LocalDate date = entry.getSaleDate();
            Objects.requireNonNull(date, "Дата продажи не распознана!");

            this.salesMap.put(saleID, new Sale(seller, good, soldAmount, date));
        });

        return this.salesMap;
    }
}