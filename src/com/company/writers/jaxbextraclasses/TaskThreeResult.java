package com.company.writers.jaxbextraclasses;

import com.company.model.Good;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Класс, описывающий результаты 3-го задания.
 */
@XmlRootElement(name = "task_three")
@XmlAccessorType(XmlAccessType.FIELD)
public class TaskThreeResult extends TaskResult {

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
    public TaskThreeResult(Map<Good, Integer> map) {

        map.forEach((good, stockAmount) -> results.add(new Entry(
                good,
                stockAmount
        )));
    }

    /**
     * Создает список результирующих записей.
     */
    private TaskThreeResult() {
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
         * Количество товара в наличии.
         */
        @XmlElement
        private Integer stockAmount;

        /**
         * Создает результирующую запись.
         *
         * @param good товар
         * @param stockAmount количество товара в наличии
         */
        public Entry(Good good, Integer stockAmount) {
            this.good = good;
            this.stockAmount = stockAmount;
        }

        /**
         * Создает результирующую запись.
         */
        private Entry() {
        }
    }
}
