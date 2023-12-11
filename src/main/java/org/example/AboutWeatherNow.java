package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//Получение информации о текущей погоде:
public class AboutWeatherNow {
    public static String getWeatherData(int cityId, String apiKey) throws Exception {
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?id=" + cityId + "&units=metric&lang=en&APPID=" + apiKey);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        return content.toString();
    }
}

