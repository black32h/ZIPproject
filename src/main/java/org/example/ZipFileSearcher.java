package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipFileSearcher {
    // Метод для перевірки наявності файлу у ZIP-архіві
    public static boolean isFileInZip(String zipFilePath, String fileName) {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry entry;
            // Проходимо по кожному запису в архіві
            while ((entry = zis.getNextEntry()) != null) {
                // Якщо знайдено файл з назвою fileName
                if (entry.getName().equals(fileName)) {
                    return true; // Файл знайдено
                }
            }
        } catch (IOException e) {
            // Якщо виникає IOException, виводимо повідомлення про помилку
            System.out.println("Помилка при читанні ZIP-архіву: " + e.getMessage());
        }
        return false; // Файл не знайдено
    }

    public static void main(String[] args) {
        String zipFilePath = "C:\\Users\\Админ\\IdeaProjects\\ZIPproject\\распаковка\\file.txt.gz"; // Шлях до ZIP-архіву
        String fileName = "file/to/find.txt"; // Ім'я файлу, який потрібно знайти

        // Виконуємо пошук файлу в архіві
        if (isFileInZip(zipFilePath, fileName)) {
            System.out.println("Файл " + fileName + " знайдено в архіві.");
        } else {
            System.out.println("Файл " + fileName + " не знайдено в архіві.");
        }
    }
}
