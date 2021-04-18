package com.company.model;

/**
 * Класс, описывающий товар.
 */
public class Good {

    /**
     * Название товара.
     */
    private final String name;

    /**
     * Создает товар.
     * @param name название товара
     */
    public Good(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
