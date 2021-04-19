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
 * Класс, описывающий результаты 4-го задания.
 */
@XmlRootElement(name = "task_four")
@XmlAccessorType(XmlAccessType.FIELD)
public class TaskFourResult extends TaskResult {

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
    public TaskFourResult(Map<Good, Integer> map) {

        map.forEach((good, stockAmount) -> results.add(new Entry(
                good,
                stockAmount
        )));
    }

    /**
     * Создает список результирующих записей.
     */
    private TaskFourResult() {
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
         * Количество проданного товара.
         */
        @XmlElement(name = "sold_amount")
        private Integer soldAmount;

        /**
         * Создает результирующую запись.
         *
         * @param good товар
         * @param soldAmount количество проданного товара
         */
        public Entry(Good good, Integer soldAmount) {
            this.good = good;
            this.soldAmount = soldAmount;
        }

        /**
         * Создает результирующую запись.
         */
        private Entry() {
        }
    }
}
