package com.application.models.weather;


public class HourlyUnits {
    private String time;
    private String temperature_2m;
    private String relative_humidity_2m;
    private String apparent_temperature;
    private String precipitation_probability;
    private String rain;
    private String cloud_cover;

    public HourlyUnits(String time, String temperature_2m, String relative_humidity_2m, String apparent_temperature, String precipitation_probability, String rain, String cloud_cover) {
        this.time = time;
        this.temperature_2m = temperature_2m;
        this.relative_humidity_2m = relative_humidity_2m;
        this.apparent_temperature = apparent_temperature;
        this.precipitation_probability = precipitation_probability;
        this.rain = rain;
        this.cloud_cover = cloud_cover;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getPrecipitation_probability() {
        return precipitation_probability;
    }

    public void setPrecipitation_probability(String precipitation_probability) {
        this.precipitation_probability = precipitation_probability;
    }

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }

    public String getCloud_cover() {
        return cloud_cover;
    }

    public void setCloud_cover(String cloud_cover) {
        this.cloud_cover = cloud_cover;
    }
    
    
}
