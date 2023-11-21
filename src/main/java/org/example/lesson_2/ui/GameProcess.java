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
        this.gameState = new GameState(playingField, winCount);
        this.human = new Human();
        this.bot = new Bot(this);

    }

    /**
     * Запуск игры
     */
    public void run() {
        // todo сделать приветствие
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

        playingField.initialize();
        playingField.printField();
        while (true) {
            while (true) {
                humanTurn();
                System.out.println("Имя игрока -> " + human.getName());
                playingField.printField();
                if (gameState.checkGameState(human.getTurn().getX(), human.getTurn().getY(),
                        human.getDot(), "Вы победили!"))

                    break;
                botTurn();
                System.out.println("Ход бота");
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
            // TODO надо сделать нормальный ввод
            int x = scanner.nextInt() - 1;
            int y = scanner.nextInt() - 1;
            human.setTurnCoordinate(x, y);
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


    /**
     * Ход игрока (компьютера)
     */
    private void botTurn() {

        if (!bot.predictionOfHumanVictory(human)) {
            do {
                int x = random.nextInt(playingField.getFieldSizeX());
                int y = random.nextInt(playingField.getFieldSizeY());
                bot.setTurnCoordinate(x, y);
            }
            while (!playingField.isCellEmpty(bot.getTurn().getX(), bot.getTurn().getY()));

            playingField.fillTurn(
                    bot.getTurn().getY(),
                    bot.getTurn().getX(),
                    bot.getDot());
        }
    }


    private boolean choiceActions() {
        // Генерируем случайное число от 0 до 99
        int randomChance = random.nextInt(100);

        // Вероятность 30% для выбора случайного действия
        if (randomChance < 10) {
            // Выбираем случайное действие
            return false;
        } else {
            // Выполняем другое действие
            return false;
        }
    }

    public PlayingField getPlayingField() {
        return playingField;
    }

    public GameState getGameState() {
        return gameState;
    }

}
