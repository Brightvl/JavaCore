package org.example.lesson_2.field;

public class GameField {

    private final char DOT_EMPTY = '*';

    private int fieldSizeX;
    private int fieldSizeY;

    private char[][] field;


    public GameField(int widthField, int heightFields) {
        this.fieldSizeX = widthField;
        this.fieldSizeY = heightFields;

        this.field = new char[fieldSizeY][fieldSizeX];

    }

    /**
     * Инициализация игрового поля
     */
    public void initialize() {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                field[y][x] = DOT_EMPTY;
            }
        }
    }

    /**
     * Печать текущего состояния игрового поля
     */
    public void printField() {
        System.out.println("+ " + stringColor(33, " 1 2 3 4 5") + " ↔ X");
        for (int i = 0; i < fieldSizeX; i++) {
            System.out.print(stringColor(32, i + 1) + " |");
            for (int j = 0; j < fieldSizeY; j++) {
                if (field[i][j] == '0') {
                    System.out.print(stringColor(34, field[i][j]) + "|");
                } else if (field[i][j] == 'X') {
                    System.out.print(stringColor(31, field[i][j]) + "|");
                } else {
                    System.out.print(field[i][j] + "|");
                }
            }
            System.out.println();
        }
        System.out.println("↕ Y");
        System.out.println("____________");
    }


    /**
     * Проверка, является ли ячейка игрового поля пустой
     *
     * @param x
     * @param y
     * @return
     */
    public boolean isCellEmpty(int x, int y) {
        return field[y][x] == DOT_EMPTY;
    }


    /**
     * Проверка доступности ячейки игрового поля (не вышли за пределы)
     *
     * @param x
     * @param y
     * @return
     */
    public boolean isCellValid(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    /**
     * Проверка, является ли ячейка игрового поля искомым символом
     *
     * @param x
     * @param y
     * @return
     */
    public boolean isCellDot(int x, int y, char dot) {
        return field[y][x] == dot;
    }

    public void fillTurn(int y, int x, char dot) {
        this.field[y][x] = dot;
    }

    public int getFieldSizeX() {
        return fieldSizeX;
    }

    public int getFieldSizeY() {
        return fieldSizeY;
    }

    public char[][] getField() {
        return field;
    }


    /**
     * Чтобы раскрасить консоль
     *
     * @param color номер цвета ANSI Escape
     * @param s     строка текста
     * @return
     */
    private String stringColor(int color, String s) {
        StringBuilder stringBuilder = new StringBuilder("\u001B[" + color + "m" + s + "\u001B[0m");
        return stringBuilder.toString();

    }

    /**
     * Чтобы раскрасить консоль
     *
     * @param color номер цвета ANSI Escape
     * @param s     char
     * @return цветной текст
     */
    private String stringColor(int color, char s) {
        StringBuilder stringBuilder = new StringBuilder("\u001B[" + color + "m" + s + "\u001B[0m");
        return stringBuilder.toString();

    }

    /**
     * Чтобы раскрасить консоль
     *
     * @param color номер цвета ANSI Escape
     * @param s     char
     * @return цветной текст
     */
    private String stringColor(int color, int s) {
        StringBuilder stringBuilder = new StringBuilder("\u001B[" + color + "m" + s + "\u001B[0m");
        return stringBuilder.toString();

    }


}
