package com.application.models;

public class PowerOutageSchedule {
  private String start;
  private String start_time;
  private String end_time;
  private String reason;
  private String address;

  // constructors
  public PowerOutageSchedule() {
  }

  public PowerOutageSchedule(String start, String start_time, String end_time, String reason, String address) {
    this.start = start;
    this.start_time = start_time;
    this.end_time = end_time;
    this.reason = reason;
    this.address = address;
  }

  // getters and setters
  public String getStart() {
    return start;
  }

  public void setStart(String start) {
    this.start = start;
  }

  public String getStart_time() {
    return start_time;
  }

  public void setStart_time(String start_time) {
    this.start_time = start_time;
  }

  public String getEnd_time() {
    return end_time;
  }

  public void setEnd_time(String end_time) {
    this.end_time = end_time;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}
