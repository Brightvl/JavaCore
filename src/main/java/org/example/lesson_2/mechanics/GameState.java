package org.example.lesson_2.mechanics;

import org.example.lesson_2.field.PlayingField;

public class GameState {

    private PlayingField playingField;
    private final int WIN_COUNT;

    public GameState(PlayingField playingField, int winCount) {
        this.playingField = playingField;
        this.WIN_COUNT = winCount;
    }

    /**
     * Метод проверки состояния игры
     *
     * @param dot фишка игрока
     * @param s   победный слоган
     * @return результат проверки состояния игры
     */
    public boolean checkGameState(char dot, String s) {
        if (checkWin(dot)) {
            playingField.setTurnCoordinate(playingField.getTurn().getX(), playingField.getTurn().getY());
            System.out.println(s);
            return true;
        }
        if (checkDraw()) {
            System.out.println("Ничья!");
            return true;
        }
        return false; // Игра продолжается
    }

    /**
     * Проверка на ничью
     *
     * @return
     */
    public boolean checkDraw() {
        for (int y = 0; y < playingField.getFieldSizeY(); y++) {
            for (int x = 0; x < playingField.getFieldSizeX(); x++) {
                if (playingField.isCellEmpty(x, y))
                    return false;
            }
        }
        return true;
    }

    /**
     * Проверка победы игрока
     *
     * @param dot фишка игрока
     * @return признак победы
     */
    public boolean checkWin(char dot) {
        if (checkWinHorizontal(playingField.getTurn().getX(), playingField.getTurn().getY(), dot)) {
            return true;
        }
        if (checkWinVertical(playingField.getTurn().getX(), playingField.getTurn().getY(), dot)) {
            return true;
        }
        if (checkWinDiagonalDescent(playingField.getTurn().getX(), playingField.getTurn().getY(), dot)) {
            return true;
        }
        if (checkWinDiagonalClimb(playingField.getTurn().getX(), playingField.getTurn().getY(), dot)) {
            return true;
        }
        return false;
    }

    /**
     * // Проверка по горизонтали ↔
     *
     * @param x
     * @param y
     * @param dot
     * @return
     */
    private boolean checkWinHorizontal(int x, int y, char dot) {
        int tempXRight = 1;
        int tempXLeft = 1;
        int tempCountRight = 0;
        int tempCountLeft = 0;

        for (int i = 0; i <= WIN_COUNT; i++) {
            // проверка вправо
            if (playingField.isCellValid(x + tempXRight, y) && playingField.isCellDot(x + tempXRight, y, dot)) {
                tempCountRight++;
                tempXRight++;
            }
            // проверка влево
            if (playingField.isCellValid(x - tempXLeft, y) && playingField.isCellDot(x - tempXLeft, y, dot)) {
                tempCountLeft++;
                tempXLeft++;
            }

        }

        return tempCountRight + tempCountLeft + 1 >= WIN_COUNT;
    }

    /**
     * Проверка по вертикали ↕
     *
     * @param x
     * @param y
     * @param dot
     * @return
     */
    private boolean checkWinVertical(int x, int y, char dot) {
        int tempYUp = 1, tempYDown = 1;
        int tempCountUp = 0, tempCountDown = 0;

        for (int i = 0; i <= WIN_COUNT; i++) {
            // проверка вверх
            if (playingField.isCellValid(x, y - tempYUp) && playingField.isCellDot(x, y - tempYUp, dot)) {
                tempCountUp++;
                tempYUp++;
            }
            // проверка вниз
            if (playingField.isCellValid(x, y + tempYDown) && playingField.isCellDot(x, y + tempYDown, dot)) {
                tempCountDown++;
                tempYDown++;
            }
        }

        return tempCountUp + tempCountDown + 1 >= WIN_COUNT;
    }

    /**
     * Проверка по диагонали ↖↘
     *
     * @param x
     * @param y
     * @param dot
     * @return
     */
    private boolean checkWinDiagonalDescent(int x, int y, char dot) {
        int tempDotUp = 1, tempDotDown = 1;
        int tempCountUp = 0, tempCountDown = 0;

        for (int i = 0; i <= WIN_COUNT; i++) {
            // проверка диагональ ↖
            if (playingField.isCellValid(x - tempDotUp, y - tempDotUp) && playingField.isCellDot(x - tempDotUp, y - tempDotUp, dot)) {
                tempCountUp++;
                tempDotUp++;
            }
            // проверка диагональ ↘
            if (playingField.isCellValid(x + tempDotDown, y + tempDotDown) && playingField.isCellDot(x + tempDotDown,
                    y + tempDotDown, dot)) {
                tempCountDown++;
                tempDotDown++;
            }

        }


        return tempCountUp + tempCountDown + 1 >= WIN_COUNT;
    }

    /**
     * Проверка по диагонали ↙↗
     *
     * @param x
     * @param y
     * @param dot
     * @return
     */
    private boolean checkWinDiagonalClimb(int x, int y, char dot) {
        int tempDotUp = 1, tempDotDown = 1;
        int tempCountUp = 0, tempCountDown = 0;
        for (int i = 0; i <= WIN_COUNT; i++) {
            // проверка диагональ ↗
            if (playingField.isCellValid(x + tempDotUp, y - tempDotUp) && playingField.isCellDot(x + tempDotUp, y - tempDotUp
                    , dot)) {
                tempCountUp++;
                tempDotUp++;
            }
            // проверка диагональ ↙
            if (playingField.isCellValid(x + tempDotDown, y - tempDotDown) && playingField.isCellDot(x + tempDotDown,
                    y - tempDotDown, dot)) {
                tempCountDown++;
                tempDotDown++;
            }
        }
        return tempCountUp + tempCountDown + 1 >= WIN_COUNT;
    }


}
