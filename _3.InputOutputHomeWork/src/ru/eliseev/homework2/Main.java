package ru.eliseev.homework2;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    private static String DEFAULT_URL = "src/ru/eliseev/Games/savegames//";
    private static int number = 0;

    public static void main(String[] args) {

        GameProgress gameProgress1 = new GameProgress(100, 2, 10, 10);
        GameProgress gameProgress2 = new GameProgress(50, 1, 15, 100);
        GameProgress gameProgress3 = new GameProgress(10, 3, 30, 50);

        saveGameProgress(gameProgress1);
        saveGameProgress(gameProgress2);
        saveGameProgress(gameProgress3);

    }

    private static void saveGameProgress(GameProgress gameProgress) {
        try(FileOutputStream fileOutputStream = new FileOutputStream(DEFAULT_URL + "save"+ ++number + ".dat");
                ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream)) {
            oos.writeObject(gameProgress);
            convertToZipFile("save" + number);
            deleteFile("save" + number + ".dat");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void convertToZipFile(String filename) {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(DEFAULT_URL + filename + ".zip"));
             FileInputStream fileInputStream = new FileInputStream(DEFAULT_URL + filename + ".dat")) {

            ZipEntry entry = new ZipEntry(DEFAULT_URL + filename + ".dat");
            zipOutputStream.putNextEntry(entry);

            byte[] buffer = new byte[fileInputStream.available()];
            fileInputStream.read(buffer);
            zipOutputStream.write(buffer);
            zipOutputStream.closeEntry();

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static void deleteFile(String filename) {
        File file = new File(DEFAULT_URL + filename);

        if (file.exists()) {
            System.out.println("Такой файл существует");
            if (file.delete()) {
                System.out.println("File " + filename + " успешно удален");
            } else {
                System.out.println("File" + filename + " удалить не получается");
            }
        } else {
            System.out.println("Такого файла не существует");
        }
    }
}
