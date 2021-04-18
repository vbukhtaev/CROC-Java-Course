package com.company.utilities;

/**
 * Класс, описывающий пару ключ-значение.
 * @param <K> Тип ключа
 * @param <V> Тип значения
 */
public class Pair<K, V> {

    /**
     * Ключ.
     */
    public final K key;

    /**
     * Значение.
     */
    public final V value;

    /**
     * Создает пару ключ-значение.
     * @param key ключ
     * @param value значение
     */
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Возвращает ключ пары ключ-значение.
     * @return ключ пары ключ-значение
     */
    public K getKey() {
        return key;
    }

    /**
     * Возвращает значение пары ключ-значение.
     * @return значение пары ключ-значение
     */
    public V getValue() {
        return value;
    }
}
