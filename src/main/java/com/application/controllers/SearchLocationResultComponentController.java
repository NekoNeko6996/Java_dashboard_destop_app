package com.application.controllers;

import com.application.events.EventAfterChangeLocationOfWeather;
import com.application.interfaces.SetEventBus;
import com.application.main.App;
import com.application.main.SettingManager;
import com.google.common.eventbus.EventBus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;

public class SearchLocationResultComponentController implements SetEventBus {

  private String locationName_;
  private double latitude_;
  private double longitude_;
  private EventBus eventBus;

  public SearchLocationResultComponentController(String locationName, double latitude, double longitude) {
    this.locationName_ = locationName;
    this.latitude_ = latitude;
    this.longitude_ = longitude;
  }

  @FXML
  private Label geographicCoordinates;

  @FXML
  private Label locationName;

  @FXML
  void onClickUserLocation(ActionEvent event) {
    try {
      SettingManager.data.getLocation().setAddress(locationName_);
      SettingManager.data.getLocation().setLatitude(latitude_);
      SettingManager.data.getLocation().setLongitude(longitude_);

      SettingManager.save();

      eventBus.post(new EventAfterChangeLocationOfWeather(latitude_, longitude_, locationName_));
      App.closeStage("searchLocation");
      ;
    } catch (Exception e) {
      App.alertMessage(AlertType.ERROR, "Error during change location", e.getMessage());
      e.printStackTrace();
    }
  }

  @Override
  public void setEventBus(EventBus eventBus) {
    this.eventBus = eventBus;
  }

  public void initialize() {
    locationName.setText(locationName_);
    geographicCoordinates.setText("[Latitude: " + latitude_ + ", Longitude: " + longitude_ + "]");
  }
}
