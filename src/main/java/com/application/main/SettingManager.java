package com.application.main;

import com.application.models.Setting;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


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

    public static final void save() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            App.gsonBuilder.toJson(data, writer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
