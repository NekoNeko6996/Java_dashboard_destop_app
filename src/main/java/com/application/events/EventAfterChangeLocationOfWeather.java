package com.application.events;

public class EventAfterChangeLocationOfWeather {
  private double latitude;
  private double longitude;
  private String locationName;

  public EventAfterChangeLocationOfWeather(double latitude, double longitude, String locationName) {
    this.latitude = latitude;
    this.longitude = longitude;
    this.locationName = locationName;
  }

  public double getLatitude() {
    return latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public String getLocationName() {
    return locationName;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public void setLocationName(String locationName) {
    this.locationName = locationName;
  }
}
