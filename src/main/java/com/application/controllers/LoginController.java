package com.application.controllers;


import com.application.main.App;
import com.application.main.Http;
import com.application.main.LoginManager;
import com.application.models.HttpResponse;
import com.application.models.LoginForm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField email;

    @FXML
    private RadioButton keepLogin;

    @FXML
    private TextField password;

    @FXML
    void onClickForgotPass(ActionEvent event) {
        
    }

    @FXML
    void onClickLogin(ActionEvent event) {
        LoginForm loginData = new LoginForm(email.getText(), password.getText());
        Http.post("/login", App.gson.toJson(loginData), null, (String response) -> {
            HttpResponse response_ = App.gson.fromJson(response, HttpResponse.class);
            
            if(response_.getStatus().equals("success") && response_.getMessage().equals("Login Successful") && !response_.getToken().isEmpty()) {
                LoginManager.setToken(response_.getToken());
                
                App.newStage("primary");
                App.closeStage("login");
            }
        });
    }

    @FXML
    void onClickSignUp(ActionEvent event) {
        
    }

    @FXML
    void onClickViewPass(ActionEvent event) {
        
    }

    public void initialize() {
        
    }
}
