package com.application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PowerOutageScheduleComponentController {

  // default
  private final String _date;
  private final String _startTime;
  private final String _endTime;
  private final String _reason;
  private final String _address;

  public PowerOutageScheduleComponentController(String date, String startTime, String endTime, String reason,
      String address) {
    this._date = date;
    this._startTime = startTime;
    this._endTime = endTime;
    this._reason = reason;
    this._address = address;
  }

  // FXML
  @FXML
  private Label date;
  @FXML
  private Label startTime;
  @FXML
  private Label endTime;
  @FXML
  private Label reason;
  @FXML
  private Label address;

  public void initialize() {
    date.setText(_date);
    startTime.setText(_startTime);
    endTime.setText(_endTime);
    reason.setText(_reason);
    address.setText(_address);
  }
}
