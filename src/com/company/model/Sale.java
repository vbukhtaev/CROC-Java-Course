package com.company.model;

import java.time.LocalDate;

/**
 * Класс, описывающий продажу.
 */
public class Sale {

    /**
     * Продавец.
     */
    private final Seller seller;
    /**
     * Товар.
     */
    private final Good good;
    /**
     * Количество проданного товара.
     */
    private final int soldAmount;
    /**
     * Дата продажи.
     */
    private final LocalDate saleDate;

    /**
     * Создает продажу.
     * @param seller продавец
     * @param good товар
     * @param soldAmount количество проданного товара
     * @param saleDate дата продажи
     */
    public Sale(Seller seller, Good good, int soldAmount, LocalDate saleDate) {
        this.seller = seller;
        this.good = good;
        this.soldAmount = soldAmount;
        this.saleDate = saleDate;
    }

    /**
     * Возвращает продавца.
     * @return продавца
     */
    public Seller getSeller() {
        return seller;
    }

    /**
     * Возвращает товар.
     * @return товар
     */
    public Good getGood() {
        return good;
    }

    /**
     * Возвращает количество проданного товара.
     * @return количество проданного товара
     */
    public int getSoldAmount() {
        return soldAmount;
    }

    /**
     * Возвращает дату продажи.
     * @return дату продажи
     */
    public LocalDate getSaleDate() {
        return saleDate;
    }

    @Override
    public String toString() {
        return "[Продажа] :" +
                " продавец = " + seller +
                ", товар = " + good +
                ", количество проданного товара = " + soldAmount +
                ", дата продажи = " + saleDate;
    }
}
