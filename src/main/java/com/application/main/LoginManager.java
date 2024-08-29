package com.application.main;

import com.application.models.HttpResponse;
import java.util.HashMap;
import java.util.Map;


public class LoginManager {
    private static String token;

    
    public static void login() {
        if(SettingManager.data.isKeep_login() && !SettingManager.data.getToken().isEmpty()) {
            Map<String, String> headers = new HashMap<>();
            headers.put("Authorization", "Bearer ".concat(SettingManager.data.getToken()));
            
            Http.post("/checkToken", "", headers, (String response) -> {
                HttpResponse response_ = App.gson.fromJson(response, HttpResponse.class);
                if(response_.getStatus().equals("success") && response_.getMessage().equals("Token Verified")) {
                    token = SettingManager.data.getToken();
                    App.newStage("primary");
                }
                else {
                    App.closeStage("login");
                }
            });
        }
        else {
            App.newStage("login");
        }
    }
    
    
    
    public static String getToken() {
        return token;
    }

    public static void setToken(String aToken) {
        token = aToken;
    }
}
