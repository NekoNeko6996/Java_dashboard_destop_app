package com.application.controllers;

import com.application.main.Http;
import com.application.main.App;

import java.util.HashMap;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class SignUpController {

  @FXML
  private TextField fullName;
  @FXML
  private TextField email;
  @FXML
  private TextField passwordPF;
  @FXML
  private TextField passwordTF;
  @FXML
  private TextField confirmPasswordPF;
  @FXML
  private TextField confirmPasswordTF;
  @FXML
  private TextField verificationCode;
  @FXML
  private RadioButton terms;
  @FXML
  private Button getCodeButton;

  @FXML
  private void onClickGetCode() {
    int maxDelay = 60;

    if (email.getText().isEmpty()) {
      email.setStyle("-fx-border-color: red;");
      return;
    } else {
      email.setStyle("");
    }

    Map<String, String> body = new HashMap<>();
    body.put("email", email.getText());
    String jsonBody = App.gson.toJson(body);

    // Disable the button and start the countdown
    getCodeButton.setDisable(true);
    App.setInterval(maxDelay, 1, idx -> {
      Platform.runLater(() -> {
        getCodeButton.setText((maxDelay - idx) + "s");
      });
    }, max -> {
      Platform.runLater(() -> {
        getCodeButton.setDisable(false);
        getCodeButton.setText("Get Code");
      });
    });

    Http.post("/getVerificationCode", jsonBody, null, (String response) -> {
      System.out.println(response);
    });
  }

  @FXML
  private void onClickSignUp() {
    if (checkInput()) {
      return;
    }

    String hashPassword = BCrypt.hashpw(passwordPF.getText(), BCrypt.gensalt());

    Map<String, String> body = new HashMap<>();
    body.put("fullName", fullName.getText());
    body.put("email", email.getText());
    body.put("hash", hashPassword);
    body.put("code", verificationCode.getText());

    String jsonBody = App.gson.toJson(body);
    Http.post("/signup", jsonBody, null, (String response) -> {
      System.out.println(response);
    });
  }

  @FXML
  private void onClickToLogin() {

  }

  @FXML
  private void onClickViewPass() {
    if (passwordPF.isVisible()) {
      passwordPF.setVisible(false);
      passwordTF.setVisible(true);

      confirmPasswordPF.setVisible(false);
      confirmPasswordTF.setVisible(true);
    } else {
      passwordPF.setVisible(true);
      passwordTF.setVisible(false);

      confirmPasswordPF.setVisible(true);
      confirmPasswordTF.setVisible(false);
    }
  }

  @FXML

  public void initialize() {
    passwordTF.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.equals(passwordPF.getText())) {
        passwordPF.setText(newValue);
      }
    });

    passwordPF.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.equals(passwordTF.getText())) {
        passwordTF.setText(newValue);
      }
    });

    confirmPasswordPF.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.equals(confirmPasswordTF.getText())) {
        confirmPasswordTF.setText(newValue);
      }
    });

    confirmPasswordTF.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.equals(confirmPasswordPF.getText())) {
        confirmPasswordPF.setText(newValue);
      }
    });
  }

  private boolean checkInput() {
    boolean isError = false;

    if (fullName.getText().isEmpty()) {
      fullName.setStyle("-fx-border-color: red;");
      isError = true;
    }
    if (email.getText().isEmpty()) {
      email.setStyle("-fx-border-color: red;");
      isError = true;
    }
    if (passwordPF.getText().isEmpty()) {
      passwordPF.setStyle("-fx-border-color: red;");
      isError = true;
    }
    if (passwordTF.getText().isEmpty()) {
      passwordTF.setStyle("-fx-border-color: red;");
      isError = true;
    }
    if (confirmPasswordPF.getText().isEmpty()) {
      confirmPasswordPF.setStyle("-fx-border-color: red;");
      isError = true;
    }
    if (confirmPasswordTF.getText().isEmpty()) {
      confirmPasswordTF.setStyle("-fx-border-color: red;");
      isError = true;
    }
    if (verificationCode.getText().isEmpty()) {
      verificationCode.setStyle("-fx-border-color: red;");
      isError = true;
    }

    return isError;
  }
}
