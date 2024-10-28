package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipFileLister {
    // Метод для виведення списку файлів у ZIP-архіві
    public static void listFilesInZip(String zipFilePath) {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry entry;
            System.out.println("Список файлів у ZIP-архіві:");

            // Проходимо по кожному запису в архіві
            while ((entry = zis.getNextEntry()) != null) {
                System.out.println(entry.getName()); // Виводимо назву файлу
            }
        } catch (IOException e) {
            // Якщо виникає IOException, виводимо повідомлення про помилку
            System.out.println("Помилка при читанні ZIP-архіву: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String zipFilePath = "C:\\Users\\Админ\\IdeaProjects\\ZIPproject\\распаковка\\file.txt.gz"; // Шлях до ZIP-архіву

        // Викликаємо метод для виведення списку файлів
        listFilesInZip(zipFilePath);
    }
}
