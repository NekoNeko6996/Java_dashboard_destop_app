package com.application.controllers;

import com.application.main.App;
import com.application.main.Http;
import com.application.main.WeatherManager;
import com.application.models.weather.Weather;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

public class PrimaryController {

    @FXML
    private ScrollPane homeScrollPaneContainer;

    // weather 
    @FXML
    private FlowPane weatherHomeFlowPane;

    @FXML
    private Label weatherHomeTomorrowApparentTemperature;

    @FXML
    private ImageView weatherHomeTomorrowIcon;

    @FXML
    private Label weatherHomeTomorrowRain;

    @FXML
    private Label weatherHomeTomorrowTemperature;

    @FXML
    private Label weatherHomeTomorrowUV;

    @FXML
    private Label weatherHomeTomorrowWind;

    //
    @FXML
    void onClickWeatherHomeMoreInfo(ActionEvent event) {

    }

    //
    public void initialize() {
        WeatherManager.load(10.15, 105.1833, (Weather data) -> {
            System.out.println("da" + data.getDaily().getWind_direction_10m_dominant().get(1));
            setValueToWeatherTomorrow(data);
        });

    }

    private void setValueToWeatherTomorrow(Weather data) {
        // temperature
        weatherHomeTomorrowTemperature.setText(Integer.toString((int) data.getDaily().getTemperature_2m_max().get(1).doubleValue()));

        // apparent temperature
        weatherHomeTomorrowApparentTemperature.setText(Integer.toString((int) data.getDaily().getApparent_temperature_max().get(1).doubleValue()));

        // rain percent
        weatherHomeTomorrowRain.setText(Integer.toString(data.getDaily().getPrecipitation_probability_max().get(1)));

        // UV
        weatherHomeTomorrowUV.setText(Double.toString(data.getDaily().getUv_index_max().get(1)));

        // wind speed
        weatherHomeTomorrowWind.setText(Double.toString(data.getDaily().getWind_speed_10m_max().get(1)));
    }

    
    private void loadWeatherNode(Weather data) {
        
    }
}
