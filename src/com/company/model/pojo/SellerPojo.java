package com.company.model.pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * POJO класс, описывающий продавца.
 */
public class SellerPojo {

    /**
     * ID продавца.
     */
    private final int id;
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
     *
     * @param id ID продавца
     * @param firstName имя продавца
     * @param lastName  фамилия продавца
     */
    @JsonCreator
    public SellerPojo(
            @JsonProperty("id") int id,
            @JsonProperty("first_name") String firstName,
            @JsonProperty("last_name") String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Возвращает ID продавца.
     *
     * @return ID продавца
     */
    public int getId() {
        return id;
    }

    /**
     * Возвращает имя продавца.
     *
     * @return имя продавца
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Возвращает фамилию продавца.
     *
     * @return фамилию продавца
     */
    public String getLastName() {
        return lastName;
    }
}
