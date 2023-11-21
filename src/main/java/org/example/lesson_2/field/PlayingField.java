package org.example.lesson_2.field;

public class PlayingField {

    private final char DOT_HUMAN = 'X';
    private final char DOT_AI = '0';
    private final char DOT_EMPTY = '*';

    private int fieldSizeX;
    private int fieldSizeY;

    private char[][] field;

    private Turn turn;

    public PlayingField(int widthField, int heightFields) {
        this.fieldSizeX = widthField;
        this.fieldSizeY = heightFields;

        this.field = new char[fieldSizeY][fieldSizeX];

        this.turn = new Turn();
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
        System.out.print("+");
        for (int i = 0; i < fieldSizeX; i++) {
            System.out.print(" " + (i + 1));
        }
        System.out.println(" ↔ X");

        for (int y = 0; y < fieldSizeY; y++) {
            System.out.print(y + 1 + "|");
            for (int x = 0; x < fieldSizeX; x++) {
                System.out.print(field[y][x] + "|");
            }
            System.out.println();
        }
        System.out.println("↕ Y");
        for (int i = 0; i < fieldSizeX * 2 + 2; i++) {
            System.out.print("_");
        }
        System.out.println();

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

    public char getDOT_HUMAN() {
        return DOT_HUMAN;
    }

    public char getDOT_AI() {
        return DOT_AI;
    }

    public void fillTurn(int Y, int X, char dot) {
        this.field[Y][X] = dot;
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

    public Turn getTurn() {
        return turn;
    }

    public void setTurnCoordinate(int x, int y) {
        turn.setX(x);
        turn.setY(y);
    }


}
