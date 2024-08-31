package com.application.models.weather;

import java.util.List;

public class Daily {

    private List<String> time;
    private List<Double> temperature_2m_max;
    private List<Double> temperature_2m_min;
    private List<Double> apparent_temperature_max;
    private List<Double> apparent_temperature_min;
    private List<String> sunrise;
    private List<String> sunset;
    private List<Double> daylight_duration;
    private List<Double> sunshine_duration;
    private List<Double> uv_index_max;
    private List<Double> uv_index_clear_sky_max;
    private List<Double> precipitation_sum;
    private List<Double> rain_sum;
    private List<Integer> precipitation_hours;
    private List<Integer> precipitation_probability_max;
    private List<Double> wind_speed_10m_max;
    private List<Integer> wind_direction_10m_dominant;

    public Daily(List<String> time, List<Double> temperature_2m_max, List<Double> temperature_2m_min, List<Double> apparent_temperature_max, List<Double> apparent_temperature_min, List<String> sunrise, List<String> sunset, List<Double> daylight_duration, List<Double> sunshine_duration, List<Double> uv_index_max, List<Double> uv_index_clear_sky_max, List<Double> precipitation_sum, List<Double> rain_sum, List<Integer> precipitation_hours, List<Integer> precipitation_probability_max, List<Double> wind_speed_10m_max, List<Integer> wind_direction_10m_dominant) {
        this.time = time;
        this.temperature_2m_max = temperature_2m_max;
        this.temperature_2m_min = temperature_2m_min;
        this.apparent_temperature_max = apparent_temperature_max;
        this.apparent_temperature_min = apparent_temperature_min;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.daylight_duration = daylight_duration;
        this.sunshine_duration = sunshine_duration;
        this.uv_index_max = uv_index_max;
        this.uv_index_clear_sky_max = uv_index_clear_sky_max;
        this.precipitation_sum = precipitation_sum;
        this.rain_sum = rain_sum;
        this.precipitation_hours = precipitation_hours;
        this.precipitation_probability_max = precipitation_probability_max;
        this.wind_speed_10m_max = wind_speed_10m_max;
        this.wind_direction_10m_dominant = wind_direction_10m_dominant;
    }

    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }

    public List<Double> getTemperature_2m_max() {
        return temperature_2m_max;
    }

    public void setTemperature_2m_max(List<Double> temperature_2m_max) {
        this.temperature_2m_max = temperature_2m_max;
    }

    public List<Double> getTemperature_2m_min() {
        return temperature_2m_min;
    }

    public void setTemperature_2m_min(List<Double> temperature_2m_min) {
        this.temperature_2m_min = temperature_2m_min;
    }

    public List<Double> getApparent_temperature_max() {
        return apparent_temperature_max;
    }

    public void setApparent_temperature_max(List<Double> apparent_temperature_max) {
        this.apparent_temperature_max = apparent_temperature_max;
    }

    public List<Double> getApparent_temperature_min() {
        return apparent_temperature_min;
    }

    public void setApparent_temperature_min(List<Double> apparent_temperature_min) {
        this.apparent_temperature_min = apparent_temperature_min;
    }

    public List<String> getSunrise() {
        return sunrise;
    }

    public void setSunrise(List<String> sunrise) {
        this.sunrise = sunrise;
    }

    public List<String> getSunset() {
        return sunset;
    }

    public void setSunset(List<String> sunset) {
        this.sunset = sunset;
    }

    public List<Double> getDaylight_duration() {
        return daylight_duration;
    }

    public void setDaylight_duration(List<Double> daylight_duration) {
        this.daylight_duration = daylight_duration;
    }

    public List<Double> getSunshine_duration() {
        return sunshine_duration;
    }

    public void setSunshine_duration(List<Double> sunshine_duration) {
        this.sunshine_duration = sunshine_duration;
    }

    public List<Double> getUv_index_max() {
        return uv_index_max;
    }

    public void setUv_index_max(List<Double> uv_index_max) {
        this.uv_index_max = uv_index_max;
    }

    public List<Double> getUv_index_clear_sky_max() {
        return uv_index_clear_sky_max;
    }

    public void setUv_index_clear_sky_max(List<Double> uv_index_clear_sky_max) {
        this.uv_index_clear_sky_max = uv_index_clear_sky_max;
    }

    public List<Double> getPrecipitation_sum() {
        return precipitation_sum;
    }

    public void setPrecipitation_sum(List<Double> precipitation_sum) {
        this.precipitation_sum = precipitation_sum;
    }

    public List<Double> getRain_sum() {
        return rain_sum;
    }

    public void setRain_sum(List<Double> rain_sum) {
        this.rain_sum = rain_sum;
    }

    public List<Integer> getPrecipitation_hours() {
        return precipitation_hours;
    }

    public void setPrecipitation_hours(List<Integer> precipitation_hours) {
        this.precipitation_hours = precipitation_hours;
    }

    public List<Integer> getPrecipitation_probability_max() {
        return precipitation_probability_max;
    }

    public void setPrecipitation_probability_max(List<Integer> precipitation_probability_max) {
        this.precipitation_probability_max = precipitation_probability_max;
    }

    public List<Double> getWind_speed_10m_max() {
        return wind_speed_10m_max;
    }

    public void setWind_speed_10m_max(List<Double> wind_speed_10m_max) {
        this.wind_speed_10m_max = wind_speed_10m_max;
    }

    public List<Integer> getWind_direction_10m_dominant() {
        return wind_direction_10m_dominant;
    }

    public void setWind_direction_10m_dominant(List<Integer> wind_direction_10m_dominant) {
        this.wind_direction_10m_dominant = wind_direction_10m_dominant;
    }
    
    
}
