package com.company.writers.jaxbextraclasses;

import com.company.model.Seller;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Класс, описывающий результаты 5-го задания.
 */
@XmlRootElement(name = "task_five")
@XmlAccessorType(XmlAccessType.FIELD)
public class TaskFiveResult extends TaskResult {

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
    public TaskFiveResult(Map<Seller, Integer> map) {

        map.forEach((seller, stockAmount) -> results.add(new Entry(
                seller,
                stockAmount
        )));
    }

    /**
     * Создает список результирующих записей.
     */
    private TaskFiveResult() {
    }


    /**
     * Вложенный класс, описывающий результирующую запись.
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    private static class Entry {

        /**
         * Продавец.
         */
        @XmlElement
        private Seller seller;

        /**
         * Количество проданного товара.
         */
        @XmlElement(name = "sold_amount")
        private Integer soldAmount;

        /**
         * Создает результирующую запись.
         *
         * @param seller продавец
         * @param soldAmount количество проданного товара
         */
        public Entry(Seller seller, Integer soldAmount) {
            this.seller = seller;
            this.soldAmount = soldAmount;
        }

        /**
         * Создает результирующую запись.
         */
        private Entry() {
        }
    }
}
