package org.example;

import java.io.*; // Імпортуємо класи для роботи з файлами
import java.util.zip.ZipEntry; // Імпортуємо клас для роботи з елементами ZIP-архіву
import java.util.zip.ZipInputStream; // Імпортуємо клас для читання ZIP-архівів

public class ZipExtractor {
    public static void main(String[] args) {
        String zipFilePath = "C:\\Users\\Админ\\IdeaProjects\\ZIPproject\\src\\main\\java\\org\\example\\LunyToons"; // Шлях до ZIP-файлу
        String destDirectory = "\"C:\\Users\\Админ\\IdeaProjects\\ZIPproject\\распаковка\""; // Директорія для розпаковування

        try {
            unzip(zipFilePath, destDirectory); // Викликаємо метод для розпаковування
            System.out.println("Розпаковка завершена!"); // Повідомлення про успішну розпаковку
        } catch (IOException e) {
            // Виводимо помилку, якщо сталася проблема під час розпаковування
            System.err.println("Помилка при розпаковуванні: " + e.getMessage());
        }
    }

    /**
     * Метод для розпаковування ZIP-файлу в зазначену директорію.
     *
     * @param zipFilePath   Шлях до ZIP-файлу
     * @param destDirectory Директорія для розпаковування
     * @throws IOException Якщо сталася помилка вводу-виводу
     */
    private static void unzip(String zipFilePath, String destDirectory) throws IOException {
        // Створюємо потік для читання ZIP-файлу
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry entry; // Змінна для зберігання запису ZIP-архіву

            // Читаємо записи з ZIP-файлу
            while ((entry = zipInputStream.getNextEntry()) != null) {
                // Отримуємо шлях до файлу
                String filePath = destDirectory + entry.getName();

                // Якщо це директорія, створюємо її
                if (entry.isDirectory()) {
                    File dir = new File(filePath); // Створюємо об'єкт директорії
                    dir.mkdirs(); // Створюємо всі необхідні проміжні директорії
                } else {
                    // Якщо це файл, розпаковуємо його
                    // Створюємо всі необхідні директори
                    new File(filePath).getParentFile().mkdirs(); // Створюємо батьківську директорію

                    // Записуємо файл
                    try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
                        byte[] buffer = new byte[1024]; // Буфер для читання даних
                        int bytesRead; // Кількість прочитаних байтів

                        // Читаємо з ZIP-потоку та записуємо в файл
                        while ((bytesRead = zipInputStream.read(buffer)) != -1) {
                            bos.write(buffer, 0, bytesRead); // Записуємо прочитані байти в файл
                        }
                    }
                }
                // Закриваємо запис, щоб перейти до наступного
                zipInputStream.closeEntry(); // Закриваємо поточний запис ZIP
            }
        }
    }
}
