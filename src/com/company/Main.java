package com.company;

public class Main {

    /**
     * Homework #3
     *
     * @version 1.0
     * @author Bukhtaev Vladislav
     *
     * Задание:
     *
     * Определить класс, описывающий координаты шахматной клетки.
     * Данные класса: компоненты x и y, отсчитываемые от левого нижнего угла.
     * Все методы, позволяющие установить координаты, в том числе и конструкторы,
     * должны проверять корректность аргументов и генерировать IllegalArgumentException в случае ошибочных значений.
     * Переопределить метод toString(), выводящий координаты клетки в формате
     * <номер колонки в виде буквы от 'a' до 'h'><номер строки, начиная с 1>.
     * Например, клетка с координатами (1, 1) имеет строковое представление "b2".
     */

    public static void main(String[] args) {

        /*
            Случай с корректными параметрами.
            Создаем клетку C4 с координатами [2, 3].
         */
        try {
            Square square = new Square(2, 3);
            System.out.println("Успешно создана клетка " + square + " с координатами [" + square.getX() + ", " + square.getY() + "]");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }


        /*
            Случай с НЕкорректными параметрами и выбросом исключения.
            Создаем несуществующую клетку с координатами [1, 8].
         */
        try {
            Square square = new Square(1, 8);
            System.out.println("Успешно создана клетка " + square + " с координатами [" + square.getX() + ", " + square.getY() + "]"); // Не выпонится.
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }


        /*
            Случай с корректными параметрами.
            Меняем клетке A1 координаты на [5, 2].
         */
        try {
            Square square = new Square(0, 0);
            square.setX(5);
            square.setY(2);
            System.out.println("Координаты клетки A1 успешно изменены на [" + square.getX() + ", " + square.getY() + "]");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }


        /*
            Случай с НЕкорректными параметрами и выбросом исключения.
            Меняем клетке A1 координату x на 9.
         */
        try {
            Square square = new Square(0, 0);
            square.setX(9);
            System.out.println("Координаты клетки A1 успешно изменены на [" + square.getX() + ", " + square.getY() + "]"); // Не выпонится.
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }


        /*
            Случай с НЕкорректными параметрами и выбросом исключения.
            Меняем клетке A1 координату y на 8.
         */
        try {
            Square square = new Square(0, 0);
            square.setY(8);
            System.out.println("Координаты клетки A1 успешно изменены на [" + square.getX() + ", " + square.getY() + "]"); // Не выпонится.
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }


        // Вывод клетки F4 в консоль.
        Square square = new Square(1, 1);
        System.out.println("Клетка с координатами [" + square.getX() + ", " + square.getY() + "] называется " + square);
    }
}