package com.application.controllers;

import com.application.main.App;
import com.application.main.Http;
import com.application.main.SettingManager;
import com.application.models.SearchLocationResponse;
import com.google.common.reflect.TypeToken;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import java.util.List;

public class SearchLocationController {

  @FXML
  private TextField inputLocation;

  @FXML
  private FlowPane resultFlowPane;

  @FXML
  private Pane notFoundPane;

  @FXML
  private void onClickSearch() {
    String location = inputLocation.getText().trim().replace(" ", "+");

    if (location.isEmpty()) {
      // Optionally, show a message to the user
      Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a location.");
      alert.showAndWait();
      return;
    }

    String query = SettingManager.data.getSearch_location_api()
        .concat("?q={address}&format=json&limit={limit}"
            .replace("{address}", location)
            .replace("{limit}", "5"));

    Http.getUrl(query, null, (String response) -> {
      try {
        List<SearchLocationResponse> responses = App.gson.fromJson(response,
            new TypeToken<List<SearchLocationResponse>>() {
            }.getType());

        boolean found = !responses.isEmpty();
        updateVisibility(found);

        if (found) {
          resultFlowPane.getChildren().clear();
          for (SearchLocationResponse searchResponse : responses) {
            FXMLLoader loader = App.loadFXMLToLoader("searchLocationResultComponent");

            SearchLocationResultComponentController controller = new SearchLocationResultComponentController(
                searchResponse.getDisplay_name(),
                searchResponse.getLat(),
                searchResponse.getLon());

            controller.setEventBus(App.getEventBus());
            loader.setController(controller);
            resultFlowPane.getChildren().add(loader.load());
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR, "An error occurred while searching. Please try again.");
        alert.showAndWait();
      }
    });
  }

  private void updateVisibility(boolean found) {
    notFoundPane.setVisible(!found);
    notFoundPane.setManaged(!found);
    resultFlowPane.setVisible(found);
    resultFlowPane.setManaged(found);
  }
}
