package com.application.controllers;

import com.application.main.App;
import com.application.main.Http;
import com.application.main.LoginManager;
import com.application.main.SettingManager;
import com.application.models.HttpResponse;
import com.application.models.LoginForm;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
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
    private PasswordField passwordTextPass;

    @FXML
    void onClickForgotPass(ActionEvent event) {

    }

    @FXML
    void onClickLogin(ActionEvent event) {
        LoginForm loginData = new LoginForm(email.getText(), password.getText());
        Http.post("/login", App.gson.toJson(loginData), null, (String response) -> {
            HttpResponse response_ = App.gson.fromJson(response, HttpResponse.class);

            if (response_.getStatus().equals("success") && response_.getMessage().equals("Login Successful") && !response_.getToken().isEmpty()) {
                LoginManager.setToken(response_.getToken());

                if (keepLogin.isSelected()) {
                    SettingManager.data.setToken(response_.getToken());
                    SettingManager.data.setKeep_login(true);
                    SettingManager.save();
                }

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
        if (!password.isVisible()) {
            password.setManaged(true);
            password.setVisible(true);
            passwordTextPass.setManaged(false);
            passwordTextPass.setVisible(false);
        }
        else {
            password.setManaged(false);
            password.setVisible(false);
            passwordTextPass.setManaged(true);
            passwordTextPass.setVisible(true);
        }
    }

    public void initialize() {
        // link text field, password field
        Bindings.bindBidirectional(password.textProperty(), passwordTextPass.textProperty());

    }
}
