package com.application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WeatherNodeComponent1A {
    //
    private final int _temperature;
    private final int _humidity;
    private final int _rainPercent;
    private final String _time;
    private final String _weatherStatus;

    public WeatherNodeComponent1A(int _temperature, int _humidity, int _rainPercent, String _time, String _weatherStatus) {
        this._temperature = _temperature;
        this._humidity = _humidity;
        this._rainPercent = _rainPercent;
        this._time = _time;
        this._weatherStatus = _weatherStatus;
    }

    //
    @FXML
    private Label humidity;

    @FXML
    private ImageView icon;

    @FXML
    private Label rain;

    @FXML
    private Label temperature;      

    @FXML
    private Label time;

 
    public void initialize() {
        time.setText(_time);
        temperature.setText(Integer.toString(_temperature));
        humidity.setText(Integer.toString(_humidity));
        rain.setText(Integer.toString(_rainPercent));
        icon.setImage(new Image(getClass().getResource(_weatherStatus).toString()));
    }
}