package com.cafeverse.view;

import com.cafeverse.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class about extends Application {

    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    double screenWidth = screenBounds.getWidth();
    double screenHeight = screenBounds.getHeight();

    @Override
    public void start(Stage primaryStage) {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #a55b3b, #d7ab70ff);");

        // === Top Logos ===
        HBox topLogos = new HBox();
        topLogos.setPadding(new Insets(5));
        topLogos.setAlignment(Pos.CENTER_LEFT);
        topLogos.setSpacing(20);

        ImageView instituteLogo = new ImageView(new Image("assets/images/logo3l.png"));
        instituteLogo.setFitHeight(100);
        instituteLogo.setPreserveRatio(true);

        ImageView projectLogo = new ImageView(new Image("assets/images/Core2webLogo.png"));
        projectLogo.setFitHeight(100);
        projectLogo.setPreserveRatio(true);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        topLogos.getChildren().addAll(instituteLogo, spacer, projectLogo);
        root.setTop(topLogos);

        // === Teacher Section ===
        ImageView teacherImage = new ImageView(new Image("assets/images/ShashiSir.png"));
        teacherImage.setFitWidth(320);
        teacherImage.setFitHeight(260);
        teacherImage.setStyle("-fx-effect: dropshadow(gaussian, gray, 10, 0.5, 2, 2);");

        Label teacherTitle = new Label("Special Thanks To Shashi Sir");
        teacherTitle.setFont(Font.font("Arial", 24));
        teacherTitle.setStyle("-fx-text-fill: white; -fx-font-weight: bold");

        Label teacherDesc = new Label(
                "Shashi Sir is a respected mentor at Core2Web,\n" +
                "known for simplifying core CS topics.\n" +
                "His approachable teaching style inspires many.");
        teacherDesc.setFont(Font.font("Arial", 18));
        teacherDesc.setStyle("-fx-text-fill: white;");
        teacherDesc.setWrapText(true);

        VBox teacherInfoBox = new VBox(10, teacherTitle, teacherDesc);
        teacherInfoBox.setPadding(new Insets(10));
        teacherInfoBox.setAlignment(Pos.TOP_LEFT);

        HBox teacherSection = new HBox(30, teacherImage, teacherInfoBox);
        teacherSection.setAlignment(Pos.CENTER);

        // === Cards Section with increased fonts ===
        VBox teacherCard = createCard("About Us", "Prof. Shashi Bagal Sir\nProf. Sachin Patil Sir\nProf. Pramod Sir\nProf.Shiv Sir\nProf.Subodh Sir\nMentor: Punam Khedkar", 300);
        VBox projectCard = createCard("About Project", "CafeVerse is a smart cafe ordering system\nbuilt with Firebase, cart features,\nand interactive UI.", 300);
        VBox teamCard = createCard("Team Members", "1. Pranoti Chakawate\n2. Lawanya Thakare\n3. Gauri Agwan\n4. Khushi Vaishnav", 220);

        HBox cardRow = new HBox(30, teacherCard, projectCard, teamCard);
        cardRow.setAlignment(Pos.CENTER);

        // === Back Button ===
        Button backButton = new Button("← Back");
        backButton.setStyle("-fx-background-color: #6c6f72ff; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-background-radius: 6;");
        backButton.setOnAction(e -> {
            try {
                new Home().start(new Stage());
                primaryStage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox centerBox = new VBox(30, teacherSection, cardRow, backButton);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setPadding(new Insets(20));

        root.setCenter(centerBox);

        Scene scene = new Scene(root, screenWidth, screenHeight);
        primaryStage.setTitle("About Us");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createCard(String title, String content, double width) {
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", 20));  // Increased
        titleLabel.setStyle("-fx-text-fill: #333; -fx-font-weight: bold;");

        Label contentLabel = new Label(content);
        contentLabel.setFont(Font.font("Arial", 16));  // Increased
        contentLabel.setStyle("-fx-text-fill: #444;");
        contentLabel.setWrapText(true);

        VBox card = new VBox(10, titleLabel, contentLabel);
        card.setPadding(new Insets(12));
        card.setPrefWidth(width);
        card.setStyle("-fx-background-color: #fbecc9ff; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 6, 0, 2, 2);");
        return card;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Parent createScene(Stage homStage) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createScene'");
    }
}