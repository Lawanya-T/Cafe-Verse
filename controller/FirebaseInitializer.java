package com.cafeverse.controller;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.io.FileInputStream;
import java.io.InputStream;

public class FirebaseInitializer {
    public static Firestore initialize() {
        try {
            // Try loading from resources first
            InputStream serviceAccount = FirebaseInitializer.class.getClassLoader()
                .getResourceAsStream("cafeverse-1b93e-firebase-adminsdk-fbsvc-8afa10c9fb.json");
            
            if (serviceAccount == null) {
                // Fallback to file system if not found in resources
                serviceAccount = new FileInputStream(
                    "src/main/resources/Assets/cafeverse-1b93e-firebase-adminsdk-fbsvc-8afa10c9fb.json"
                );
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setProjectId("cafeverse-1b93e")
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                System.out.println("Initialized Successfully");
                System.out.println(FirestoreClient.getFirestore());
            }


            return FirestoreClient.getFirestore();

        } catch (Exception e) {
            e.printStackTrace();
            // Show an alert to the user
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Initialization Error");
                alert.setHeaderText("Failed to connect to database");
                alert.setContentText("Could not initialize Firebase: " + e.getMessage());
                alert.showAndWait();
            });
        }
        return null;
    }
}