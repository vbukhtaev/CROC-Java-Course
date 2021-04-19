package com.company.writers.jaxbextraclasses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Класс, описывающий результаты 9-го задания.
 */
@XmlRootElement(name = "task_nine")
@XmlAccessorType(XmlAccessType.FIELD)
public class TaskNineResult extends TaskResult {

    /**
     * Шаблон для приведения даты к строке.
     */
    private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd MMMM yyyy");

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
    public TaskNineResult(Map<LocalDate, Double> map) {

        map.forEach((date, soldAmount) -> results.add(new Entry(
                date.format(DATE_PATTERN),
                Math.round(soldAmount * 100.0) / 100.0
        )));
    }

    /**
     * Создает список результирующих записей.
     */
    private TaskNineResult() {
    }


    /**
     * Вложенный класс, описывающий результирующую запись.
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    private static class Entry {

        /**
         * Дата.
         */
        @XmlElement
        private String date;

        /**
         * Среднее количество проданного товара.
         */
        @XmlElement(name = "avg_sold_amount")
        private Double avgSoldAmount;

        /**
         * Создает результирующую запись.
         *
         * @param date дата
         * @param avgSoldAmount среднее количество проданного товара
         */
        public Entry(String date, Double avgSoldAmount) {
            this.date = date;
            this.avgSoldAmount = avgSoldAmount;
        }

        /**
         * Создает результирующую запись.
         */
        private Entry() {
        }
    }
}
