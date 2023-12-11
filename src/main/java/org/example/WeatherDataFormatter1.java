package org.example;

import org.json.JSONObject;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class WeatherDataFormatter1 {
    static String CONST_EUROPE = "Europe";
    static String COMMON_CITY = "Saratov";

    public static JSONObject formatWeatherData(String commonCity, String commonCountry, int cityId,
                                               String weatherDescription, double temp, double tempMin, double tempMax) {
        // Создание временной зоны:
        ZoneId zoneId = ZoneId.of(CONST_EUROPE + "/" + COMMON_CITY); // "Europe/Saratov"
        ZonedDateTime dateTimeNow = ZonedDateTime.now(zoneId);

        // Форматирование даты и времени:
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = dateTimeNow.format(formatter);

        // Создание JSON формата:
        JSONObject weatherJson = new JSONObject();
        weatherJson.put("city", commonCity);
        weatherJson.put("country", commonCountry);
        weatherJson.put("city_id", cityId);
        weatherJson.put("conditions", weatherDescription);
        weatherJson.put("date_time_now", formattedDateTime);
        weatherJson.put("temp", temp);
        weatherJson.put("temp_min", tempMin);
        weatherJson.put("temp_max", tempMax);

        return weatherJson;
    }
}


