package org.example.lesson_1.sample;

import org.example.lesson_1.other_class.Decorator;
import org.example.lesson_1.other_class.OtherClass;

/**

 *     1 вариант запуска
 *     Построение и запуск архива .jar
 * cd C:\Users\Bright_vl\Documents\Code\Java\JavaCore\out\artifacts\JavaCore_jar
 * java -jar JavaCore_jar
 * <p>
 *     2 вариант запуска
 *     Переходим в место где начинается проект
 * cd C:/Users/Bright_vl/Documents/Code/Java/JavaCore/src/main/
 *     Указываем путь точки входа
 * -sourcepath -> папка с кодом, по сути синим в идее подсвечена
 * -d out -> указывает на файл который будет откомпилирован в байт-код, точка входа
 * javac -sourcepath ./java -d out ./java/org/example/lesson_1/sample/App.java
 * <p>
 *     Запускаем указывая полное наименование пакета
 * java -classpath ./out org.example.lesson_1.sample.App
 * <p>
 *     Создание javadoc
 * javadoc -encoding utf8 -d docs -sourcepath ./java -cp ./out -subpackages org
 * <p>
 *     Работа с Docker
 *     Установка Docker
 * https://www.docker.com/products/docker-desktop/
 * https://hub.docker.com/
 *  * <p>
 *      можно установить образ докера через консоль к примеру так:
 * docker pull bellsoft/liberica-openjdk-alpine:latest
 *
 *      Прописываем dockerfile !важно чтобы
 * FROM bellsoft/liberica-openjdk-alpine:11.0.16 // указываем используемый образ докера
 *      копируем откуда будем копировать исходники, в относительно root директории Linux
 * COPY ./java ./src
 *      создаем директорию out
 * RUN mkdir ./out
 *      компилируем исходники
 * RUN javac -sourcepath ./src -d out src/org/example/lesson_1/sample/App.java
 *      запуск на исполнение
 * CMD java -classpath ./out org.example.lesson_1.sample.App
 * 4 команды выполнятся на этапе сборки а CMD на этапе контейнера
 * <p>
 *     собираем контейнер docker относительно текущей директории
 *     -t имя образа:версия
 * docker build . -t mycalcapp:v1
 * <p>
 *     Запускаем контейнер
 * docker runGame mycalcapp:v1
 * docker runGame --rm mycalcapp:v1 // чтобы сразу удалялся после запуска
 */
public class App {
    /**
     * Точка входа в программу. С неё всегда всё начинается.
     *
     * @param args стандартные аргументы командной строки
     */
    public static void main(String[] args) {
        int result = OtherClass.add(2, 2);
        System.out.println(Decorator.decorate(result));
        result = OtherClass.sub(2, 2);
        System.out.println(Decorator.decorate(result));
        result = OtherClass.mul(2, 2);
        System.out.println(Decorator.decorate(result));
        result = OtherClass.div(2, 2);
        System.out.println(Decorator.decorate(result));
    }
}