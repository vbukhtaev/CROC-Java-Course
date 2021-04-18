package com.company.model;

/**
 * Класс, описывающий запись наличия товара.
 */
public class StockEntry {

    /**
     * Продавец.
     */
    private final Seller seller;
    /**
     * Товар.
     */
    private final Good good;
    /**
     * Цена товара.
     */
    private final int goodPrice;
    /**
     * Количество товара в наличии.
     */
    private final int goodAmount;

    /**
     * Создает запись наличия товара.
     * @param seller продавец
     * @param good товар
     * @param goodAmount количество товара в наличии
     * @param goodPrice цена товара
     */
    public StockEntry(Seller seller, Good good, int goodAmount, int goodPrice) {
        this.seller = seller;
        this.good = good;
        this.goodAmount = goodAmount;
        this.goodPrice = goodPrice;
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
     * Возвращает цену товара.
     * @return цену товара
     */
    public int getGoodPrice() {
        return goodPrice;
    }

    /**
     * Возвращает количество товара в наличии.
     * @return количество товара в наличии
     */
    public int getGoodAmount() {
        return goodAmount;
    }

    @Override
    public String toString() {
        return "[Наличие] : " +
                "продавец = " + seller +
                ", товар = " + good +
                ", количество = " + goodAmount +
                ", цена = " + goodPrice;
    }
}
