package org.example.lesson_5.s_5;

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
     * 1.  Создать 2 текстовых файла, примерно по 50-100 символов в каждом(особого значения не имеет);
     * 2.  Написать метод, «склеивающий» эти файлы, то есть вначале идет текст из первого файла,
     * потом текст из второго.
     * 3.* Написать метод, который проверяет, присутствует ли указанное пользователем слово
     * в файле (работаем только с латиницей).
     * 4.* Написать метод, проверяющий, есть ли указанное слово в папке
     */

    private static final Random random = new Random();
    private static final int CHAR_BOUND_L = 65; // Номер начального символа
    private static final int CHAR_BOUND_H = 90; // Номер конечного символа
    private static final String TO_SEARCH = "GeekBrains";

    public static void main(String[] args) throws IOException {

        System.out.println(generateSymbols(15));
        writeFileContents("sample01.txt", 30, TO_SEARCH);
        writeFileContents("sample02.txt", 50, TO_SEARCH);
        concatenate("sample01.txt", "sample02.txt", "sample_out.txt");

        System.out.println(searchInFile("sample01.txt", TO_SEARCH));
        System.out.println(searchInFile("sample02.txt", TO_SEARCH));

        String[] fileName = new String[10];
        for (int i = 0; i < fileName.length; i++) {
            fileName[i] = "file_" + i + ".txt";
            writeFileContents(fileName[i], 15, TO_SEARCH);
            System.out.printf("Файл %s создан.\n", fileName[i]);
        }

        List<String> list = searchMatch(new File("."), TO_SEARCH);
        for (String s : list) {
            System.out.printf("Файл %s содержит искомое слово '%s'\n", s, TO_SEARCH);
        }

    }

    /**
     * Метод генерации случайной последовательности символов
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

    static void writeFileContents(String fileName, int count) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            fileOutputStream.write(generateSymbols(count).getBytes(StandardCharsets.UTF_8));
        }
    }

    static void writeFileContents(String fileName, int count, String toSearch) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            fileOutputStream.write(generateSymbols(count).getBytes(StandardCharsets.UTF_8));
            if (random.nextInt(2) == 0) { // 50%
                fileOutputStream.write(toSearch.getBytes(StandardCharsets.UTF_8));
                fileOutputStream.write(generateSymbols(count).getBytes(StandardCharsets.UTF_8));
            }
        }
    }

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

    static boolean searchInFile(String fileName, String searchWord) throws IOException {
        int c;
        byte[] searchData = searchWord.getBytes();
        int counter = 0;
        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            while ((c = fileInputStream.read()) != -1) {
                if (c == searchData[counter]) {
                    counter++;
                } else {
                    counter = 0;
                    if (c == searchData[counter])
                        counter++;
                }
                if (counter == searchData.length) {
                    return true;
                }
            }
            return false;
        }
    }

    static List<String> searchMatch(File dir, String search) throws IOException {
        List<String> list = new ArrayList<>();
        File[] files = dir.listFiles();
        if (files == null)
            return list;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                if (searchInFile(files[i].getCanonicalPath(), search))
                    list.add(files[i].getCanonicalPath());
            }
        }
        return list;
    }

}
