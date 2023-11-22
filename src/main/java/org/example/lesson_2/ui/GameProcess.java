package org.example.lesson_2.ui;

import org.example.lesson_2.field.GameField;
import org.example.lesson_2.mechanics.GameState;
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
        this.human = new Human(this);
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
        choiceDifficulty();
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
                System.out.println("Сменить уровень сложности? (Y - да): ");
                choice = scanner.next();
                if (choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("Н")) {
                    choiceDifficulty();
                }
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
            System.out.printf("Игрок: %s Сложность: %s\n", bot.getName(), showDifficult());
            playingField.printField();
            if (gameState.checkGameState(bot.getTurn().getX(), bot.getTurn().getY(),
                    bot.getDot(), "Победил Автобот!"))
                break;
        }
    }

    /**
     * Осуществляет ход игрока (человека)
     */
    private void humanTurn() {
        human.humanTurn(scanner);
    }

    /**
     * Осуществляет ход игрока (компьютера)
     */
    private void botTurn() {
        bot.botTurn(human, random);
    }

    public GameField getPlayingField() {
        return playingField;
    }

    public GameState getGameState() {
        return gameState;
    }


    /**
     * Выбор сложности
     */
    public boolean choiceDifficulty() {
        System.out.println("""
                Уровень сложности компьютера:
                1. Глупая железяка
                2. Доставит трудности
                3. Кажется у него есть разум
                Введите номер: """);
        try {
            int choice = scanner.nextInt() - 1;
            if (choice < 0 || choice > 2) {
                System.out.printf("Вы не ввели число из диапазона\nСтандартный уровень сложности: %s\n",
                        showDifficult());
            } else {
                bot.choiceDifficulty(choice);
                return true;
            }
        } catch (InputMismatchException e) {
            System.out.printf("Вы не ввели целое число!\nСтандартный уровень сложности: %s\n", showDifficult());
            scanner.nextLine(); // очистить буфер ввода
        }
        return false;
    }

    private String showDifficult() {
        String difficult;
        switch (bot.getLevelBot()) {
            case 0 -> {
                difficult = "Глупая железяка";
            }
            case 2 -> {
                difficult = "Кажется у него есть разум";
            }
            default -> {
                difficult = "Доставит трудности";
            }
        }
        return difficult;
    }
}

