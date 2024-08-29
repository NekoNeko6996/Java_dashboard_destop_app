package com.application.controllers;


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
