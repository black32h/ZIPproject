package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipValidator {
    public static boolean isValidZipFile(String filePath) {
        // Відкриваємо файл для читання як ZIP
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(filePath))) {
            ZipEntry entry;
            // Читаємо всі записи у файлі ZIP
            while ((entry = zis.getNextEntry()) != null) {
                // Якщо знайдено запис, закриваємо її та продовжуємо перевірку
                zis.closeEntry();
            }
            return true; // Якщо всі записи успішно прочитані, файл дійсний
        } catch (IOException e) {
            // Якщо відбувається IOException, це означає, що файл не є дійсним ZIP
            System.out.println("Неправильний формат ZIP-файлу: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        String filePath = "C:\\Users\\Админ\\IdeaProjects\\ZIPproject\\распаковка\\file.txt.gz";

        // Перевіряємо, чи є файл дійсним ZIP-файлом
        if (isValidZipFile(filePath)) {
            System.out.println("Файл є дійсним ZIP-файлом.");
        } else {
            System.out.println("Файл не є дійсним ZIP-файлом.");
        }
    }
}
