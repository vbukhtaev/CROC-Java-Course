package com.company.model.pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * POJO класс, описывающий товар.
 */
public class GoodPojo {

    /**
     * ID товара.
     */
    private final int id;
    /**
     * Название товара.
     */
    private final String name;

    /**
     * Создает товар.
     *
     * @param id ID товара
     * @param name название товара
     */
    @JsonCreator
    public GoodPojo(
            @JsonProperty("id") int id,
            @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Возвращает ID товара.
     *
     * @return ID товара
     */
    public int getId() {
        return id;
    }

    /**
     * Возвращает название товара.
     *
     * @return название товара
     */
    public String getName() {
        return name;
    }
}
