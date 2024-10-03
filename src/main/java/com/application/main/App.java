package com.application.main;

import com.application.interfaces.SetEventBus;
import com.application.models.ScreenInfo;
import com.google.common.eventbus.EventBus;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;

public class App extends Application {

    // public
    @SuppressWarnings("exports")
    public static Gson gson = new Gson();
    @SuppressWarnings("exports")
    public static Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().create();
    public static final String DEFAULT_TITLE = "App";

    // private
    private static final Image appIcon;
    private static final Map<String, ScreenInfo> SCREEN_INFO = new HashMap<>();
    private static final EventBus eventBus = new EventBus();

    // static
    static {
        // Load the icon in the static block to ensure it's initialized correctly
        appIcon = new Image(App.class.getResourceAsStream("/logos/app_icon.png"));
    }

    private static final Map<String, Stage> stages = new HashMap<>();

    @Override
    public void start(Stage stage) {
        // screen initialize
        SCREEN_INFO.put("login", new ScreenInfo(1000, 650));
        SCREEN_INFO.put("primary", new ScreenInfo(1400, 800));
        SCREEN_INFO.put("weatherNodeComponent1A", new ScreenInfo(150, 140));
        SCREEN_INFO.put("createNewNote", new ScreenInfo(630, 700));
        SCREEN_INFO.put("noteHomeComponent", new ScreenInfo(350, 300));

        // start here ...
        SettingManager.load();
        GlobalVariable.init();
        LoginManager.login();
    }

    // open new scene
    public static void newStage(String fxml) {
        newStage(fxml, DEFAULT_TITLE);
    }

    public static void newStage(String fxml, String title) {
        if (stages.get(fxml) == null) {
            try {
                ScreenInfo screen = SCREEN_INFO.get(fxml);
                if (screen == null) {
                    System.out.println("Screen not found for FXML: " + fxml);
                    return;
                }

                Parent root = FXMLLoader.load(App.class.getResource(fxml + ".fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root, screen.getWidth(), screen.getHeight()));
                stage.setTitle(title);
                stage.getIcons().add(appIcon);
                stage.setResizable(false);
                stage.show();
                stages.put(fxml, stage);
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("[App][" + fxml + "][ERROR]" + ex.getMessage());
                // ErrorLog.writeLog(ex, "[OPEN NEW SCENE][" + nameStage + "][ERROR]");
                alertMessage(Alert.AlertType.ERROR, "Open Scene Error!", ex.getMessage()).showAndWait();
            }
        }
    }

    public static <T extends SetEventBus> void newStageEvent(String fxml, Class<T> Controller) {
        newStageEvent(fxml, DEFAULT_TITLE, Controller);
    }

    public static <T extends SetEventBus> void newStageEvent(String fxml, String title, Class<T> Controller) {
        if (stages.get(fxml) == null) {
            try {
                ScreenInfo screen = SCREEN_INFO.get(fxml);
                if (screen == null) {
                    System.out.println("Screen not found for FXML: " + fxml);
                    return;
                }

                FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
                Parent root = loader.load();
                T controller = loader.getController();
                controller.setEventBus(eventBus);

                Stage stage = new Stage();
                stage.setScene(new Scene(root, screen.getWidth(), screen.getHeight()));
                stage.setTitle(title);
                stage.getIcons().add(appIcon);
                stage.setResizable(false);
                stage.show();
                stages.put(fxml, stage);
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("[App][" + fxml + "][ERROR]" + ex.getMessage());
                // ErrorLog.writeLog(ex, "[OPEN NEW SCENE][" + nameStage + "][ERROR]");
                alertMessage(Alert.AlertType.ERROR, "Open Scene Error!", ex.getMessage()).showAndWait();
            }
        }
    }

    // get stage
    public static Stage getStage(String nameStage) {
        return stages.get(nameStage);
    }

    public static boolean issetStage(String nameStage) {
        return stages.get(nameStage) != null;
    }

    // close stage
    public static void closeStage(String nameStage) {
        Stage stage = stages.get(nameStage);
        if (stage != null) {
            stage.close();
            stages.remove(nameStage);
        }
    }

    // close all stage
    public static void closeAllStages() {
        for (Stage stage : stages.values()) {
            stage.close();
        }
        stages.clear();
    }

    // load fxml
    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static FXMLLoader loadFXMLToLoader(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader;
    }

    public static Alert alertMessage(Alert.AlertType alertType, String headerContent, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(DEFAULT_TITLE);
        alert.setHeaderText(headerContent);
        alert.setContentText(content);
        return alert;
    }

    public static void main(String[] args) {
        launch();
    }

}
