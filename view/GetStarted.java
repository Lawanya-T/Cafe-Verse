package com.GetStarted;

import com.Home.profilepage;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DoorImageApp extends Application {
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

    @Override
    public void start(Stage primaryStage) {
        // Load door images
        Image leftDoorImage = new Image(getClass().getResourceAsStream("/assets/images/Cafe (4).png"));
        Image rightDoorImage = new Image(getClass().getResourceAsStream("/assets/images/Verse (3).png"));

        ImageView leftDoor = new ImageView(leftDoorImage);
        ImageView rightDoor = new ImageView(rightDoorImage);

        leftDoor.setFitWidth(800);
        leftDoor.setFitHeight(900);
        rightDoor.setFitWidth(800);
        rightDoor.setFitHeight(900);

        // Wrap doors in panes
        StackPane leftDoorPane = new StackPane(leftDoor);
        StackPane rightDoorPane = new StackPane(rightDoor);

        // Base layout
        HBox doorsBox = new HBox(leftDoorPane, rightDoorPane);
        doorsBox.setAlignment(Pos.CENTER);

        // Create circular "Open" button
        Button openButton = new Button("Open");
        openButton.setStyle(
            "-fx-background-radius: 16px; " +
            "-fx-min-width: 60px; " +
            "-fx-min-height: 60px; " +
            "-fx-max-width: 60px; " +
            "-fx-max-height: 60px; " +
            "-fx-background-color: #e7b04aff; " +
            "-fx-font-size: 14px; " +
            "-fx-font-weight: bold;"
        );

        // Stack all elements
        StackPane root = new StackPane(doorsBox, openButton);
        root.setStyle("-fx-background-color: beige;");
        root.setPrefSize(800, 500);
        StackPane.setAlignment(openButton, Pos.CENTER); // Center the button
        StackPane.setMargin(openButton, new Insets(100, 0, 0, 0));

        Scene scene = new Scene(root, screenWidth, screenHeight);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sliding Doors App");
        primaryStage.show();

        // Button click animation
        openButton.setOnAction(e -> {
            TranslateTransition slideLeft = new TranslateTransition(Duration.seconds(1), leftDoorPane);
            slideLeft.setByX(-350);

            TranslateTransition slideRight = new TranslateTransition(Duration.seconds(1), rightDoorPane);
            slideRight.setByX(350);

            slideLeft.play();
            slideRight.play();

            // Hide the button during animation
            openButton.setVisible(false);

            slideRight.setOnFinished(event -> showGateStartedPage(primaryStage));
        });
    }

    private void showGateStartedPage(Stage stage) {
    // Get the video resource
    java.net.URL resource = getClass().getResource("/assets/videos/GetStarted.mp4");
    if (resource == null) {
        System.err.println("Video file not found. Please check the path.");
        return;
    }

    // Load the video
    Media media = new Media(resource.toExternalForm());
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    MediaView mediaView = new MediaView(mediaPlayer);

    mediaView.setFitWidth(screenWidth);
    mediaView.setFitHeight(screenHeight);
    mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the video
    mediaPlayer.play();

    // Create a button with an image background
    Image buttonImage = new Image(getClass().getResourceAsStream("/assets/images/onClickButton.png")); // Your image path
    ImageView buttonImageView = new ImageView(buttonImage);
    buttonImageView.setFitWidth(200);
    buttonImageView.setFitHeight(200);

    Button bottomRightButton = new Button();
    bottomRightButton.setGraphic(buttonImageView);
    bottomRightButton.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
    bottomRightButton.setOnAction(e -> {
        try {
            new profilepage().start(stage);
        } catch (Exception ex) {
            // TODO: handle exception
            printStackTrace();
        }
        System.out.println("Next button clicked!");
    });

    // Layout
    StackPane startedLayout = new StackPane(mediaView, bottomRightButton);
    StackPane.setAlignment(bottomRightButton, Pos.BOTTOM_RIGHT);
    StackPane.setMargin(bottomRightButton, new Insets(0, 30, 30, 0)); // Padding: top, right, bottom, left

    Scene startedScene = new Scene(startedLayout, screenWidth, screenHeight);
    stage.setScene(startedScene);
}

    private void printStackTrace() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'printStackTrace'");
    }


}
