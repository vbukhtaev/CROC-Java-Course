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
 * Класс, описывающий результаты 1-го задания.
 */
@XmlRootElement(name = "task_one")
@XmlAccessorType(XmlAccessType.FIELD)
public class TaskOneResult extends TaskResult {

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
    public TaskOneResult(Map<Good, Pair<Seller, Integer>> map) {

        map.forEach((good, pair) -> results.add(new Entry(
                good,
                pair.getKey(),
                pair.getValue()
        )));
    }

    /**
     * Создает список результирующих записей.
     */
    private TaskOneResult() {
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
         * Количество товара в наличии.
         */
        @XmlElement
        private Integer stockAmount;

        /**
         * Создает результирующую запись.
         *
         * @param good товар
         * @param seller продавец
         * @param stockAmount количество товара в наличии
         */
        public Entry(Good good, Seller seller, Integer stockAmount) {
            this.good = good;
            this.seller = seller;
            this.stockAmount = stockAmount;
        }

        /**
         * Создает результирующую запись.
         */
        private Entry() {
        }
    }
}
