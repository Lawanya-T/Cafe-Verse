package com.cafeverse.view;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DecorationPage extends Application {

    private final ImageView previewImage = new ImageView();

    @Override
    public void start(Stage stage) {
        Accordion accordion = new Accordion();
        previewImage.setFitWidth(220);
        previewImage.setFitHeight(220);
        previewImage.setPreserveRatio(true);

        // Balloon Category
        TitledPane balloonPane = new TitledPane("🎈 Balloons", createDecorGrid(new String[]{
            "balloon-blue.png", "balloon-red.png", "balloon-gold.png"
        }));

        // Flowers Category
        TitledPane flowerPane = new TitledPane("🌸 Flowers", createDecorGrid(new String[]{
            "flower-lily.png", "flower-rose.png", "garland.png"
        }));

        accordion.getPanes().addAll(balloonPane, flowerPane);
        accordion.setExpandedPane(balloonPane);

        VBox previewBox = new VBox(10, new Label("Preview"), previewImage);
        previewBox.setPadding(new Insets(20));
        previewBox.setStyle("-fx-background-color: #fcf2e8; -fx-border-color: #ddd;");

        HBox layout = new HBox(20, accordion, previewBox);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #fff8f4;");

        Scene scene = new Scene(layout, 640, 380);
        stage.setTitle("Café Decoration Chooser ☕");
        stage.setScene(scene);
        stage.show();
    }

    private TilePane createDecorGrid(String[] imageFiles) {
        TilePane tilePane = new TilePane();
        tilePane.setHgap(10);
        tilePane.setVgap(10);
        tilePane.setPadding(new Insets(10));

        for (String file : imageFiles) {
            Image image = new Image("file:images/" + file);
            ImageView icon = new ImageView(image);
            icon.setFitWidth(80);
            icon.setFitHeight(80);
            icon.setPreserveRatio(true);
            icon.setStyle("-fx-cursor: hand;");
            icon.setOnMouseClicked(e -> updatePreview(image));
            tilePane.getChildren().add(icon);
        }

        return tilePane;
    }

    private void updatePreview(Image image) {
        previewImage.setImage(image);
        FadeTransition ft = new FadeTransition(Duration.millis(400), previewImage);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}