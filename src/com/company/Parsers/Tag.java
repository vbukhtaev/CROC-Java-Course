package com.company.Parsers;

/**
 * Перечисление, описывающее тег XML файла или свойство JSON файла.
 */
public enum Tag {

    /**
     * Тег &lt;id&gt;&lt;/id&gt;
     */
    ID("id"),

    /**
     * Тег &lt;name&gt;&lt;/name&gt;
     */
    NAME("name"),

    /**
     * Тег &lt;first_name&gt;&lt;/first_name&gt;
     */
    FIRST_NAME("first_name"),

    /**
     * Тег &lt;last_name&gt;&lt;/last_name&gt;
     */
    LAST_NAME("last_name"),

    /**
     * Тег &lt;seller_id&gt;&lt;/seller_id&gt;
     */
    SELLER_ID("seller_id"),

    /**
     * Тег &lt;good_id&gt;&lt;/good_id&gt;
     */
    GOOD_ID("good_id"),

    /**
     * Тег &lt;amount&gt;&lt;/amount&gt;
     */
    AMOUNT("amount"),

    /**
     * Тег &lt;price&gt;&lt;/price&gt;
     */
    PRICE("price"),

    /**
     * Тег &lt;date&gt;&lt;/date&gt;
     */
    DATE("date"),

    /**
     * Тег &lt;good&gt;&lt;/good&gt;
     */
    GOOD("good"),

    /**
     * Тег &lt;goods&gt;&lt;/goods&gt;
     */
    GOODS("goods"),

    /**
     * Тег &lt;seller&gt;&lt;/seller&gt;
     */
    SELLER("seller"),

    /**
     * Тег &lt;sellers&gt;&lt;/sellers&gt;
     */
    SELLERS("sellers"),

    /**
     * Тег &lt;entry&gt;&lt;/entry&gt;
     */
    ENTRY("entry"),

    /**
     * Тег &lt;stock&gt;&lt;/stock&gt;
     */
    STOCK("stock"),

    /**
     * Тег &lt;sale&gt;&lt;/sale&gt;
     */
    SALE("sale"),

    /**
     * Тег &lt;sales&gt;&lt;/sales&gt;
     */
    SALES("sales");

    /**
     * Название тега.
     */
    private String name;

    Tag(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
