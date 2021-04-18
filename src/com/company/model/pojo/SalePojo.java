package com.company.model.pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

/**
 * POJO класс, описывающий продажу.
 */
public class SalePojo {

    /**
     * ID продажи.
     */
    private final Integer saleID;
    /**
     * ID продавца.
     */
    private final Integer sellerID;
    /**
     * ID товара.
     */
    private final Integer goodID;
    /**
     * Количество проданного товара.
     */
    private final Integer soldAmount;
    /**
     * Дата продажи.
     */
    private final LocalDate saleDate;

    /**
     * Создает продажу.
     *
     * @param saleID     ID продажи
     * @param sellerID   продавец
     * @param goodID     товар
     * @param soldAmount количество проданного товара
     * @param saleDate   дата продажи
     */
    @JsonCreator
    public SalePojo(
            @JsonProperty("id") Integer saleID,
            @JsonProperty("seller_id") Integer sellerID,
            @JsonProperty("good_id") Integer goodID,
            @JsonProperty("amount") Integer soldAmount,
            @JsonProperty("date") LocalDate saleDate) {
        this.saleID = saleID;
        this.sellerID = sellerID;
        this.goodID = goodID;
        this.soldAmount = soldAmount;
        this.saleDate = saleDate;
    }

    /**
     * Возвращает ID продажи.
     *
     * @return ID продажи
     */
    public Integer getSaleID() {
        return saleID;
    }

    /**
     * Возвращает ID продавца.
     *
     * @return продавца
     */
    public Integer getSellerID() {
        return sellerID;
    }

    /**
     * Возвращает ID товара.
     *
     * @return товар
     */
    public Integer getGoodID() {
        return goodID;
    }

    /**
     * Возвращает количество проданного товара.
     *
     * @return количество проданного товара
     */
    public Integer getSoldAmount() {
        return soldAmount;
    }

    /**
     * Возвращает дату продажи.
     *
     * @return дату продажи
     */
    public LocalDate getSaleDate() {
        return saleDate;
    }
}