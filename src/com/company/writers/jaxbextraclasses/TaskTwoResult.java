package com.company.writers.jaxbextraclasses;

import com.company.model.Good;
import com.company.model.Seller;
import com.company.utilities.Pair;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Класс, описывающий результаты 2-го задания.
 */
@XmlRootElement(name = "task_two")
@XmlAccessorType(XmlAccessType.FIELD)
public class TaskTwoResult extends TaskResult {

    /**
     * Список с результирующими записями {@code Entry}.
     */
    @XmlElement(name = "entry")
    private List<Entry> results = new ArrayList<>();

    /**
     * Создает список результирующих записей из ассоциативного массива.
     *
     * @param map ассоциативный массив
     */
    public TaskTwoResult(Map<Good, Pair<Seller, Integer>> map) {

        map.forEach((good, pair) -> results.add(new Entry(
                good,
                pair.getKey(),
                pair.getValue()
        )));
    }

    /**
     * Создает список результирующих записей.
     */
    private TaskTwoResult() {
    }


    /**
     * Вложенный класс, описывающий результирующую запись.
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    private static class Entry {

        /**
         * Товар.
         */
        @XmlElement
        private Good good;

        /**
         * Продавец.
         */
        @XmlElement
        private Seller seller;

        /**
         * Цена товара.
         */
        @XmlElement
        private Integer price;

        /**
         * Создает результирующую запись.
         *
         * @param good товар
         * @param seller продавец
         * @param price цена товара
         */
        public Entry(Good good, Seller seller, Integer price) {
            this.good = good;
            this.seller = seller;
            this.price = price;
        }

        /**
         * Создает результирующую запись.
         */
        private Entry() {
        }
    }
}
