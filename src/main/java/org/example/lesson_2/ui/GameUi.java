package org.example.lesson_2.ui;

import org.example.lesson_2.mechanics.GameState;
import org.example.lesson_2.field.PlayingField;
import org.example.lesson_2.players.Ai;
import org.example.lesson_2.players.Human;

import java.util.Random;
import java.util.Scanner;

public class GameUi {


    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();

    private PlayingField playingField;
    private GameState gameState;

    private Human human;
    private Ai ai;


    public GameUi(int widthField, int heightFields, int winCount) {
        this.playingField = new PlayingField(widthField, heightFields);
        this.gameState = new GameState(playingField, winCount);
        this.human = new Human();
        ai = new Ai();

    }

    /**
     * Запуск игры
     */
    public void run() {
//        menuWelcome();
        gameProcess();

    }

    /**
     * Приветствие при первом входе
     */
    private void menuWelcome() {
        System.out.println("""
                Крестики-нолики!
                                
                Введи свое имя:
                """);
        human.setName(scanner.next());
    }

    /**
     * Игровой процесс
     */
    private void gameProcess() {
        while (true) {
            System.out.println("Имя игрока -> " + human.getName());
            playingField.initialize();
            playingField.printField();
            while (true) {
                humanTurn();
                playingField.printField();
                if (gameState.checkGameState(playingField.getDOT_HUMAN(), "Вы победили!"))

                    break;
                aiTurn();
                playingField.printField();
                if (gameState.checkGameState(playingField.getDOT_AI(), "Победил Автобот!"))
                    break;
            }
            System.out.print("Желаете сыграть еще раз? (Y - да): ");
            if (!scanner.next().equalsIgnoreCase("Y"))
                break;
        }
    }

    /**
     * Ход игрока (человека)
     */
    private void humanTurn() {
        int x;
        int y;
        do {
            System.out.printf("Введите координаты хода X и Y (от 1 до %d)\nчерез пробел: ",
                    playingField.getFieldSizeX());
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
            playingField.setTurnCoordinate(x, y);
            if (!playingField.isCellEmpty(x, y)) {
                System.out.println("Клетка занята");
            }
        }
        while (!playingField.isCellValid(playingField.getTurn().getX(), playingField.getTurn().getY()) ||
                !playingField.isCellEmpty(playingField.getTurn().getX(), playingField.getTurn().getY()));

        playingField.fillTurn(
                playingField.getTurn().getY(),
                playingField.getTurn().getX(),
                playingField.getDOT_HUMAN());
    }


    /**
     * Ход игрока (компьютера)
     */
    private void aiTurn() {
        int x;
        int y;

        do {
            x = random.nextInt(playingField.getFieldSizeX());
            y = random.nextInt(playingField.getFieldSizeY());
            playingField.setTurnCoordinate(x, y);
        }
        while (!playingField.isCellEmpty(playingField.getTurn().getX(), playingField.getTurn().getY()));

        playingField.fillTurn(y, x, playingField.getDOT_AI());
    }

}
