package com.company.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Класс, описывающий продавца.
 */
@XmlRootElement
public class Seller {

    /**
     * Имя продавца.
     */
    @XmlElement(name = "first_name")
    private final String firstName;
    /**
     * Фамилия продавца.
     */
    @XmlElement(name = "last_name")
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

    private Seller() {
        this.firstName = null;
        this.lastName = null;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
