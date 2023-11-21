package org.example.lesson_2;

import org.example.lesson_2.ui.GameUi;

public class RunTicTacToe {


    public static void main(String[] args) {
        GameUi gameUi = new GameUi(5, 5, 3);
        gameUi.run();
    }


}
