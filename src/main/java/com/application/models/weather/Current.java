package com.application.models.weather;


public class Current {
    private String time;
    private int interval;
    private double temperature_2m;
    private int relative_humidity_2m;
    private double apparent_temperature;
    private int is_day;
    private double precipitation;
    private double rain;
    private int weather_code;
    private int cloud_cover;
    private double wind_speed_10m;
    private int wind_direction_10m;

    public Current(String time, int interval, double temperature_2m, int relative_humidity_2m, double apparent_temperature, int is_day, double precipitation, double rain, int weather_code, int cloud_cover, double wind_speed_10m, int wind_direction_10m) {
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

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public double getTemperature_2m() {
        return temperature_2m;
    }

    public void setTemperature_2m(double temperature_2m) {
        this.temperature_2m = temperature_2m;
    }

    public int getRelative_humidity_2m() {
        return relative_humidity_2m;
    }

    public void setRelative_humidity_2m(int relative_humidity_2m) {
        this.relative_humidity_2m = relative_humidity_2m;
    }

    public double getApparent_temperature() {
        return apparent_temperature;
    }

    public void setApparent_temperature(double apparent_temperature) {
        this.apparent_temperature = apparent_temperature;
    }

    public int getIs_day() {
        return is_day;
    }

    public void setIs_day(int is_day) {
        this.is_day = is_day;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }

    public double getRain() {
        return rain;
    }

    public void setRain(double rain) {
        this.rain = rain;
    }

    public int getWeather_code() {
        return weather_code;
    }

    public void setWeather_code(int weather_code) {
        this.weather_code = weather_code;
    }

    public int getCloud_cover() {
        return cloud_cover;
    }

    public void setCloud_cover(int cloud_cover) {
        this.cloud_cover = cloud_cover;
    }

    public double getWind_speed_10m() {
        return wind_speed_10m;
    }

    public void setWind_speed_10m(double wind_speed_10m) {
        this.wind_speed_10m = wind_speed_10m;
    }

    public int getWind_direction_10m() {
        return wind_direction_10m;
    }

    public void setWind_direction_10m(int wind_direction_10m) {
        this.wind_direction_10m = wind_direction_10m;
    }
    
    
}
