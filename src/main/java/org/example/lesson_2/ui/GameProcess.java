package org.example.lesson_2.ui;

import org.example.lesson_2.mechanics.GameState;
import org.example.lesson_2.field.PlayingField;
import org.example.lesson_2.players.Bot;
import org.example.lesson_2.players.Human;

import java.util.Random;
import java.util.Scanner;

public class GameProcess {


    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();

    private PlayingField playingField;
    private GameState gameState;

    private Human human;
    private Bot bot;


    public GameProcess(int widthField, int heightFields, int winCount) {
        this.playingField = new PlayingField(widthField, heightFields);
        this.gameState = new GameState(this, playingField, winCount);
        this.human = new Human();
        this.bot = new Bot();

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
                if (gameState.checkGameState(human.getTurn().getX(), human.getTurn().getY(),
                        human.getDot(), "Вы победили!"))

                    break;
//                botTurn();
                playingField.printField();
                if (gameState.checkGameState(bot.getTurn().getX(), bot.getTurn().getY(),
                        bot.getDot(), "Победил Автобот!"))
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
        do {
            System.out.printf("Введите координаты хода X и Y (от 1 до %d)\nчерез пробел: ",
                    playingField.getFieldSizeX());
            int x = scanner.nextInt() - 1;
            int y = scanner.nextInt() - 1;
            human.setTurnCoordinate(x, y);
            System.out.printf("%d, %s",human.getTurn().getX(),human.getTurn().getY());
            if (!playingField.isCellEmpty(x, y)) {
                System.out.println("Клетка занята");
            }
        }
        while (!playingField.isCellValid(human.getTurn().getX(), human.getTurn().getY()) ||
                !playingField.isCellEmpty(human.getTurn().getX(), human.getTurn().getY()));

        playingField.fillTurn(
                human.getTurn().getY(),
                human.getTurn().getX(),
                human.getDot());
    }


//    /**
//     * Ход игрока (компьютера)
//     */
//    private void botTurn() {
//        int x;
//        int y;
//
//        do {
//            x = random.nextInt(playingField.getFieldSizeX());
//            y = random.nextInt(playingField.getFieldSizeY());
//            playingField.setTurnCoordinate(x, y);
//        }
//        while (!playingField.isCellEmpty(playingField.getTurn().getX(), playingField.getTurn().getY()));
//
//        playingField.fillTurn(y, x, playingField.getDOT_AI());
//    }


    public Human getHuman() {
        return human;
    }

}
