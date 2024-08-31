package com.application.main;

import com.application.models.weather.Weather;
import java.util.function.Consumer;
import javafx.scene.control.Alert;


public class WeatherManager {
    private static Weather data;
    private static final String URL = "https://api.open-meteo.com/v1/forecast?latitude={lat}&longitude={long}&current=temperature_2m,relative_humidity_2m,apparent_temperature,is_day,precipitation,rain,weather_code,cloud_cover,wind_speed_10m,wind_direction_10m&hourly=temperature_2m,relative_humidity_2m,apparent_temperature,precipitation_probability,rain,cloud_cover&daily=temperature_2m_max,temperature_2m_min,apparent_temperature_max,apparent_temperature_min,sunrise,sunset,daylight_duration,sunshine_duration,uv_index_max,uv_index_clear_sky_max,precipitation_sum,rain_sum,precipitation_hours,precipitation_probability_max,wind_speed_10m_max,wind_direction_10m_dominant&timezone=auto";
    
    public static void load(double latitude, double longitude, Consumer<Weather> callback) {
        String completeURL = URL.replace("{lat}", Double.toString(latitude)).replace("{long}", Double.toString(longitude));
        System.out.println(completeURL);
        
        Http.getUrl(completeURL, null, (String responseText) -> {
            Weather weather = App.gson.fromJson(responseText, Weather.class);
            
            
            if(weather == null) {
                App.alertMessage(Alert.AlertType.WARNING, "Get Weather Information Fail", "");
                return;
            }
            
            data = weather;
            callback.accept(weather);
        });
    }
}
