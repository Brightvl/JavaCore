package org.example.lesson_5.hw_5;

import java.io.File;

/**
 * Доработайте класс Tree и метод print который мы разработали на семинаре. Ваш метод должен распечатать полноценное дерево директорий и файлов относительно корневой директории.
 */
public class Tree {

    public static void main(String[] args) {
        print(new File("."), "", true);
    }

    static void print(File file, String indent, boolean isLast){
        System.out.print(indent);
        if (isLast){
            System.out.print("└─");
            indent += "  ";
        }
        else {
            System.out.print("├─");
            indent += "│ ";
        }
        System.out.println(file.getName());

        File[] files = file.listFiles();
        if (files == null)
            return;

        int subDirTotal = 0;
        for (int i = 0; i < files.length; i++){
            if (files[i].isDirectory())
                subDirTotal++;
        }

        int subDirCounter = 0;
        for (int i = 0; i < files.length; i++){
            if (files[i].isDirectory()){
                print(files[i], indent, subDirTotal == ++subDirCounter);
            }
        }
    }

}
