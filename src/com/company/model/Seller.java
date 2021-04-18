package com.company.model;

/**
 * Класс, описывающий продавца.
 */
public class Seller {

    /**
     * Имя продавца.
     */
    private final String firstName;
    /**
     * Фамилия продавца.
     */
    private final String lastName;

    /**
     * Создает продавца.
     * @param firstName имя продавца
     * @param lastName фамилия продавца
     */
    public Seller(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
