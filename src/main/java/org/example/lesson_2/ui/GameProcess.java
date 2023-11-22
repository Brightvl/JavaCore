package org.example.lesson_2.ui;

import org.example.lesson_2.mechanics.GameState;
import org.example.lesson_2.field.GameField;
import org.example.lesson_2.players.Bot;
import org.example.lesson_2.players.Human;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


/**
 * Класс отвечает за управление игровым процессом, включая ходы игроков, отображение игрового интерфейса и обработку
 * состояний игры.
 */
public class GameProcess {

    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();

    private GameField playingField;
    private GameState gameState;

    private Human human;
    private Bot bot;

    private boolean gameRun;


    /**
     * Конструктор класса
     *
     * @param widthField  Ширина игрового поля.
     * @param heightField Высота игрового поля.
     * @param winCount    Количество символов для победы.
     */
    public GameProcess(int widthField, int heightField, int winCount) {
        this.playingField = new GameField(widthField, heightField);
        this.gameState = new GameState(playingField, winCount);
        this.human = new Human();
        this.bot = new Bot(this);
        this.gameRun = true;

    }

    /**
     * Запускает игру
     */
    public void runGame() {
        menuWelcome();
        gameUi();
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
     * Отображает игровой процесс, включая ходы игроков и проверку состояния игры.
     */
    private void gameUi() {
        playingField.initialize(); // Инициализация доски
        System.out.println("Игрок: " + human.getName());
        playingField.printField(); // Отрисовка

        while (gameRun) {
            gameProcess(); // запуск игрового процесса

            System.out.print("Желаете сыграть еще раз? (Y - да): ");
            String choice = scanner.next();
            if (choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("Н")) {
                playingField.initialize();
                playingField.printField();
            } else {
                scanner.close();
                this.gameRun = false;
            }
        }
    }

    /**
     * Запускает игровой процесс
     */
    private void gameProcess() {
        while (true) {
            humanTurn(); // ход игрока
            System.out.println("Игрок: " + human.getName());
            playingField.printField();
            if (gameState.checkGameState(human.getTurn().getX(), human.getTurn().getY(),
                    human.getDot(), "Вы победили!")) {
                break;
            }
            botTurn(); // ход бота
            System.out.println("Игрок: " + bot.getName());
            playingField.printField();
            if (gameState.checkGameState(bot.getTurn().getX(), bot.getTurn().getY(),
                    bot.getDot(), "Победил Автобот!"))
                break;
        }
    }

    /**
     * Осуществляет ход игрока (человека), запрашивая координаты и проверяя их корректность.
     */
    private void humanTurn() {
        while (true) {
            try {
                System.out.printf("Введите координаты хода X и Y (от 1 до %d)\nчерез пробел: ",
                        playingField.getFieldSizeX());

                int x = scanner.nextInt() - 1;
                int y = scanner.nextInt() - 1;
                human.setTurnCoordinate(x, y);

                if (!playingField.isCellValid(x, y)) {
                    System.out.println("Клетка вне диапазона");
                } else if (!playingField.isCellEmpty(x, y)) {
                    System.out.println("Клетка занята");
                } else {
                    playingField.fillTurn(y, x, human.getDot());
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Пожалуйста, введите целые числа.");
                scanner.nextLine(); // очистить буфер ввода
            }
        }
    }


    /**
     * Осуществляет ход игрока (компьютера).
     */
    private void botTurn() {

        // todo усложнить логику бота метод написан в нем
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

    public GameField getPlayingField() {
        return playingField;
    }

    public GameState getGameState() {
        return gameState;
    }

}
