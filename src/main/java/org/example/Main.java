package org.example;

import org.json.JSONObject;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;


public class Main {
    public static void main(String[] args) throws Exception {
        String CONST_MOSCOW = "Moscow";
        String CONST_EUROPE = "Europe";
        String common_city = "Saratov";
        String common_country = "RU";
        String country = "Russia";
        String s_city = common_city+','+common_country;
        int city_id = 0;
        String appId = "2a9391878d3c4a87279b49bdc5f73a9d";

        String path = "./jsons";
        String weatherStr = "weather";
        String dotJson = ".json";

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        while (true){
            // Получение данных о погоде:
            JSONObject data = WeatherDataFetcher.fetchWeatherData(s_city, appId);
            city_id = data.getJSONArray("list").getJSONObject(0).getInt("id");

            // Получение временной зоны:
            TimeZone tz_a = TimeZone.getTimeZone("Europe/Saratov"); // "Europe/Saratov"
            ZonedDateTime dt_a = ZonedDateTime.now(tz_a.toZoneId());

            // Форматирование даты и времени:
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = dt_a.format(formatter);

            // Создание JSON формата:
            JSONObject weatherJsonFirst = new JSONObject();
            weatherJsonFirst.put("city", common_city);
            weatherJsonFirst.put("country", common_country);
            weatherJsonFirst.put("city_id", city_id);
            weatherJsonFirst.put("conditions", data.getJSONArray("list").getJSONObject(0).getJSONArray("weather").
                    getJSONObject(0).getString("description"));
            weatherJsonFirst.put("date_time_now", formattedDateTime);
            weatherJsonFirst.put("temp", data.getJSONArray("list").getJSONObject(0).getJSONObject("main").getDouble("temp"));
            weatherJsonFirst.put("temp_min", data.getJSONArray("list").getJSONObject(0).getJSONObject("main").getDouble("temp_min"));
            weatherJsonFirst.put("temp_max", data.getJSONArray("list").getJSONObject(0).getJSONObject("main").getDouble("temp_max"));

            // Преобразование в строку JSON с отступами:
            String jsonString = weatherJsonFirst.toString();

            // Вывод строки JSON:
            System.out.println(jsonString);

            int count = WeatherJsonWriter.writeWeatherJson(path, weatherStr, dotJson, jsonString);

            System.out.println("Count = " + count);
            System.out.println("Successful!");

            TimeUnit.SECONDS.sleep(5);
        }

    }
}


