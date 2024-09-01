package com.application.models.weather;


public class WeatherStatus {
    public static final String SUN = "/icons/sunny.png";
    public static final String CLOUD = "/icons/cloud.png";
    public static final String RAIN = "/icons/rainy.png";
    public static final String SUN_CLOUD = "/icons/sun_cloudy.png";
    
    
    public static String getWeatherStatus(int rainPercent) {
        if(rainPercent > 50) {
            return RAIN;
        }
        else if(rainPercent < 50 && rainPercent > 30) {
            return CLOUD;   
        }
        else {
            return SUN;
        }
    }
}
