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
 * Класс, описывающий результаты 8-го задания.
 */
@XmlRootElement(name = "task_eight")
@XmlAccessorType(XmlAccessType.FIELD)
public class TaskEightResult extends TaskResult {

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
    public TaskEightResult(Map<LocalDate, Integer> map) {

        map.forEach((date, soldAmount) -> results.add(new Entry(
                date.format(DATE_PATTERN),
                soldAmount
        )));
    }

    /**
     * Создает список результирующих записей.
     */
    private TaskEightResult() {
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
         * Количество проданного товара.
         */
        @XmlElement(name = "sold_amount")
        private Integer soldAmount;

        /**
         * Создает результирующую запись.
         *
         * @param date дата
         * @param soldAmount количество проданного товара
         */
        public Entry(String date, Integer soldAmount) {
            this.date = date;
            this.soldAmount = soldAmount;
        }

        /**
         * Создает результирующую запись.
         */
        private Entry() {
        }
    }
}
