package com.application.models;


public class Setting {
    private String app_version;
    private String theme;
    private String base_api;
    private boolean keep_login;
    private String token;

    public Setting(String app_version, String theme, String base_api, boolean keep_login, String token) {
        this.app_version = app_version;
        this.theme = theme;
        this.base_api = base_api;
        this.keep_login = keep_login;
        this.token = token;
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
}
