package com.application.models.weather;

import java.util.List;

public class Hourly {
    private List<String> time;
    private List<Double> temperature_2m;
    private List<Integer> relative_humidity_2m;
    private List<Double> apparent_temperature;
    private List<Integer> precipitation_probability;
    private List<Double> rain;
    private List<Integer> cloud_cover;

    public Hourly(List<String> time, List<Double> temperature_2m, List<Integer> relative_humidity_2m, List<Double> apparent_temperature, List<Integer> precipitation_probability, List<Double> rain, List<Integer> cloud_cover) {
        this.time = time;
        this.temperature_2m = temperature_2m;
        this.relative_humidity_2m = relative_humidity_2m;
        this.apparent_temperature = apparent_temperature;
        this.precipitation_probability = precipitation_probability;
        this.rain = rain;
        this.cloud_cover = cloud_cover;
    }


    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }

    public List<Double> getTemperature_2m() {
        return temperature_2m;
    }

    public void setTemperature_2m(List<Double> temperature_2m) {
        this.temperature_2m = temperature_2m;
    }

    public List<Integer> getRelative_humidity_2m() {
        return relative_humidity_2m;
    }

    public void setRelative_humidity_2m(List<Integer> relative_humidity_2m) {
        this.relative_humidity_2m = relative_humidity_2m;
    }

    public List<Double> getApparent_temperature() {
        return apparent_temperature;
    }

    public void setApparent_temperature(List<Double> apparent_temperature) {
        this.apparent_temperature = apparent_temperature;
    }

    public List<Integer> getPrecipitation_probability() {
        return precipitation_probability;
    }

    public void setPrecipitation_probability(List<Integer> precipitation_probability) {
        this.precipitation_probability = precipitation_probability;
    }

    public List<Double> getRain() {
        return rain;
    }

    public void setRain(List<Double> rain) {
        this.rain = rain;
    }

    public List<Integer> getCloud_cover() {
        return cloud_cover;
    }

    public void setCloud_cover(List<Integer> cloud_cover) {
        this.cloud_cover = cloud_cover;
    }
    
    
}
