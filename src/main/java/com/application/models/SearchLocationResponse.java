package com.application.models;

import java.util.List;

public class SearchLocationResponse {
  private long place_id;
  private String licence;
  private String osm_type;
  private long osm_id;
  private double lat;
  private double lon;
  private String place_class;
  private String type;
  private int place_rank;
  private double importance;
  private String addresstype;
  private String name;
  private String display_name;
  private List<String> boundingbox;

  // Getters và Setters

  public long getPlace_id() {
    return place_id;
  }

  public void setPlace_id(long place_id) {
    this.place_id = place_id;
  }

  public String getLicence() {
    return licence;
  }

  public void setLicence(String licence) {
    this.licence = licence;
  }

  public String getOsm_type() {
    return osm_type;
  }

  public void setOsm_type(String osm_type) {
    this.osm_type = osm_type;
  }

  public long getOsm_id() {
    return osm_id;
  }

  public void setOsm_id(long osm_id) {
    this.osm_id = osm_id;
  }

  public double getLat() {
    return lat;
  }

  public void setLat(double lat) {
    this.lat = lat;
  }

  public double getLon() {
    return lon;
  }

  public void setLon(double lon) {
    this.lon = lon;
  }

  public String getPlace_class() {
    return place_class;
  }

  public void setPlace_class(String place_class) {
    this.place_class = place_class;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getPlace_rank() {
    return place_rank;
  }

  public void setPlace_rank(int place_rank) {
    this.place_rank = place_rank;
  }

  public double getImportance() {
    return importance;
  }

  public void setImportance(double importance) {
    this.importance = importance;
  }

  public String getAddresstype() {
    return addresstype;
  }

  public void setAddresstype(String addresstype) {
    this.addresstype = addresstype;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDisplay_name() {
    return display_name;
  }

  public void setDisplay_name(String display_name) {
    this.display_name = display_name;
  }

  public List<String> getBoundingbox() {
    return boundingbox;
  }

  public void setBoundingbox(List<String> boundingbox) {
    this.boundingbox = boundingbox;
  }
}
