package com.application.models;

public class PowerOutageScheduleRegion {
  private String region_key;
  private String region_name;

  public PowerOutageScheduleRegion(String region_key, String region_name) {
    this.region_key = region_key;
    this.region_name = region_name;
  }

  public String getRegion_key() {
    return region_key;
  }

  public void setRegion_key(String region_key) {
    this.region_key = region_key;
  }

  public String getRegion_name() {
    return region_name;
  }

  public void setRegion_name(String region_name) {
    this.region_name = region_name;
  }
}
