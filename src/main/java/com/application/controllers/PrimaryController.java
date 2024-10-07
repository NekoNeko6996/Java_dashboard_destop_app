package com.application.controllers;

import com.application.events.EventAfterChangeLocationOfWeather;
import com.application.events.EventAfterSaveNote;
import com.application.interfaces.SetEventBus;
import com.application.main.App;
import com.application.main.GlobalVariable;
import com.application.main.Http;
import com.application.main.LoginManager;
import com.application.main.PowerOutageScheduleManager;
import com.application.main.SettingManager;
import com.application.main.WeatherManager;
import com.application.models.NoteResponse;
import com.application.models.PowerOutageSchedule;
import com.application.models.PowerOutageScheduleRegion;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private int powerOutageSchedulePageIndex = 1;
    private int powerOutageSchedulePageCount = 1;
    private int powerOutageScheduleMaxItem = 5;

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

    @FXML
    private Label weatherAddressLabel;

    // power outage schedule
    @FXML
    private Button powOutShedPrevBtn;
    @FXML
    private Button powOutShedNextBtn;
    @FXML
    private Button powOutShedPageShowBtn;

    @FXML
    private ComboBox<String> powOutSchedProvinceSelect;
    @FXML
    private ComboBox<String> powOutSchedRegionSelect;
    // power outage schedule table view
    @FXML
    private TableView<PowerOutageSchedule> powOutSchedTableView;
    @FXML
    private TableColumn<PowerOutageSchedule, String> powOutScheStartColumn;
    @FXML
    private TableColumn<PowerOutageSchedule, String> powOutScheStartTimeColumn;
    @FXML
    private TableColumn<PowerOutageSchedule, String> powOutScheEndTimeColumn;
    @FXML
    private TableColumn<PowerOutageSchedule, String> powOutScheAddressColumn;
    @FXML
    private TableColumn<PowerOutageSchedule, String> powOutScheReasonColumn;

    // onclick select province or region
    @FXML
    private void onSelectProvincePowOutSched() {
        //
        String province = powOutSchedProvinceSelect.getValue();
        String provinceCode = "PB20";

        for (Map.Entry<String, String> entry : GlobalVariable.PowOutScheduleProvince.entrySet()) {
            if (entry.getValue().equals(province)) {
                provinceCode = entry.getKey();
            }
        }

        PowerOutageScheduleManager.loadRegion(provinceCode, (List<PowerOutageScheduleRegion> region) -> {
            powOutSchedRegionSelect.getItems().clear();
            for (PowerOutageScheduleRegion regionItem : region) {
                powOutSchedRegionSelect.getItems().add(regionItem.getRegion_name());
            }
            powOutSchedRegionSelect.getSelectionModel().selectFirst();
        });
    }

    @FXML
    private void onSelectRegionPowOutSched() {
    }

    // click change page on power outage schedule
    @FXML
    private void onClickPowOutShedPrevPage() {
        if (powerOutageSchedulePageIndex == 1) {
            return;
        }
        loadPowerOutageSchedule(PowerOutageScheduleManager.getData(), powerOutageSchedulePageIndex -= 1);
        powOutShedPageShowBtn.setText(powerOutageSchedulePageIndex + "/" + powerOutageSchedulePageCount);

    }

    @FXML
    private void onClickPowOutShedNextPage() {
        if (powerOutageSchedulePageIndex == powerOutageSchedulePageCount) {
            return;
        }
        loadPowerOutageSchedule(PowerOutageScheduleManager.getData(), powerOutageSchedulePageIndex += 1);
        powOutShedPageShowBtn.setText(powerOutageSchedulePageIndex + "/" + powerOutageSchedulePageCount);

    }

    // click view more info on weather
    @FXML
    private void onClickWeatherHomeMoreInfo(ActionEvent event) {

    }

    // click change location of weather
    @FXML
    private void onClickChangeLocation() {
        App.newStage("searchLocation");
    }

    // click create new note
    @FXML
    private void onClickCreateNewNote() {
        App.newStageEvent("createNewNote", "New Note", CreateNewNoteController.class);
    }

    // change tab on left nav
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

    // power outage schedule change page
    @FXML
    private void onClickPowerOutageScheduleChangePage(ActionEvent event) {
        System.out.println("onClickPowerOutageScheduleChangePage");
    }

    // event handler
    @Override
    public void setEventBus(EventBus eventBus) {
        eventBus.register(this);
    }

    // event handler of create new note
    @Subscribe
    public void handleAfterSaveNote(EventAfterSaveNote event) {
        loadNoteToHomePane();
    }

    // event handler of change location of weather
    @Subscribe
    public void handleAfterChangeLocation(EventAfterChangeLocationOfWeather event) {
        loadWeatherData();
    }

    //
    public void initialize() {
        loadWeatherData();
        loadUserAvatar("https://i.imgur.com/vji8rWQ.png");
        loadNoteToHomePane();

        // init power outage schedule table view
        powerOutageScheduleTableViewInit();

        // get power outage schedule data
        PowerOutageScheduleManager.load("PB2006", (List<PowerOutageSchedule> powerOutageScheduleList) -> {
            powerOutageSchedulePageCount = (int) Math
                    .ceil((double) powerOutageScheduleList.size() / powerOutageScheduleMaxItem);

            powOutShedPageShowBtn.setText(powerOutageSchedulePageIndex + "/" + powerOutageSchedulePageCount);
            loadPowerOutageSchedule(powerOutageScheduleList);
        });

        // load province to combo box
        for (Map.Entry<String, String> entry : GlobalVariable.PowOutScheduleProvince.entrySet()) {
            powOutSchedProvinceSelect.getItems().add(entry.getValue());
        }
        powOutSchedProvinceSelect.getSelectionModel().select(GlobalVariable.PowOutScheduleProvince.get("PB20"));
        onSelectProvincePowOutSched();

        // after load scene
        Platform.runLater(() -> {
            topNavSearchBarInit();

            //
            appTabList.put("home-button", homeScrollPaneContainer);
            appTabList.put("other-button", otherScrollPaneContainer);
        });
    }

    // power outage schedule table init
    private void powerOutageScheduleTableViewInit() {
        powOutScheStartColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        powOutScheStartTimeColumn.setCellValueFactory(new PropertyValueFactory<>("start_time"));
        powOutScheEndTimeColumn.setCellValueFactory(new PropertyValueFactory<>("end_time"));
        powOutScheAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        powOutScheReasonColumn.setCellValueFactory(new PropertyValueFactory<>("reason"));
    }

    public void setManagedNavTag(ScrollPane open, List<ScrollPane> scrollPaneList) {

    }

    private void loadNoteToHomePane() {
        Http.get("/getNote", LoginManager.headers, (String responseString) -> {
            try {
                NoteResponse response = App.gson.fromJson(responseString, NoteResponse.class);

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

    private void topNavSearchBarInit() {
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

    private void loadUserAvatar(String imgURL) {
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

    // load power outage schedule
    private void loadPowerOutageSchedule(List<PowerOutageSchedule> data) {
        loadPowerOutageSchedule(data, 1);
    }

    private void loadPowerOutageSchedule(List<PowerOutageSchedule> data, int page) {
        if (data.size() == 0) {
            return;
        }

        if (data.size() > 3) {
            int startIndex = (page - 1) * powerOutageScheduleMaxItem;
            int endIndex = Math.min(startIndex + powerOutageScheduleMaxItem, data.size());
            data = data.subList(startIndex, endIndex);
        }

        ObservableList<PowerOutageSchedule> dataList = FXCollections.observableArrayList();

        for (PowerOutageSchedule schedule : data) {
            schedule.setStart(schedule.getStart_time().split(" ")[0]);
            dataList.add(schedule);
        }

        powOutSchedTableView.setItems(dataList);
    }

    private void loadWeatherData() {
        weatherTomorrowSkeleton.setVisible(true);
        weatherTomorrowSkeleton.setManaged(true);

        weatherAddressLabel.setText(SettingManager.data.getLocation().getAddress());

        // load weather
        WeatherManager.load(
                SettingManager.data.getLocation().getLatitude(),
                SettingManager.data.getLocation().getLongitude(),
                (Weather data) -> {
                    loadHomeWeatherNodeTomorrow(data);
                    loadHomeWeatherNode(data);
                });
    }

    private static int findTimeIndex(List<String> timeList, LocalDateTime currentTime) {
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
