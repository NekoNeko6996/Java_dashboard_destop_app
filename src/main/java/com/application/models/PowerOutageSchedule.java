package com.application.models;

public class PowerOutageSchedule {
  private String start_date;
  private String end_date;
  private String reason;
  private String address;

  // constructors
  public PowerOutageSchedule() {
  }

  public PowerOutageSchedule(String start_date, String end_date, String reason, String address) {
    this.start_date = start_date;
    this.end_date = end_date;
    this.reason = reason;
    this.address = address;
  }

  // getters and setters
  public String getStart_date() {
    return start_date;
  }

  public void setStart_date(String start_date) {
    this.start_date = start_date;
  }

  public String getEnd_date() {
    return end_date;
  }

  public void setEnd_date(String end_date) {
    this.end_date = end_date;
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
