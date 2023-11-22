package org.example.lesson_2.players;

import org.example.lesson_2.ui.GameProcess;

import java.util.Random;

public class Bot extends Player {
    GameProcess gameProcess;
    int levelBot;

    public Bot(GameProcess gameProcess) {
        super("Автобот", '0');
        this.gameProcess = gameProcess;
        this.levelBot = 1;
    }


    /**
     * Предсказание победы соперника
     *
     * @param player соперник
     * @return
     */
    public boolean predictionOfHumanVictory(Player player) {
        return predictVictory(player);
    }

    /**
     * Предсказание своей победы
     *
     * @return
     */
    public boolean predictionItSelfVictory() {
        return predictVictory(this);
    }


    /**
     * Предсказание победы
     *
     * @param player
     * @return
     */
    private boolean predictVictory(Player player) {
        for (int y = 0; y < gameProcess.getGameField().getFieldSizeY(); y++) {
            for (int x = 0; x < gameProcess.getGameField().getFieldSizeX(); x++) {
                if (gameProcess.getGameField().isCellEmpty(x, y) &&
                        gameProcess.getGameState().checkWin(x, y, player.getDot())) {
                    super.setTurnCoordinate(x, y);
                    gameProcess.getGameField().fillTurn(super.getTurn().getY(), super.getTurn().getX(), super.getDot());
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Осуществляет ход игрока (компьютера).
     */
    public void botTurn(Player player, Random random) {
        switch (levelBot) {
            case 0 -> {
                botEasy(random);
            }
            case 2 -> {
                botHard(player, random);
            }
            default -> {
                botMiddle(player, random);
            }
        }
    }

    /**
     * Лёгкий уровень сложности
     * @param random
     */
    private void botEasy(Random random) {

        do {
            int x = random.nextInt(gameProcess.getGameField().getFieldSizeX());
            int y = random.nextInt(gameProcess.getGameField().getFieldSizeY());
            super.setTurnCoordinate(x, y);
        }
        while (!gameProcess.getGameField().isCellEmpty(super.getTurn().getX(), super.getTurn().getY()));

        gameProcess.getGameField().fillTurn(
                super.getTurn().getY(),
                super.getTurn().getX(),
                super.getDot());

    }

    /**
     * Средний уровень сложности
     * @param player
     * @param random
     */
    private void botMiddle(Player player, Random random) {
        if (!predictionOfHumanVictory(player)) {
            do {
                int x = random.nextInt(gameProcess.getGameField().getFieldSizeX());
                int y = random.nextInt(gameProcess.getGameField().getFieldSizeY());
                super.setTurnCoordinate(x, y);
            }
            while (!gameProcess.getGameField().isCellEmpty(super.getTurn().getX(), super.getTurn().getY()));

            gameProcess.getGameField().fillTurn(
                    super.getTurn().getY(),
                    super.getTurn().getX(),
                    super.getDot());
        }
    }

    /**
     * Тяжелый уровень сложности
     * @param player
     * @param random
     */
    private void botHard(Player player, Random random) {
        if (!predictionOfHumanVictory(player) && !predictionItSelfVictory()) {
            do {
                int x = random.nextInt(gameProcess.getGameField().getFieldSizeX());
                int y = random.nextInt(gameProcess.getGameField().getFieldSizeY());
                super.setTurnCoordinate(x, y);
            }
            while (!gameProcess.getGameField().isCellEmpty(super.getTurn().getX(), super.getTurn().getY()));

            gameProcess.getGameField().fillTurn(
                    super.getTurn().getY(),
                    super.getTurn().getX(),
                    super.getDot());
        }
    }

    public int getLevelBot() {
        return levelBot;
    }

    public void choiceDifficulty(int num) {
        this.levelBot = num;
    }
}
