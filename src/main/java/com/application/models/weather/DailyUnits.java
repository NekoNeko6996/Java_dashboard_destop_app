package com.application.models.weather;

public class DailyUnits {

    private String time;
    private String temperature_2m_max;
    private String temperature_2m_min;
    private String apparent_temperature_max;
    private String apparent_temperature_min;
    private String sunrise;
    private String sunset;
    private String daylight_duration;
    private String sunshine_duration;
    private String uv_index_max;
    private String uv_index_clear_sky_max;
    private String precipitation_sum;
    private String rain_sum;
    private String precipitation_hours;
    private String precipitation_probability_max;
    private String wind_speed_10m_max;
    private String wind_direction_10m_dominant;

    public DailyUnits(String time, String temperature_2m_max, String temperature_2m_min, String apparent_temperature_max, String apparent_temperature_min, String sunrise, String sunset, String daylight_duration, String sunshine_duration, String uv_index_max, String uv_index_clear_sky_max, String precipitation_sum, String rain_sum, String precipitation_hours, String precipitation_probability_max, String wind_speed_10m_max, String wind_direction_10m_dominant) {
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemperature_2m_max() {
        return temperature_2m_max;
    }

    public void setTemperature_2m_max(String temperature_2m_max) {
        this.temperature_2m_max = temperature_2m_max;
    }

    public String getTemperature_2m_min() {
        return temperature_2m_min;
    }

    public void setTemperature_2m_min(String temperature_2m_min) {
        this.temperature_2m_min = temperature_2m_min;
    }

    public String getApparent_temperature_max() {
        return apparent_temperature_max;
    }

    public void setApparent_temperature_max(String apparent_temperature_max) {
        this.apparent_temperature_max = apparent_temperature_max;
    }

    public String getApparent_temperature_min() {
        return apparent_temperature_min;
    }

    public void setApparent_temperature_min(String apparent_temperature_min) {
        this.apparent_temperature_min = apparent_temperature_min;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getDaylight_duration() {
        return daylight_duration;
    }

    public void setDaylight_duration(String daylight_duration) {
        this.daylight_duration = daylight_duration;
    }

    public String getSunshine_duration() {
        return sunshine_duration;
    }

    public void setSunshine_duration(String sunshine_duration) {
        this.sunshine_duration = sunshine_duration;
    }

    public String getUv_index_max() {
        return uv_index_max;
    }

    public void setUv_index_max(String uv_index_max) {
        this.uv_index_max = uv_index_max;
    }

    public String getUv_index_clear_sky_max() {
        return uv_index_clear_sky_max;
    }

    public void setUv_index_clear_sky_max(String uv_index_clear_sky_max) {
        this.uv_index_clear_sky_max = uv_index_clear_sky_max;
    }

    public String getPrecipitation_sum() {
        return precipitation_sum;
    }

    public void setPrecipitation_sum(String precipitation_sum) {
        this.precipitation_sum = precipitation_sum;
    }

    public String getRain_sum() {
        return rain_sum;
    }

    public void setRain_sum(String rain_sum) {
        this.rain_sum = rain_sum;
    }

    public String getPrecipitation_hours() {
        return precipitation_hours;
    }

    public void setPrecipitation_hours(String precipitation_hours) {
        this.precipitation_hours = precipitation_hours;
    }

    public String getPrecipitation_probability_max() {
        return precipitation_probability_max;
    }

    public void setPrecipitation_probability_max(String precipitation_probability_max) {
        this.precipitation_probability_max = precipitation_probability_max;
    }

    public String getWind_speed_10m_max() {
        return wind_speed_10m_max;
    }

    public void setWind_speed_10m_max(String wind_speed_10m_max) {
        this.wind_speed_10m_max = wind_speed_10m_max;
    }

    public String getWind_direction_10m_dominant() {
        return wind_direction_10m_dominant;
    }

    public void setWind_direction_10m_dominant(String wind_direction_10m_dominant) {
        this.wind_direction_10m_dominant = wind_direction_10m_dominant;
    }
    
    
}
