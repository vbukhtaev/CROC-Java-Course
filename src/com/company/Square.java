package com.company;

/**
 * Class describing coordinates of a chessboard square.
 * @author Bukhtaev Vladislav
 * @version 1.0
 */

public class Square {

    /**
     * X coordinate
     */
    private int x;

    /**
     * Y coordinate
     */
    private int y;

    /**
     * Constructs a square the specified coordinates.
     * @param x X coordinate
     * @param y Y coordinate
     * @throws IllegalArgumentException if at least one specified coordinate is out of range [0; 7]
     */
    public Square(int x, int y) throws IllegalArgumentException {

        if (isLegal(x) && isLegal(y)) {
            this.x = x;
            this.y = y;

        } else {
            throw new IllegalArgumentException("Клетки с координатами [" + x + ", " + y + "] не существует");
        }
    }

    /**
     * Checks if a coordinate is in range [0; 7].
     * @param coordinate a coordinate
     * @return true if a coordinate is in range [0; 7]
     */
    private static boolean isLegal(int coordinate) {
        return coordinate >= 0 && coordinate <= 7;
    }

    /**
     * Returns a string representation of this square.
     * @return a string representation of this square
     */
    @Override
    public String toString() {
        String letter = String.valueOf((char) (this.x + 65)); // Преобразуем координату x в букву.
        return letter + (this.y + 1);
    }

    /**
     * Changes the X coordinate to have the specified coordinate.
     * @param x X coordinate
     * @throws IllegalArgumentException if the specified coordinate is out of range [0; 7]
     */
    public void setX(int x) throws IllegalArgumentException {
        if (isLegal(x)) {
            this.x = x;
        } else {
            throw new IllegalArgumentException("Клетки с координатой x = " + x + " не существует");
        }
    }

    /**
     * Changes the Y coordinate to have the specified coordinate.
     * @param y Y coordinate
     * @throws IllegalArgumentException if the specified coordinate is out of range [0; 7]
     */
    public void setY(int y) throws IllegalArgumentException {
        if (isLegal(y)) {
            this.y = y;
        } else {
            throw new IllegalArgumentException("Клетки с координатой y = " + y + " не существует");
        }
    }

    /**
     * Returns the X coordinate of this square.
     * @return the X coordinate of this square
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the Y coordinate of this square.
     * @return the Y coordinate of this square
     */
    public int getY() {
        return y;
    }
}