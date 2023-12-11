package org.example;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


// Рабочий код:
public class WeatherDataFetcher {
    public static JSONObject fetchWeatherData(String s_city, String appid) throws Exception {
        // Получение данных о погоде
        URL url = new URL("http://api.openweathermap.org/data/2.5/find?q=" + s_city + "&type=like&units=metric&APPID=" + appid);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        // Парсинг JSON-ответа
        return new JSONObject(content.toString());
    }
}

