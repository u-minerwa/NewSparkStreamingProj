package org.example;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// Работаем с JSON и файлами:
public class WeatherJsonWriter {
    public static int writeWeatherJson(String path, String weatherStr, String dotJson, String weatherJsonFirst) {
        int count = 1;
        String filepath = weatherStr + count + dotJson;     // "weather1.json"
        Path filePath = Paths.get(path, filepath);          // "./jsons/weather1.json"

        if (Files.isRegularFile(filePath)) {
            while (Files.isRegularFile(filePath)) {
                count++;
                filepath = weatherStr + count + dotJson;
                filePath = Paths.get(path, filepath);
            }
        }

        try {
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
//            Files.writeString(filePath, weatherJsonFirst);
            List<String> lines = new ArrayList<>();
            lines.add(weatherJsonFirst);
            Files.write(filePath, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }
}

