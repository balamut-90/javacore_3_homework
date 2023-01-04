package ru.eliseev.homework1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    private static String DEFAULT_URL = "src/ru/eliseev/Games/";
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    private static String strDate = "";

    public static void main(String[] args) {

        StringBuilder stringBuilder = new StringBuilder();

        createFileOrDirectory(TypeOfData.DIRECTORY, "src", null, stringBuilder);
        createFileOrDirectory(TypeOfData.DIRECTORY, "res", null, stringBuilder);
        createFileOrDirectory(TypeOfData.DIRECTORY, "savegames", null, stringBuilder);
        createFileOrDirectory(TypeOfData.DIRECTORY, "temp",null, stringBuilder);
        createFileOrDirectory(TypeOfData.DIRECTORY, "src//main", null, stringBuilder);
        createFileOrDirectory(TypeOfData.DIRECTORY, "src//test", null, stringBuilder);
        createFileOrDirectory(TypeOfData.DIRECTORY, "res//drawables", null, stringBuilder);
        createFileOrDirectory(TypeOfData.DIRECTORY, "res//vectors",null, stringBuilder);
        createFileOrDirectory(TypeOfData.DIRECTORY, "res//icons", null, stringBuilder);

        createFileOrDirectory(TypeOfData.FILE, "src//main//", "Main.java", stringBuilder);
        createFileOrDirectory(TypeOfData.FILE, "src//main//", "Utils.java", stringBuilder);
        createFileOrDirectory(TypeOfData.FILE,"temp//","temp.txt", stringBuilder);

        addLogInFile(stringBuilder.toString(), "temp//", "temp.txt");
    }

    private static void createFileOrDirectory(TypeOfData typeOfData,
                                                String pathDirectory,
                                                String nameFile,
                                                StringBuilder stringBuilder) {
        switch (typeOfData) {
            case FILE:
                stringBuilder.append(createFile(nameFile, pathDirectory));
                break;
            case DIRECTORY:
                stringBuilder.append(createDirectory(pathDirectory));
                break;
            default:
                break;
        }
    }

    private static String createDirectory(String nameDirectory) {
        File dir = new File(DEFAULT_URL + "//" + nameDirectory);
        Date date = new Date();
        strDate = dateFormat.format(date);

        if (dir.exists()) {
            return "Дата: " + strDate +  " Директория: " + dir.getName() + " - попытка повторного создания директории" + "\n";
        } else {
            if (dir.mkdir()) {
                return "Дата: " + strDate + " Директория: " + dir.getName() + " - успешно создана" + "\n";
            } else {
                return "Дата: " + strDate + " Директория: " + dir.getName() + " - попытка создания директории не успешно" + "\n";
            }
        }
    }

    private static String createFile(String nameFile, String pathFile) {
        File newFile = new File(DEFAULT_URL + pathFile + "//" + nameFile);
        try {
            if (newFile.exists()) {
                return "Дата: " + strDate + " Файл: " + nameFile + " - попытка повторного создания файла" + "\n";
            } else {
                if (newFile.createNewFile()) {
                    return "Дата: " + strDate + " Файл: " + nameFile + " - успешно создан" + "\n";
                } else {
                    System.out.println("Файл " + nameFile + " не созда");
                    return "Дата: " + strDate + " Файл: " + nameFile + " - попытка создания файла не успешно" + "\n";
                }
            }
        } catch (IOException exception) {
            return "Дата: " + strDate + "Файл: " + nameFile + " " + exception.getMessage();
        }
    }

    private static void addLogInFile(String text, String filePath, String fileName) {
        try (FileWriter writer = new FileWriter(DEFAULT_URL + filePath + "//" + fileName, true)) {
            writer.write(text);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}