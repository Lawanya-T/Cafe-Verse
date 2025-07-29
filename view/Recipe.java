package com.cafeverse.view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Recipe extends Application {

    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    double screenWidth = screenBounds.getWidth();
    double screenHeight = screenBounds.getHeight();

    private final String API_KEY = "AIzaSyDsA2GaOystKssusklWOEOGPndcS0PU_8I"; // Replace with your actual Gemini API key

    @Override
    public void start(Stage primaryStage) {
        // ---------------- HEADER (Title + Back) ----------------
        HBox headerBar = new HBox();
        headerBar.setPadding(new Insets(20, 40, 20, 40));
        headerBar.setAlignment(Pos.CENTER_RIGHT);
        headerBar.setStyle("-fx-background-color: transparent;");

        Label title = new Label("🍽 Recipe Generator");
        title.setFont(new Font("Arial", 36));
        title.setTextFill(Color.WHITE);
        title.setStyle("-fx-font-weight: bold;");
        HBox.setHgrow(title, Priority.ALWAYS);
        title.setMaxWidth(Double.MAX_VALUE);
        title.setAlignment(Pos.CENTER);

        Button backButton = new Button("← Back");
        backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white; " +
                "-fx-font-size: 16px; -fx-underline: true; -fx-font-weight:bold;");
        backButton.setOnAction(e -> new AdminDashboard().show(primaryStage));

        headerBar.getChildren().addAll(title, backButton);

        // ---------------- FOOD / MOOD LEFT PANEL ----------------
        VBox leftPane = new VBox(20);
        leftPane.setPadding(new Insets(40, 60, 40, 40));
        leftPane.setAlignment(Pos.TOP_CENTER);
        leftPane.setPrefWidth(400);
        leftPane.setStyle("-fx-background-color: linear-gradient(to bottom, #98563aff, #af9267ff);" +
                "-fx-background-radius: 0;");

        Label title1 = new Label("A Recipe has no Soul");
        title1.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        title1.setTextFill(Color.WHITE);

        Label subtitle = new Label("WE");
        subtitle.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        subtitle.setTextFill(Color.WHITE);

        Label title2 = new Label("Bring Soul to the Recipe");
        title2.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        title2.setTextFill(Color.WHITE);

        ImageView imageView;
        try {
            Image image = new Image("assets\\Images\\Recipe.png");
            imageView = new ImageView(image);
            imageView.setFitWidth(250);
            imageView.setPreserveRatio(true);
        } catch (Exception e) {
            imageView = new ImageView();
        }

        leftPane.getChildren().addAll(title1, subtitle, title2, imageView);

        // ---------------- INPUT SECTION (Below Title) ----------------
        HBox inputBar = new HBox(20);
        inputBar.setPadding(new Insets(20, 40, 20, 40));
        inputBar.setAlignment(Pos.CENTER);
        inputBar.setStyle("-fx-background-color: rgba(255,255,255,0.15); -fx-background-radius: 20;");

        TextField ingredientInput = new TextField();
        ingredientInput.setPromptText("Enter ingredients (e.g., tomato, egg)");
        ingredientInput.setPrefWidth(350);
        ingredientInput.setStyle("-fx-background-radius: 10; -fx-padding: 10;");

        ComboBox<String> dietType = new ComboBox<>();
        dietType.getItems().addAll("Any", "Vegetarian", "Vegan", "Keto", "High Protein");
        dietType.setValue("Any");
        dietType.setPrefWidth(180);
        dietType.setStyle("-fx-background-radius: 10;");

        Button suggestButton = new Button("🔍 Suggest Recipes");
        suggestButton.setStyle("-fx-background-color: #ff6600; -fx-text-fill: white; " +
                "-fx-font-weight: bold; -fx-font-size:16px; -fx-background-radius: 15;");
        suggestButton.setPrefSize(180, 40);

        inputBar.getChildren().addAll(ingredientInput, dietType, suggestButton);

        // ---------------- RIGHT PANEL (Output) ----------------
        VBox rightPanel = new VBox(15);
        rightPanel.setAlignment(Pos.TOP_CENTER);
        rightPanel.setPadding(new Insets(30));
        rightPanel.setBackground(new Background(new BackgroundFill(
                Color.rgb(255, 255, 255, 0.1), new CornerRadii(20), Insets.EMPTY)));
        rightPanel.setStyle("-fx-background-radius: 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 15, 0, 0, 8);");

        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);
        resultArea.setWrapText(true);
        resultArea.setPrefHeight(500);
        resultArea.setPrefWidth(700);
        resultArea.setStyle("-fx-background-radius: 10; -fx-opacity: 0.95; -fx-font-size:16px;");

        rightPanel.getChildren().add(resultArea);

        // ---------------- MAIN LAYOUT ----------------
        BorderPane layout = new BorderPane();
        layout.setTop(new VBox(headerBar, inputBar)); // Title + Input section
        layout.setLeft(leftPane); // Branding
        layout.setCenter(rightPanel); // Output
        layout.setStyle("-fx-background-color: linear-gradient(to bottom right, #a55b3b, #fcebd5);");

        Scene scene = new Scene(layout, screenWidth, screenHeight);
        primaryStage.setScene(scene);
        primaryStage.setTitle("SmartChef - AI Recipe Suggester");
        primaryStage.show();

        // ---------------- ACTION (Button Logic) ----------------
        suggestButton.setOnAction(e -> {
            String ingredients = ingredientInput.getText().trim();
            String diet = dietType.getValue();
            if (ingredients.isEmpty()) {
                resultArea.setText("⚠ Please enter ingredients to get suggestions.");
                return;
            }
            resultArea.setText("⏳ Thinking...");
            new Thread(() -> {
                String aiResponse = callGeminiAI(ingredients, diet);
                Platform.runLater(() -> resultArea.setText(aiResponse));
            }).start();
        });
    }

    private String callGeminiAI(String ingredients, String dietType) {
        try {
            String endpoint = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + API_KEY;
            String prompt = "Suggest 5 healthy recipes using: " + ingredients + " (Diet: " + dietType + ")";
            String jsonInput = "{ \"contents\": [{\"parts\": [{\"text\": \"" + prompt + "\"}]}] }";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endpoint))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonInput))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String body = response.body();
                int start = body.indexOf("\"text\": \"");
                if (start == -1) return "⚠ Unexpected response format.";
                start += 9;
                int end = body.indexOf("\"", start);
                String result = body.substring(start, end);
                return result.replace("\\n", "\n").replace("\\\"", "\"");
            } else {
                return "❌ API Error: " + response.statusCode();
            }
        } catch (Exception e) {
            return "❌ Error: " + e.getMessage();
        }
    }
}
