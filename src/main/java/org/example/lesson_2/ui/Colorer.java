package org.example.lesson_2.ui;

public class Colorer {



    /**
     * Чтобы раскрасить консоль
     *
     * @param color номер цвета ANSI Escape
     * @param s     строка текста
     * @return
     */
    public String stringColor(int color, String s) {
        StringBuilder stringBuilder = new StringBuilder("\u001B[" + color + "m" + s + "\u001B[0m");
        return stringBuilder.toString();

    }

    /**
     * Чтобы раскрасить консоль
     *
     * @param color номер цвета ANSI Escape
     * @param s     char
     * @return цветной текст
     */
    public String stringColor(int color, char s) {
        StringBuilder stringBuilder = new StringBuilder("\u001B[" + color + "m" + s + "\u001B[0m");
        return stringBuilder.toString();

    }

    /**
     * Чтобы раскрасить консоль
     *
     * @param color номер цвета ANSI Escape
     * @param s     char
     * @return цветной текст
     */
    public String stringColor(int color, int s) {
        StringBuilder stringBuilder = new StringBuilder("\u001B[" + color + "m" + s + "\u001B[0m");
        return stringBuilder.toString();

    }

}
