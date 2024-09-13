package com.application.main;

import com.application.models.User;
import com.google.gson.JsonParseException;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.Alert;

public class LoginManager {

    public static Map<String, String> headers = new HashMap<>();

    public static void login() {
        if (SettingManager.data.isKeep_login() && !SettingManager.data.getToken().isEmpty()) {
            
            //
            setToken(SettingManager.data.getToken());
            Http.post("/checkToken", "", headers, (String response) -> {
                System.out.println(response);
                try {
                    User user = App.gson.fromJson(response, User.class);
                    if (user.getStatus().equals("success") && user.getMessage().equals("Token Verified")) {
                        UserManager.setUser(user);
                        App.newStage("primary");
                    } else {
                        App.newStage("login");
                    }
                } catch (JsonParseException e) {
                    System.out.println("Failed to convert JSON to User object: " + e.getMessage());
                    App.alertMessage(Alert.AlertType.ERROR, "Unexpected error during data conversion", "").showAndWait();
                    App.closeAllStages();
                }
            }
            );
        } else {
            App.newStage("login");
        }
    }
    
    public static void setToken(String token) {
        headers.put("Authorization", "Bearer ".concat(token));
    }
}
