package com.application.main;

import com.google.gson.Gson;
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
    public static Gson gson = new Gson();
    public static final String DEFAULT_TITLE = "App";
    
    // private
    private static final Image appIcon;

    // static
    static {
        // Load the icon in the static block to ensure it's initialized correctly
        appIcon = new Image(App.class.getResourceAsStream("/logos/app_icon.png"));
    }

    private static final Map<String, Stage> stages = new HashMap<>();

    @Override
    public void start(Stage stage) {
        // start here ...
        App.newScene("login", 1000, 650, "login");
    }

    // open new scene
    public static void newScene(String fxml, double width, double height, String nameStage) {
        if (stages.get(nameStage) == null) {
            try {
                Stage newStage = new Stage();
                newStage.setScene(new Scene(loadFXML(fxml), width, height));
                newStage.setOnCloseRequest(event -> closeStage(nameStage));
                newStage.setTitle(DEFAULT_TITLE);
                newStage.getIcons().add(appIcon);
                newStage.setResizable(false);
                newStage.show();
                stages.put(nameStage, newStage);
            } catch (IOException ex) {
                System.out.println("[OPEN NEW SCENE][" + nameStage + "][ERROR]" + ex.getMessage());
//                ErrorLog.writeLog(ex, "[OPEN NEW SCENE][" + nameStage + "][ERROR]");
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
