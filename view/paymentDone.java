// package com.payment;
package com.cafeverse.view;

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class paymentDone extends Application {

     Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

    @Override
    public void start(Stage primaryStage) {
        Image thumbsUp = new Image("assets\\images\\thumbsup.jpeg");
        ImageView thumbsView = new ImageView(thumbsUp);
        thumbsView.setFitWidth(100);
        thumbsView.setFitHeight(100);

        ScaleTransition bounce = new ScaleTransition(Duration.seconds(0.8), thumbsView);
        bounce.setFromX(1.0);
        bounce.setFromY(1.0);
        bounce.setToX(1.2);
        bounce.setToY(1.2);
        bounce.setAutoReverse(true);
        bounce.setCycleCount(Animation.INDEFINITE);

        RotateTransition rotate = new RotateTransition(Duration.seconds(1.6), thumbsView);
        rotate.setFromAngle(-10);
        rotate.setToAngle(10);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setAutoReverse(true);

        new ParallelTransition(bounce, rotate).play();

        VBox header = new VBox(thumbsView);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(50, 0, 30, 0));
        header.setStyle("-fx-background-color: hsla(118, 61%, 41%, 1.00);");

        Text thankYou = new Text("Thank you!");
        thankYou.setFont(Font.font("Arial", 40));
        thankYou.setFill(Color.web("#2ecc71"));
        thankYou.setOpacity(0);

        Text status = new Text("Payment Done Successfully");
        status.setFont(Font.font("Arial", 22));
        status.setFill(Color.BLACK);
        status.setOpacity(0);

        Text description = new Text(
                "Your order has been placed! Sit back and relax, we’ll notify you once it’s ready.");
        description.setFont(Font.font("Arial", 16));
        description.setFill(Color.GRAY);
        description.setWrappingWidth(500);
        description.setOpacity(0);
        description.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        // Home Button (kept for navigation to HomePage, if you want later)
        Button homeButton = new Button("Take me to my Home Page");
     
            homeButton.setOnAction(e -> {
            try {
                new Home().start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        
        styleMainButton(homeButton);
        homeButton.setOpacity(0);

        // Review Button (Navigates to Review Page)
        Button statusButton = new Button("Status");
        styleSecondaryButton(statusButton);
        statusButton.setOpacity(0);

        // reviewButton.setOnAction(e -> {
        // try {
        //     new paymentDone().start(primaryStage);
        // } catch (Exception ex) {
        //     // TODO: handle exception
        //     ex.printStackTrace();
        // });
    

        statusButton.setOnAction(e -> {
            review reviewPage = new review();
            try {
              new com.cafeverse.view. status().start(primaryStage);  // Opens Review page in the SAME window
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox contentBox = new VBox(20, thankYou, status, description, homeButton, statusButton);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setPadding(new Insets(30));

        VBox root = new VBox(header, contentBox);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root,screenWidth, screenHeight);
        primaryStage.setTitle("Payment Successful");
        primaryStage.setScene(scene);
        primaryStage.show();

        fadeInElements(thankYou, status, description, homeButton, statusButton);
    }

    private void styleMainButton(Button button) {
        button.setFont(Font.font("Arial", 18));
        button.setTextFill(Color.WHITE);
        button.setStyle("-fx-background-color: #2ecc71; -fx-background-radius: 10; -fx-padding: 10px 25px;");
    }

    private void styleSecondaryButton(Button button) {
        button.setFont(Font.font("Arial", 16));
        button.setTextFill(Color.BLACK);
        button.setStyle("-fx-background-color: #f1f1f1; -fx-background-radius: 8; -fx-border-color: #bdbdbd;");
    }

    private void fadeInElements(javafx.scene.Node... nodes) {
        SequentialTransition seq = new SequentialTransition();
        for (javafx.scene.Node node : nodes) {
            FadeTransition fade = new FadeTransition(Duration.seconds(1), node);
            fade.setFromValue(0);
            fade.setToValue(1);
            seq.getChildren().add(fade);
        }
        seq.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

