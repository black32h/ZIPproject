package org.example;

import java.io.FileInputStream; // Імпорт класу для читання файлів
import java.io.FileOutputStream; // Імпорт класу для запису файлів
import java.io.IOException; // Імпорт класу для обробки винятків
import java.util.zip.GZIPOutputStream; // Імпорт класу для стиснення у формат GZIP

public class GzipCompressor {

    public static void main(String[] args) {
        String sourceFile = "C:\\Users\\Админ\\IdeaProjects\\ZIPproject\\src\\main\\java\\org\\example\\LunyToons";// Шлях до вихідного текстового файлу
        String gzipFile = "C:\\Users\\Админ\\IdeaProjects\\ZIPproject\\распаковка\\file.txt.gz";// Шлях до стиснутого файлу

        try {
            compressFileToGzip(sourceFile, gzipFile); // Викликаємо метод для стиснення файлу
            System.out.println("Стиснення завершено!"); // Повідомлення про успішне стиснення
        } catch (IOException e) {
            // Виводимо повідомлення про помилку, якщо сталося виключення
            System.err.println("Помилка під час стиснення файлу: " + e.getMessage());
        }
    }

    /**
     * Метод для стиснення текстового файлу у формат GZIP.
     *
     * @param sourceFile Шлях до вихідного текстового файлу
     * @param gzipFile   Шлях до файлу, куди буде записано стиснені дані у форматі GZIP
     * @throws IOException Якщо сталася помилка вводу-виводу
     */
    private static void compressFileToGzip(String sourceFile, String gzipFile) throws IOException {
        // Створюємо потік для читання вихідного файлу
        try (FileInputStream fis = new FileInputStream(sourceFile);
             // Створюємо потік для запису стиснених даних у GZIP-форматі
             FileOutputStream fos = new FileOutputStream(gzipFile);
             GZIPOutputStream gzipOS = new GZIPOutputStream(fos)) {

            byte[] buffer = new byte[1024]; // Буфер для зберігання прочитаних байтів
            int bytesRead; // Змінна для зберігання кількості прочитаних байтів

            // Читаємо дані з вихідного файлу та записуємо їх у GZIP-формат
            while ((bytesRead = fis.read(buffer)) != -1) {
                gzipOS.write(buffer, 0, bytesRead); // Записуємо байти в GZIP потік
            }
        }
    }
}
