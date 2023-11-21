package org.example.lesson_2;

import org.example.lesson_2.ui.GameProcess;

public class RunTicTacToe {


    public static void main(String[] args) {
        GameProcess gameUi = new GameProcess(3, 3, 3);
        gameUi.run();
    }


}
