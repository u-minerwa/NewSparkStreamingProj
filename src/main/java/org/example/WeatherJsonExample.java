package org.example;

import org.json.JSONObject;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class WeatherJsonExample {
    static String CONST_EUROPE = "Europe";
    static String COMMON_CITY = "Saratov";

    public static JSONObject createWeatherJson(String commonCity, String commonCountry, int cityId, JSONObject data) {
        // Получение временной зоны:
        TimeZone tz_a = TimeZone.getTimeZone(CONST_EUROPE + "/" + COMMON_CITY); // "Europe/Saratov"
        ZonedDateTime dt_a = ZonedDateTime.now(tz_a.toZoneId());

        // Создание JSON формата:
        JSONObject weatherJsonFirst = new JSONObject();
        weatherJsonFirst.put("city", commonCity);
        weatherJsonFirst.put("country", commonCountry);
        weatherJsonFirst.put("city_id", cityId);

        // Предполагаем, что data - это объект, содержащий данные о погоде
        weatherJsonFirst.put("conditions", data.getJSONArray("weather").getJSONObject(0).getString("description"));
        weatherJsonFirst.put("date_time_now", dt_a.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        weatherJsonFirst.put("temp", data.getJSONObject("main").getDouble("temp"));
        weatherJsonFirst.put("temp_min", data.getJSONObject("main").getDouble("temp_min"));
        weatherJsonFirst.put("temp_max", data.getJSONObject("main").getDouble("temp_max"));

        return weatherJsonFirst;
    }
}


