package com.application.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CssManager {
    private static final List<String> fontFamilyList = new ArrayList<>();
    private static final List<String> fontSizeList = new ArrayList<>();

    public static List<String> loadFontFamilyFromCss() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(ResourceLoader.getResourceAsStream("/css/fontFamily.css")));

        if(!fontFamilyList.isEmpty()) {
            fontFamilyList.clear();
        }
        
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                if (line.contains("-fx-font-family:")) {
                    fontFamilyList.add(line.substring(line.indexOf(":") + 3, line.indexOf(";") - 1).trim());
                }
            }
        } catch (IOException ex) {
            System.out.println("load font family file error");
        }
       
        return fontFamilyList;
    }

    public static List<String> loadFontSizeFromCss() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(ResourceLoader.getResourceAsStream("/css/fontSize.css")));

        if(!fontSizeList.isEmpty()) {
            fontSizeList.clear();
        }
        
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                if (line.contains("-fx-font-size:")) {
                    fontSizeList.add(line.substring(line.indexOf(":") + 1, line.indexOf("pt")).trim());
                }
            }
        } catch (IOException ex) {
            System.out.println("load font size file error");
        }

        return fontSizeList;
    }

    public static List<String> getFontSizeList() {
        if(fontSizeList.isEmpty()) {
            loadFontSizeFromCss();
        }
        return fontSizeList;
    }

    public static List<String> getFontFamilyList() {
        if(fontFamilyList.isEmpty()) {
            loadFontFamilyFromCss();
        }
        return fontFamilyList;
    }
}
