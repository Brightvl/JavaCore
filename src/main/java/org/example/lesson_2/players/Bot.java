package org.example.lesson_2.players;

import org.example.lesson_2.ui.GameProcess;

public class Bot extends Player {
    GameProcess gameProcess;


    public Bot(GameProcess gameProcess) {
        super("Автобот", '0');
        this.gameProcess = gameProcess;
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
     * @param player
     * @return
     */
    private boolean predictVictory(Player player) {
        for (int y = 0; y < gameProcess.getPlayingField().getFieldSizeY(); y++) {
            for (int x = 0; x < gameProcess.getPlayingField().getFieldSizeX(); x++) {
                if (gameProcess.getPlayingField().isCellEmpty(x, y) &&
                        gameProcess.getGameState().checkWin(x, y, player.getDot())) {
                    super.setTurnCoordinate(x, y);
                    gameProcess.getPlayingField().fillTurn(super.getTurn().getY(), super.getTurn().getX(), super.getDot());
                    return true;
                }
            }
        }
        return false;
    }
}
