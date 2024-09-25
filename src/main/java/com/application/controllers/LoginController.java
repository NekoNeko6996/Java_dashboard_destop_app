package com.application.controllers;

import com.application.main.App;
import com.application.main.Http;
import com.application.main.LoginManager;
import com.application.main.SettingManager;
import com.application.main.UserManager;
import com.application.models.LoginForm;
import com.application.models.User;
import com.google.gson.JsonParseException;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
            try {
                User user = App.gson.fromJson(response, User.class);

                if (user.getStatus().equals("success") && user.getMessage().equals("Login Successful")
                        && !user.getToken().isEmpty()) {
                    UserManager.setUser(user);
                    LoginManager.setToken(user.getToken());

                    if (keepLogin.isSelected()) {
                        SettingManager.data.setToken(user.getToken());
                        SettingManager.data.setKeep_login(true);
                        SettingManager.save();
                    }

                    // load primary with event
                    App.newStageEvent("primary", PrimaryController.class);
                    App.closeStage("login");
                } else {
                    App.alertMessage(Alert.AlertType.INFORMATION, "Login failed", user.getMessage()).showAndWait();
                }
            } catch (JsonParseException e) {
                System.out.println("Failed to convert JSON to User object: " + e.getMessage());
                App.alertMessage(Alert.AlertType.ERROR, "Unexpected error during data conversion", "").showAndWait();
                App.closeAllStages();
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
        } else {
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
