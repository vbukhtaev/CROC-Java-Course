package com.company.Model.POJO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * POJO класс, описывающий запись наличия товара.
 */
public class StockEntryPojo {

    /**
     * ID продавца.
     */
    private final Integer sellerID;
    /**
     * ID товара.
     */
    private final Integer goodID;
    /**
     * Цена товара.
     */
    private final Integer goodPrice;
    /**
     * Количество товара в наличии.
     */
    private final Integer goodAmount;

    /**
     * Создает запись наличия товара.
     *
     * @param sellerID   продавец
     * @param goodID     товар
     * @param goodAmount количество товара в наличии
     * @param goodPrice  цена товара
     */
    @JsonCreator
    public StockEntryPojo(
            @JsonProperty("seller_id") Integer sellerID,
            @JsonProperty("good_id") Integer goodID,
            @JsonProperty("amount") Integer goodAmount,
            @JsonProperty("price") Integer goodPrice) {
        this.sellerID = sellerID;
        this.goodID = goodID;
        this.goodAmount = goodAmount;
        this.goodPrice = goodPrice;
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
     * Возвращает цену товара.
     *
     * @return цену товара
     */
    public Integer getGoodPrice() {
        return goodPrice;
    }

    /**
     * Возвращает количество товара в наличии.
     *
     * @return количество товара в наличии
     */
    public Integer getGoodAmount() {
        return goodAmount;
    }
}