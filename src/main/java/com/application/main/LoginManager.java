package com.application.main;

import com.application.models.User;
import java.util.HashMap;
import java.util.Map;


public class LoginManager {
    public static Map<String, String> headers = new HashMap<>();
    
    public static void login() {
        if(SettingManager.data.isKeep_login() && !SettingManager.data.getToken().isEmpty()) {
            headers.put("Authorization", "Bearer ".concat(SettingManager.data.getToken()));
            
            Http.post("/checkToken", "", headers, (String response) -> {
                User user = App.gson.fromJson(response, User.class);
                if(user.getStatus().equals("success") && user.getMessage().equals("Token Verified")) {
                    UserManager.setUser(user);
                    App.newStage("primary");
                }
                else {
                    App.newStage("login");
                }
            });
        }
        else {
            App.newStage("login");
        }
    }
}
