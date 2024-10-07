package com.application.models;

import java.util.List;

public class Setting {
    private String app_version;
    private String theme;
    private String base_api;
    private String search_location_api;
    private boolean keep_login;
    private String token;
    private List<NoteColorSet> noteColorSet;
    private Location location;

    public Setting(String app_version, String theme, String base_api, String search_location_api, boolean keep_login,
            String token,
            List<NoteColorSet> noteColorSet, Location location) {
        this.app_version = app_version;
        this.theme = theme;
        this.base_api = base_api;
        this.keep_login = keep_login;
        this.token = token;
        this.noteColorSet = noteColorSet;
        this.location = location;
        this.search_location_api = search_location_api;
    }

    public class Location {
        private String address;
        private double latitude;
        private double longitude;

        // constructor
        public Location(String address, double latitude, double longitude) {
            this.address = address;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        // getter and setter
        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
    }

    public class NoteColorSet {
        private String type;
        private String color;

        public NoteColorSet(String type, String color) {
            this.type = type;
            this.color = color;
        }

        // getter and setter
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }

    public String getApp_version() {
        return app_version;
    }

    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getBase_api() {
        return base_api;
    }

    public void setBase_api(String base_api) {
        this.base_api = base_api;
    }

    public boolean isKeep_login() {
        return keep_login;
    }

    public void setKeep_login(boolean keep_login) {
        this.keep_login = keep_login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<NoteColorSet> getNoteColorSet() {
        return noteColorSet;
    }

    public void setNoteColorSet(List<NoteColorSet> noteColorSet) {
        this.noteColorSet = noteColorSet;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getSearch_location_api() {
        return search_location_api;
    }

    public void setSearch_location_api(String search_location_api) {
        this.search_location_api = search_location_api;
    }
}
