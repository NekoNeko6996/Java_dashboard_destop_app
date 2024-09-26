package com.application.controllers;

import com.application.events.EventAfterSaveNote;
import com.application.interfaces.SetEventBus;
import com.application.main.App;
import com.application.main.Http;
import com.application.main.LoginManager;
import com.application.main.WeatherManager;
import com.application.models.Note;
import com.application.models.NoteResponse;
import com.application.models.weather.*;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
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

public class PrimaryController implements SetEventBus {

    //
    private final Map<String, ScrollPane> appTabList = new HashMap<>();

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

    @FXML
    private FlowPane noteHomeFlowPane;

    //
    @FXML
    private void onClickWeatherHomeMoreInfo(ActionEvent event) {

    }

    @FXML
    private void onClickCreateNewNote() {
        App.newStageEvent("createNewNote", "New Note", CreateNewNoteController.class);
    }

    @FXML
    private void onClickChangePage(ActionEvent event) {
        // Reset all buttons to their default styles
        for (Node btn : leftNavBtnListContainer.getChildren()) {
            if (btn instanceof Button) {
                Button b = (Button) btn;

                b.getStyleClass().remove("pane-nav-button-selected");
                b.getStyleClass().add("pane-nav-button");
                b.getStyleClass().add("pane-nav-button-hover");
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

    // event handler
    @Override
    public void setEventBus(EventBus eventBus) {
        eventBus.register(this);
    }

    @Subscribe
    public void handleAfterSaveNote(EventAfterSaveNote event) {
        System.out.println("EventAfterSaveNote");
        loadNoteToHomePane();
    }

    //
    public void initialize() {
        loadWeatherData();
        loadUserAvatar("https://i.imgur.com/vji8rWQ.png");
        loadNoteToHomePane();

        // after load scene
        Platform.runLater(() -> {
            topNavSearchBarInit();

            //
            appTabList.put("home-button", homeScrollPaneContainer);
            appTabList.put("other-button", otherScrollPaneContainer);
        });
    }

    public void setManagedNavTag(ScrollPane open, List<ScrollPane> scrollPaneList) {

    }

    public void loadNoteToHomePane() {
        Http.get("/getNote", LoginManager.headers, (String responseString) -> {
            try {
                NoteResponse response = App.gson.fromJson(responseString, NoteResponse.class);

                System.out.println(App.gsonBuilder.toJson(response));

                // Clear old notes from the pane
                noteHomeFlowPane.getChildren().clear();

                for (int count = 0; count < 3; count++) {
                    try {
                        NoteResponse.Note_ note = response.getNotes().get(count);
                        FXMLLoader loader = App.loadFXMLToLoader("noteHomeComponent");
                        Type tagType = new TypeToken<List<String>>() {
                        }.getType();
                        List<String> tag = App.gson.fromJson(note.getTag(), tagType);

                        // Create and set controller with the note data
                        NoteHomeComponentController controller = new NoteHomeComponentController(
                                note.getPriority(),
                                note.getContent(),
                                note.getDay(),
                                note.getIcon(),
                                tag);
                        loader.setController(controller);

                        // Load FXML and add to FlowPane
                        noteHomeFlowPane.getChildren().add(loader.load());

                    } catch (IOException | JsonSyntaxException ex) {
                        ex.printStackTrace();
                        return;
                    }
                }
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
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
                        WeatherStatus.getWeatherStatus(weatherHourly.getPrecipitation_probability().get(count)));

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
        weatherHomeTomorrowTemperature
                .setText(Integer.toString((int) data.getDaily().getTemperature_2m_max().get(1).doubleValue()));

        // apparent temperature
        weatherHomeTomorrowApparentTemperature
                .setText(Integer.toString((int) data.getDaily().getApparent_temperature_max().get(1).doubleValue()));

        // rain percent
        weatherHomeTomorrowRain.setText(Integer.toString(data.getDaily().getPrecipitation_probability_max().get(1)));

        // UV
        weatherHomeTomorrowUV.setText(Double.toString(data.getDaily().getUv_index_max().get(1)));

        // wind speed
        weatherHomeTomorrowWind.setText(Double.toString(data.getDaily().getWind_speed_10m_max().get(1)));

        //
        weatherHomeTomorrowIcon.setImage(new Image(getClass()
                .getResource(WeatherStatus.getWeatherStatus(data.getDaily().getPrecipitation_probability_max().get(1)))
                .toString()));

        //
        weatherTomorrowSkeleton.setVisible(false);
        weatherTomorrowSkeleton.setManaged(false);
    }
}
