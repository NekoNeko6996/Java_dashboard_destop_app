package com.application.controllers;

import com.application.main.App;
import com.application.main.WeatherManager;
import com.application.models.weather.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class PrimaryController {
    //
    private Map<String, ScrollPane> appTabList = new HashMap<>();
    
    //
    @FXML
    private ScrollPane homeScrollPaneContainer;
    @FXML
    private ScrollPane otherScrollPaneContainer;

    // left nav
    @FXML
    private Pane leftNavBtnListContainer;

    // top nav
    @FXML
    private TextField topNavSearchBar;

    // user avatar
    @FXML
    private Circle userAvatarCircle;
    @FXML
    private Circle userAvatarCircleTopNav;

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

    @FXML
    private ImageView weatherTomorrowSkeleton;

    //
    @FXML
    private void onClickWeatherHomeMoreInfo(ActionEvent event) {

    }
    
    @FXML
    private void onClickCreateNewNote() {
        App.newStage("createNewNote");
    }

    @FXML
    private void onClickChangePage(ActionEvent event) {
        // Reset all buttons to their default styles
        for (Node btn : leftNavBtnListContainer.getChildren()) {
            if (btn instanceof Button) {
                Button b = (Button) btn;
                
                b.getStyleClass().remove("pane-nav-button-selected");
                b.getStyleClass().add("pane-nav-button");
                b.getStyleClass().add( "pane-nav-button-hover");
                appTabList.get(b.getId()).setManaged(false);
                appTabList.get(b.getId()).setVisible(false);
            }
        }

        // Highlight the clicked button
        Button clickedButton = (Button) event.getSource();
        clickedButton.getStyleClass().remove("pane-nav-button");
        clickedButton.getStyleClass().remove("pane-nav-button-hover");
        clickedButton.getStyleClass().setAll("pane-nav-button-selected");
        appTabList.get(clickedButton.getId()).setManaged(true);
        appTabList.get(clickedButton.getId()).setVisible(true);
    }

    public void setManagedNavTag(ScrollPane open, List<ScrollPane> scrollPaneList) {
        
    }

    //
    public void initialize() {
        
        //load weather
        loadWeatherData();

        // load avatar
        loadUserAvatar("https://i.imgur.com/vji8rWQ.png");

        // sau khi load scene xong 
        Platform.runLater(() -> {
            topNavSearchBarInit();
            
            //
            appTabList.put("home-button", homeScrollPaneContainer);
            appTabList.put("other-button", otherScrollPaneContainer);
        });
    }

    public void topNavSearchBarInit() {
        Stage primaryStage = App.getStage("primary");
        if (primaryStage.getScene() != null) {
            primaryStage.getScene().setOnKeyPressed(event -> {
                String keyName = event.getCode().toString();
                if (keyName.equals("SLASH") && !topNavSearchBar.isFocused()) {
                    topNavSearchBar.requestFocus();
                    topNavSearchBar.positionCaret(topNavSearchBar.getText().length());
                }
            });

            primaryStage.requestFocus();
        } else {
            System.out.println("Scene is not set on the primary stage.");
        }
    }

    public void loadUserAvatar(String imgURL) {
        Task<Void> loadImg = new Task<>() {
            @Override
            protected Void call() throws Exception {
                Image avatar = new Image(imgURL);
                if (avatar.isError()) {
                    return null;
                }
                Platform.runLater(() -> {
                    ImagePattern av = new ImagePattern(avatar);
                    userAvatarCircle.setFill(av);
                    userAvatarCircleTopNav.setFill(av);
                });
                return null;
            }
        };

        new Thread(loadImg).start();
    }

    public void loadWeatherData() {
        weatherTomorrowSkeleton.setVisible(true);
        weatherTomorrowSkeleton.setManaged(true);

        // load weather
        WeatherManager.load(10.15, 105.1833, (Weather data) -> {
            loadHomeWeatherNodeTomorrow(data);

            loadHomeWeatherNode(data);
        });
    }

    public static int findTimeIndex(List<String> timeList, LocalDateTime currentTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        for (int i = 0; i < timeList.size(); i++) {
            LocalDateTime time = LocalDateTime.parse(timeList.get(i), formatter);
            if (time.isAfter(currentTime) || time.isEqual(currentTime)) {
                return i - 1 >= 0 ? i - 1 : 0;
            }
        }

        return -1;
    }

    private void loadHomeWeatherNode(Weather data) {
        Hourly weatherHourly = data.getHourly();

        int index = findTimeIndex(weatherHourly.getTime(), LocalDateTime.now());

        weatherHomeFlowPane.getChildren().clear();
        weatherHomeFlowPane.setHgap(20);
        for (int count = index; count < index + 5; count++) {
            String time = LocalTime.now().getHour() + count - index + ":" + "00";
            try {
                FXMLLoader weatherLoader = App.loadFXMLToLoader("weatherNodeComponent1A");
                WeatherNodeComponent1A controller = new WeatherNodeComponent1A(
                        weatherHourly.getTemperature_2m().get(count).intValue(),
                        weatherHourly.getRelative_humidity_2m().get(count),
                        weatherHourly.getPrecipitation_probability().get(count),
                        time,
                        WeatherStatus.getWeatherStatus(weatherHourly.getPrecipitation_probability().get(count))
                );

                weatherLoader.setController(controller);
                weatherHomeFlowPane.getChildren().add(weatherLoader.load());
            } catch (IOException ex) {
                App.alertMessage(Alert.AlertType.ERROR, "Load Weather Node Error", ex.getMessage()).showAndWait();
                return;
            }
        }
    }

    private void loadHomeWeatherNodeTomorrow(Weather data) {
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

        //
        weatherHomeTomorrowIcon.setImage(new Image(getClass().getResource(WeatherStatus.getWeatherStatus(data.getDaily().getPrecipitation_probability_max().get(1))).toString()));

        //
        weatherTomorrowSkeleton.setVisible(false);
        weatherTomorrowSkeleton.setManaged(false);
    }
}
