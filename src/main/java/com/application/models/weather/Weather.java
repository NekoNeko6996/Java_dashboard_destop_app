package com.application.models.weather;


public class Weather {
    private double latitude;
    private double longitude;
    private double generationtime_ms;
    private int utc_offset_seconds;
    private String timezone;
    private String timezone_abbreviation;
    private int elevation;
    private CurrentUnits current_units;
    private Current current;
    private HourlyUnits hourly_units;
    private Hourly hourly;
    private DailyUnits daily_units;
    private Daily daily;

    public Weather(double latitude, double longitude, double generationtime_ms, int utc_offset_seconds, String timezone, String timezone_abbreviation, int elevation, CurrentUnits current_units, Current current, HourlyUnits hourly_units, Hourly hourly, DailyUnits daily_units, Daily daily) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.generationtime_ms = generationtime_ms;
        this.utc_offset_seconds = utc_offset_seconds;
        this.timezone = timezone;
        this.timezone_abbreviation = timezone_abbreviation;
        this.elevation = elevation;
        this.current_units = current_units;
        this.current = current;
        this.hourly_units = hourly_units;
        this.hourly = hourly;
        this.daily_units = daily_units;
        this.daily = daily;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getGenerationtime_ms() {
        return generationtime_ms;
    }

    public void setGenerationtime_ms(double generationtime_ms) {
        this.generationtime_ms = generationtime_ms;
    }

    public int getUtc_offset_seconds() {
        return utc_offset_seconds;
    }

    public void setUtc_offset_seconds(int utc_offset_seconds) {
        this.utc_offset_seconds = utc_offset_seconds;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getTimezone_abbreviation() {
        return timezone_abbreviation;
    }

    public void setTimezone_abbreviation(String timezone_abbreviation) {
        this.timezone_abbreviation = timezone_abbreviation;
    }

    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public CurrentUnits getCurrent_units() {
        return current_units;
    }

    public void setCurrent_units(CurrentUnits current_units) {
        this.current_units = current_units;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public HourlyUnits getHourly_units() {
        return hourly_units;
    }

    public void setHourly_units(HourlyUnits hourly_units) {
        this.hourly_units = hourly_units;
    }

    public Hourly getHourly() {
        return hourly;
    }

    public void setHourly(Hourly hourly) {
        this.hourly = hourly;
    }

    public DailyUnits getDaily_units() {
        return daily_units;
    }

    public void setDaily_units(DailyUnits daily_units) {
        this.daily_units = daily_units;
    }

    public Daily getDaily() {
        return daily;
    }

    public void setDaily(Daily daily) {
        this.daily = daily;
    }

    
}
