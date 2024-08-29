package com.application.main;

import com.application.models.Setting;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class SettingManager {
    private static final String FILE_NAME = "setting.json";
    public static Setting data; 
    
    public static final void load() {
        try {
            data = App.gson.fromJson(new FileReader(FILE_NAME), Setting.class);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            
        }
    }
}
