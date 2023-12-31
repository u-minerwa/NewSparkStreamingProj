package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


//Проверка наличия в базе информации о нужном населённом пункте:
public class WeatherApiExample {
    public static String getWeatherData(String city, String apiKey) throws Exception {
        URL url = new URL("http://api.openweathermap.org/data/2.5/find?q=" + city + "&type=like&units=metric&APPID=" + apiKey);
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

