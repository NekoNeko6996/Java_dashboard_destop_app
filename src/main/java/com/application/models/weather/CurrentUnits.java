package com.application.models.weather;


public class CurrentUnits {
    private String time;
    private String interval;
    private String temperature_2m;
    private String relative_humidity_2m;
    private String apparent_temperature;
    private String is_day;
    private String precipitation;
    private String rain;
    private String weather_code;
    private String cloud_cover;
    private String wind_speed_10m;
    private String wind_direction_10m;

    public CurrentUnits(String time, String interval, String temperature_2m, String relative_humidity_2m, String apparent_temperature, String is_day, String precipitation, String rain, String weather_code, String cloud_cover, String wind_speed_10m, String wind_direction_10m) {
        this.time = time;
        this.interval = interval;
        this.temperature_2m = temperature_2m;
        this.relative_humidity_2m = relative_humidity_2m;
        this.apparent_temperature = apparent_temperature;
        this.is_day = is_day;
        this.precipitation = precipitation;
        this.rain = rain;
        this.weather_code = weather_code;
        this.cloud_cover = cloud_cover;
        this.wind_speed_10m = wind_speed_10m;
        this.wind_direction_10m = wind_direction_10m;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public String getTemperature_2m() {
        return temperature_2m;
    }

    public void setTemperature_2m(String temperature_2m) {
        this.temperature_2m = temperature_2m;
    }

    public String getRelative_humidity_2m() {
        return relative_humidity_2m;
    }

    public void setRelative_humidity_2m(String relative_humidity_2m) {
        this.relative_humidity_2m = relative_humidity_2m;
    }

    public String getApparent_temperature() {
        return apparent_temperature;
    }

    public void setApparent_temperature(String apparent_temperature) {
        this.apparent_temperature = apparent_temperature;
    }

    public String getIs_day() {
        return is_day;
    }

    public void setIs_day(String is_day) {
        this.is_day = is_day;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }

    public String getWeather_code() {
        return weather_code;
    }

    public void setWeather_code(String weather_code) {
        this.weather_code = weather_code;
    }

    public String getCloud_cover() {
        return cloud_cover;
    }

    public void setCloud_cover(String cloud_cover) {
        this.cloud_cover = cloud_cover;
    }

    public String getWind_speed_10m() {
        return wind_speed_10m;
    }

    public void setWind_speed_10m(String wind_speed_10m) {
        this.wind_speed_10m = wind_speed_10m;
    }

    public String getWind_direction_10m() {
        return wind_direction_10m;
    }

    public void setWind_direction_10m(String wind_direction_10m) {
        this.wind_direction_10m = wind_direction_10m;
    }
    
    
}
