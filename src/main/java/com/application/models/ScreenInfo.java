package com.application.models;


public class ScreenInfo {
    private final int width;
    private final int height;

    public ScreenInfo(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
