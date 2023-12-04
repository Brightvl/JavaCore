package org.example.lesson_5.hw_5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Program {

    /**
     * Написать функцию, создающую резервную копию всех файлов в директории во вновь созданную папку ./backup
     */

    private static final Random random = new Random();
    private static final int CHAR_BOUND_L = 65; // ASCII-код начального символа ('A')
    private static final int CHAR_BOUND_H = 90; // ASCII-код конечного символа ('Z')
    private static final String TO_SEARCH = "GeekBrains"; // Искомое слово

    public static void main(String[] args) throws IOException {


        File filePath = new File("src/main/java/org/example/lesson_5/hw_5/data/", "/");

        {
            File backupPath = new File("src/main/java/org/example/lesson_5/hw_5/backup/", "/");
            backupAllFile(filePath, backupPath);
        }// HW_5 task-1


        {
            System.out.println(generateSymbols(15));
            writeFileContents(filePath + "sample01.txt", 10, TO_SEARCH);
            writeFileContents(filePath + "sample02.txt", 10, TO_SEARCH);

            concatenate(
                    filePath + "sample01.txt",
                    filePath + "sample02.txt",
                    filePath + "sample_out.txt");
        } // Склеивание файлов

        {
            System.out.println(searchInFile(filePath + "sample01.txt", TO_SEARCH));
            System.out.println(searchInFile(filePath + "sample02.txt", TO_SEARCH));
        } // Поиск по названию

        {
            //создаем пачку файлов с рандомными значениями внутри
            String[] fileName = new String[5];
            for (int i = 0; i < fileName.length; i++) {
                fileName[i] = "file_" + i + ".txt";
                writeFileContents(filePath + fileName[i], 15, TO_SEARCH);
                System.out.printf("Файл %s создан.\n", fileName[i]);
            }

            List<String> list = searchMatch(filePath, TO_SEARCH);
            for (String s : list) {
                System.out.printf("Файл %s содержит искомое слово '%s'\n",
                        s.replace(filePath.getCanonicalPath(), ""), TO_SEARCH);
                // replace заменил путь на "" и оставил только название файла
            }
        } // Поиск файлов в папке, содержащих заданное слово


    }

    //region Создание строки

    /**
     * Метод создает случайную строку заданной длины, используя символы от 'A' до 'Z'.
     *
     * @param count кол-во символов
     * @return
     */
    static String generateSymbols(int count) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            stringBuilder.append((char) random.nextInt(CHAR_BOUND_L, CHAR_BOUND_H + 1));
        }
        return stringBuilder.toString();
    }
    //endregion

    // region Запись в файл

    /**
     * Метод записывает в файл случайную последовательность символов заданной длины.
     *
     * @param fileName имя файла
     * @param count    количество символов
     * @param toSearch
     * @throws IOException исключение ввода вывода
     */
    static void writeFileContents(String fileName, int count, String toSearch) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            // .getBytes разбивает строку на массив байтов char[], необходимого для FileOutputStream
            fileOutputStream.write(generateSymbols(count).getBytes(StandardCharsets.UTF_8));
            if (random.nextInt(2) == 0) { // 50% шанса, что в файл запишется искомое слово
                fileOutputStream.write(toSearch.getBytes(StandardCharsets.UTF_8));
                fileOutputStream.write(generateSymbols(count).getBytes(StandardCharsets.UTF_8));
            }
        }
    }
    //endregion

    //region Склеивание файлов

    /**
     * Метод склеивает содержимое двух файлов в третий файл.
     *
     * @param fileIn1 первый файл
     * @param fileIn2 второй файл
     * @param fileOut склеенный файл
     * @throws IOException исключение ввода вывода
     */
    static void concatenate(String fileIn1, String fileIn2, String fileOut) throws IOException {
        // На запись
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileOut)) {
            int c;
            // На чтение
            try (FileInputStream fileInputStream = new FileInputStream(fileIn1)) {
                while ((c = fileInputStream.read()) != -1) {
                    fileOutputStream.write(c);
                }
            }
            // На чтение
            try (FileInputStream fileInputStream = new FileInputStream(fileIn2)) {
                while ((c = fileInputStream.read()) != -1) {
                    fileOutputStream.write(c);
                }
            }
        }
    }
    //endregion

    //region Поиск строки

    /**
     * Поиск строки внутри файла
     *
     * @param fileName   имя файла
     * @param searchWord искомое слово
     * @return true если найден
     * @throws IOException Исключение ввода вывода
     */
    static boolean searchInFile(String fileName, String searchWord) throws IOException {
        int charKey;
        byte[] searchData = searchWord.getBytes();
        int counter = 0;
        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            // записываем char, пока не дойдем до конца, если достигнут конец, то read вернет -1
            while ((charKey = fileInputStream.read()) != -1) {
                if (charKey == searchData[counter]) {
                    counter++;// счетчик++ если бит искомого слова searchData совпадает с charKey
                } else {
                    counter = 0; // обнуляем если последовательность оборвана
                    if (charKey == searchData[counter])
                        counter++; // проверка вдруг после обрыва снова идет искомое слово, а GeekGeekBrains
                }
                if (counter == searchData.length) {
                    return true; // если искомое слово равно нашему счетчику
                }
            }
            return false;
        }
    }
    //endregion

    //region Поиск по названию

    /**
     * Поиск файлов в папке, содержащих заданное слово
     *
     * @param dir    директория файлов
     * @param search название файла
     * @return возвращает List<String> с именами файлов
     * @throws IOException исключение ввода вывода
     */
    static List<String> searchMatch(File dir, String search) throws IOException {
        List<String> list = new ArrayList<>();
        File[] files = dir.listFiles(); // массив путей файлов
        if (files == null)
            return list;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) { // проверка является ли файлом
                if (searchInFile(files[i].getCanonicalPath(), search)) // поиск слова в файле
                    list.add(files[i].getCanonicalPath()); // все ок добавляем в list
            }
        }
        return list;
    }
    // endregion

    //region HW_5
    /**
     * Копирует все файлы из директории в директорию
     *
     * @param sourcePath откуда копируем
     * @param targetPath   куда
     * @throws IOException исключение ввода вывода данных
     */
    static void backupAllFile(File sourcePath, File targetPath) throws IOException {
        File[] files = sourcePath.listFiles();
        for (int i = 0; i < files.length; i++) {
            // создаем новый путь из пути и имени файла, имитируя копирование
            File out = new File(targetPath + files[i].getName());
            targetPath.mkdir(); // создает директорию если она не существует
            // На запись
            //
            try (FileOutputStream fileOutputStream = new FileOutputStream(out)) {
                int charKey;
                // На чтение
                try (FileInputStream fileInputStream = new FileInputStream(files[i])) {
                    while ((charKey = fileInputStream.read()) != -1) {
                        fileOutputStream.write(charKey);
                    }
                }
            }
        }
    }
    //endregion
}
