package com.application.main;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class Http {
    
    public static void post(String path, String jsonData, Map<String, String> headers, Consumer<String> callback) {
    CompletableFuture.supplyAsync(() -> {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(SettingManager.data.getBase_api().concat(path));

            // Set the request headers
            post.setHeader("Content-Type", "application/json");
            post.setHeader("Accept", "application/json");

            // Add additional headers from the map
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    post.setHeader(entry.getKey(), entry.getValue());
                }
            }

            // Set the request body
            StringEntity entity = new StringEntity(jsonData, "UTF-8");
            post.setEntity(entity);

            // Execute the request
            HttpResponse response = httpClient.execute(post);
            HttpEntity responseEntity = response.getEntity();
            return EntityUtils.toString(responseEntity, "UTF-8");
        } catch (IOException e) {
            Platform.runLater(() -> {
                App.alertMessage(Alert.AlertType.ERROR, "Send Http Request Error", e.getMessage()).showAndWait();
            });
            return null;
        }
    }).thenAcceptAsync(responseBody -> {
        if (responseBody != null) {
            callback.accept(responseBody);
        }
    }, Platform::runLater);
}
}
