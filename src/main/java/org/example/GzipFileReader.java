package org.example;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

public class GzipFileReader {

    public static void main(String[] args) {
        String gzipFilePath = "C:\\Users\\Админ\\IdeaProjects\\ZIPproject\\распаковка\\file.txt.gz"; //  шлях до GZIP-файлу

        try {
            // Відкриття GZIP-файлу для читання
            FileInputStream fis = new FileInputStream(gzipFilePath);
            GZIPInputStream gis = new GZIPInputStream(fis);
            InputStreamReader isr = new InputStreamReader(gis);
            BufferedReader reader = new BufferedReader(isr);

            String line;
            // Читання рядків із розпакованого файлу
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // Вивід кожного рядка в консоль
            }

            // Закриття потоків
            reader.close();
            gis.close();
            fis.close();

        } catch (IOException e) {
            System.err.println("Помилка під час читання GZIP-файлу: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
