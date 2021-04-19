package com.company.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Класс, описывающий товар.
 */
@XmlRootElement
public class Good {

    /**
     * Название товара.
     */
    @XmlElement
    private final String name;

    /**
     * Создает товар.
     * @param name название товара
     */
    public Good(String name) {
        this.name = name;
    }

    private Good() {
        this.name = null;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
