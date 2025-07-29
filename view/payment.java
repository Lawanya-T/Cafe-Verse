package com.cafeverse.view;

import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class payment extends Application {

     Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

    VBox upiOptionsBox;
    VBox cashPane;

    @Override
    public void start(Stage stage) {
        Label title = new Label("💳 Payment Options");
        title.setFont(Font.font("Arial", 26));
        title.setStyle("-fx-font-weight: bold;");

        // Menu Button
        MenuButton menuButton = new MenuButton("☰ Menu");
        menuButton.setStyle("-fx-background-color: #e6ccb2; -fx-text-fill: #5c3d2e; "
                + "-fx-font-weight: bold; -fx-font-size: 18px;");

        MenuItem homeItem = new MenuItem("Home");
        MenuItem orderItem = new MenuItem("My Orders");
        MenuItem profileItem = new MenuItem("Profile");
        menuButton.getItems().addAll(homeItem, orderItem, profileItem);

        HBox menuBox = new HBox(menuButton);
        menuBox.setAlignment(Pos.TOP_RIGHT);
        menuBox.setPadding(new Insets(10, 10, 0, 0));

        // Payment Buttons
        ToggleGroup paymentGroup = new ToggleGroup();
        ToggleButton upiBtn = createCardButton("UPI", "assets/images/finalupi.png");
        ToggleButton cardBtn = createCardButton("CARD", "assets/images/card.png");
        ToggleButton cashBtn = createCardButton("CASH", "assets/images/cash.png");

        upiBtn.setToggleGroup(paymentGroup);
        cardBtn.setToggleGroup(paymentGroup);
        cashBtn.setToggleGroup(paymentGroup);

        HBox paymentOptions = new HBox(60, upiBtn, cardBtn, cashBtn);
        paymentOptions.setAlignment(Pos.CENTER);

        // Google Pay Option for UPI
        Image gpayLogo = new Image("assets/images/google pay.jpeg", 20, 20, true, true);
        ImageView gpayView = new ImageView(gpayLogo);
        RadioButton gpayRadio = new RadioButton("GPay");
        gpayRadio.setGraphic(gpayView);
        gpayRadio.setContentDisplay(ContentDisplay.LEFT);

        // Card placeholder
        Label cardLbl = new Label("Card payment coming soon …");
        cardLbl.setFont(Font.font("Arial", 35));
        VBox cardPane = new VBox(cardLbl);
        cardPane.setPadding(new Insets(50));
        cardPane.setAlignment(Pos.CENTER);
        cardPane.setVisible(false);

        // Cash Section
        Label cashLbl = new Label("Pay at counter.");
        cashLbl.setFont(Font.font("Arial", 18));

        Button cashConfirmBtn = new Button("Confirm Cash Payment");
        cashConfirmBtn.setStyle(
                "-fx-background-color: #f39c12; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 16px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-padding: 8px 20px; " +
                        "-fx-background-radius: 20;"
        );
        cashConfirmBtn.setOnAction(e -> {
            showAlert("Cash Payment", "Please pay at the counter to complete your order.");
        });

        cashPane = new VBox(15, cashLbl, cashConfirmBtn);
        cashPane.setAlignment(Pos.CENTER);
        cashPane.setPadding(new Insets(20));
        cashPane.setVisible(false);

        // UPI Section
        TextField upiField = new TextField();
        upiField.setPromptText("Enter UPI ID");
        upiField.setPrefWidth(300);
        Button addBtn = new Button("+");
        HBox upiEntry = new HBox(40, upiField, addBtn);
        upiEntry.setAlignment(Pos.CENTER);

        Button upiPayBtn = new Button("Pay via UPI");
        upiPayBtn.setStyle(
                "-fx-background-color: #4CAF50; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 16px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-padding: 8px 25px; " +
                        "-fx-background-radius: 20;"
        );

        // Payment Done Page navigation
        upiPayBtn.setOnAction(e -> {
            String upiId = upiField.getText();
            if (upiId.isEmpty()) {
                showAlert("UPI Error", "Please enter your UPI ID.");
            } else {
                paymentDone donePage = new paymentDone();
                try {
                    donePage.start(stage);  // Open PaymentDone in same window
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        upiOptionsBox = new VBox(10, gpayRadio, upiEntry, upiPayBtn);
        upiOptionsBox.setAlignment(Pos.CENTER);
        upiOptionsBox.setPadding(new Insets(20, 0, 0, 0));

        // Toggle logic
        paymentGroup.selectedToggleProperty().addListener((obs, oldT, newT) -> {
            upiOptionsBox.setVisible(false);
            cardPane.setVisible(false);
            cashPane.setVisible(false);
            if (newT == upiBtn) upiOptionsBox.setVisible(true);
            else if (newT == cardBtn) cardPane.setVisible(true);
            else if (newT == cashBtn) cashPane.setVisible(true);
        });

        // Default UPI selected
        paymentGroup.selectToggle(upiBtn);
        upiOptionsBox.setVisible(true);

        // Place Order Button
        Button submitBtn = new Button("Place Order");
        submitBtn.setStyle(
                "-fx-background-color: rgba(120, 17, 17, 1); " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 20px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-padding: 12px 40px; " +
                        "-fx-background-radius: 50;"
        );
        submitBtn.setOnAction(e -> {
            showAlert("Order Confirmed", "Your order has been placed! 🎉");
        });

        // Pulse animation
        ScaleTransition pulse = new ScaleTransition(Duration.seconds(1), submitBtn);
        pulse.setFromX(1.0);
        pulse.setFromY(1.0);
        pulse.setToX(1.1);
        pulse.setToY(1.1);
        pulse.setCycleCount(Animation.INDEFINITE);
        pulse.setAutoReverse(true);
        pulse.play();

        // Optional: Pay button for extra confirmation
        Button payBtn = new Button("Pay");
        payBtn.setStyle(
                "-fx-background-color: #4CAF50; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 18px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-padding: 10px 30px; " +
                        "-fx-background-radius: 30;"
        );
        payBtn.setOnAction(e -> showAlert("Payment", "Processing your payment..."));

        VBox footerButtons = new VBox(10, submitBtn, payBtn);
        footerButtons.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setTop(menuBox);
        root.setCenter(new VBox(30, title, paymentOptions, upiOptionsBox, cardPane, cashPane));
        root.setBottom(new HBox(footerButtons));
        root.setStyle("-fx-background-color: linear-gradient(#c8a185,#fce3d1);");

        Scene scene = new Scene(root, screenWidth, screenHeight);
        stage.setTitle("CupfulCanvas - Payment");
        stage.setScene(scene);
        stage.show();
    }

    private ToggleButton createCardButton(String label, String iconPath) {
        ImageView icon = new ImageView(new Image(iconPath, 400, 300, true, true));
        Label text = new Label(label);
        text.setFont(Font.font("Arial", 20));
        VBox content = new VBox(5, icon, text);
        content.setAlignment(Pos.CENTER);
        ToggleButton card = new ToggleButton();
        card.setGraphic(content);
        card.setPrefSize(100, 130);
        card.setStyle("-fx-background-color: white; -fx-border-color: #ccc; -fx-border-radius: 8px; -fx-background-radius: 8px;");
        DropShadow shadow = new DropShadow();
        shadow.setRadius(8);
        shadow.setOffsetX(2);
        shadow.setOffsetY(4);
        shadow.setColor(Color.rgb(0, 0, 0, 0.2));
        card.setEffect(shadow);
        return card;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}



















// package com.project;

// import javafx.animation.Animation;
// import javafx.animation.ScaleTransition;
// import javafx.application.Application;
// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.*;
// import javafx.scene.effect.DropShadow;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.*;
// import javafx.scene.paint.Color;
// import javafx.scene.text.Font;
// import javafx.stage.Stage;
// import javafx.util.Duration;

// public class payment extends Application {

//     VBox upiOptionsBox;
//     VBox cashPane;

//     @Override
//     public void start(Stage stage) {
//         Label title = new Label(" – Payment");
//         title.setFont(Font.font("Arial", 26));
//         title.setStyle("-fx-font-weight: bold;");

//         // Menu Button
//         MenuButton menuButton = new MenuButton("☰ Menu");
//         menuButton.setStyle("-fx-background-color: #e6ccb2; -fx-text-fill: #5c3d2e; "
//                 + "-fx-font-weight: bold; -fx-font-size: 18px;");

//         MenuItem homeItem = new MenuItem("Home");
//         MenuItem orderItem = new MenuItem("My Orders");
//         MenuItem profileItem = new MenuItem("Profile");
//         menuButton.getItems().addAll(homeItem, orderItem, profileItem);

//         HBox menuBox = new HBox(menuButton);
//         menuBox.setAlignment(Pos.TOP_RIGHT);
//         menuBox.setPadding(new Insets(10, 10, 0, 0));

//         // Payment Options
//         ToggleGroup paymentGroup = new ToggleGroup();
//         ToggleButton upiBtn = createCardButton("UPI", "Assets\\Images\\finalupi.png");
//         ToggleButton cardBtn = createCardButton("CARD", "Assets\\Images\\card.png");
//         ToggleButton cashBtn = createCardButton("CASH", "Assets\\Images\\cash.png");

//         upiBtn.setToggleGroup(paymentGroup);
//         cardBtn.setToggleGroup(paymentGroup);
//         cashBtn.setToggleGroup(paymentGroup);

//         HBox paymentOptions = new HBox(60, upiBtn, cardBtn, cashBtn);
//         paymentOptions.setAlignment(Pos.CENTER);

//         // Google Pay Option for UPI
//         Image gpayLogo = new Image("Assets/Images/google pay.jpeg", 20, 20, true, true);
//         ImageView gpayView = new ImageView(gpayLogo);
//         RadioButton gpayRadio = new RadioButton("GPay");
//         gpayRadio.setGraphic(gpayView);
//         gpayRadio.setContentDisplay(ContentDisplay.LEFT);

//         // Card placeholder
//         Label cardLbl = new Label("Card payment coming soon …");
//         cardLbl.setFont(Font.font("Arial", 35));
//         VBox cardPane = new VBox(cardLbl);
//         cardPane.setPadding(new Insets(50));
//         cardPane.setAlignment(Pos.CENTER);
//         cardPane.setVisible(false);

//         // Cash Section
//         Label cashLbl = new Label("Pay at counter.");
//         cashLbl.setFont(Font.font("Arial", 18));

//         Button cashConfirmBtn = new Button("Confirm Cash Payment");
//         cashConfirmBtn.setStyle(
//                 "-fx-background-color: #f39c12; " +
//                         "-fx-text-fill: white; " +
//                         "-fx-font-size: 16px; " +
//                         "-fx-font-weight: bold; " +
//                         "-fx-padding: 8px 20px; " +
//                         "-fx-background-radius: 20;"
//         );
//         cashConfirmBtn.setOnAction(e -> {
//             showAlert("Cash Payment", "Please pay at the counter to complete your order.");
//         });

//         cashPane = new VBox(15, cashLbl, cashConfirmBtn);
//         cashPane.setAlignment(Pos.CENTER);
//         cashPane.setPadding(new Insets(20));
//         cashPane.setVisible(false);

//         // UPI Section
//         TextField upiField = new TextField();
//         upiField.setPromptText("Enter UPI ID");
//         upiField.setPrefWidth(300);
//         Button addBtn = new Button("+");
//         HBox upiEntry = new HBox(40, upiField, addBtn);
//         upiEntry.setAlignment(Pos.CENTER);

//         Button upiPayBtn = new Button("Pay via UPI");
//         upiPayBtn.setStyle(
//                 "-fx-background-color: #4CAF50; " +
//                         "-fx-text-fill: white; " +
//                         "-fx-font-size: 16px; " +
//                         "-fx-font-weight: bold; " +
//                         "-fx-padding: 8px 25px; " +
//                         "-fx-background-radius: 20;"
//         );

//         // --- Navigate to paymentDone page when clicked ---
//         upiPayBtn.setOnAction(e -> {
//             String upiId = upiField.getText();
//             if (upiId.isEmpty()) {
//                 showAlert("UPI Error", "Please enter your UPI ID.");
//             } else {
//                 paymentDone donePage = new paymentDone();
//                 try {
//                     donePage.start(stage);  // Open PaymentDone in same window
//                 } catch (Exception ex) {
//                     ex.printStackTrace();
//                 }
//             }
//         });

//         upiOptionsBox = new VBox(10, gpayRadio, upiEntry, upiPayBtn);
//         upiOptionsBox.setAlignment(Pos.CENTER);
//         upiOptionsBox.setPadding(new Insets(20, 0, 0, 0));

//         // Toggle logic
//         paymentGroup.selectedToggleProperty().addListener((obs, oldT, newT) -> {
//             upiOptionsBox.setVisible(false);
//             cardPane.setVisible(false);
//             cashPane.setVisible(false);
//             if (newT == upiBtn) upiOptionsBox.setVisible(true);
//             else if (newT == cardBtn) cardPane.setVisible(true);
//             else if (newT == cashBtn) cashPane.setVisible(true);
//         });

//         // Default UPI
//         paymentGroup.selectToggle(upiBtn);
//         upiOptionsBox.setVisible(true);

//         // Buttons at bottom
//         Button submitBtn = new Button("Place Order");
//         submitBtn.setStyle(
//                 "-fx-background-color: rgba(120, 17, 17, 1); " +
//                         "-fx-text-fill: white; " +
//                         "-fx-font-size: 20px; " +
//                         "-fx-font-weight: bold; " +
//                         "-fx-padding: 12px 40px; " +
//                         "-fx-background-radius: 50;"
//         );

//         ScaleTransition pulse = new ScaleTransition(Duration.seconds(1), submitBtn);
//         pulse.setFromX(1.0);
//         pulse.setFromY(1.0);
//         pulse.setToX(1.1);
//         pulse.setToY(1.1);
//         pulse.setCycleCount(Animation.INDEFINITE);
//         pulse.setAutoReverse(true);
//         pulse.play();

//         Button payBtn = new Button("Pay");
//         payBtn.setStyle(
//                 "-fx-background-color: #4CAF50; " +
//                         "-fx-text-fill: white; " +
//                         "-fx-font-size: 18px; " +
//                         "-fx-font-weight: bold; " +
//                         "-fx-padding: 10px 30px; " +
//                         "-fx-background-radius: 30;"
//         );
//         payBtn.setOnAction(e -> showAlert("Payment", "Processing your payment..."));

//         VBox footerButtons = new VBox(10, submitBtn, payBtn);
//         footerButtons.setAlignment(Pos.CENTER);

//         BorderPane root = new BorderPane();
//         root.setTop(menuBox);
//         root.setCenter(new VBox(30, title, paymentOptions, upiOptionsBox, cardPane, cashPane));
//         root.setBottom(new HBox(footerButtons));
//         root.setStyle("-fx-background-color: linear-gradient(#c8a185,#fce3d1);");

//         Scene scene = new Scene(root, 800, 500);
//         stage.setTitle("CupfulCanvas - Payment");
//         stage.setScene(scene);
//         stage.show();
//     }

//     private ToggleButton createCardButton(String label, String iconPath) {
//         ImageView icon = new ImageView(new Image(iconPath, 400, 300, true, true));
//         Label text = new Label(label);
//         text.setFont(Font.font("Arial", 20));
//         VBox content = new VBox(5, icon, text);
//         content.setAlignment(Pos.CENTER);
//         ToggleButton card = new ToggleButton();
//         card.setGraphic(content);
//         card.setPrefSize(100, 130);
//         card.setStyle("-fx-background-color: white; -fx-border-color: #ccc; -fx-border-radius: 8px; -fx-background-radius: 8px;");
//         DropShadow shadow = new DropShadow();
//         shadow.setRadius(8);
//         shadow.setOffsetX(2);
//         shadow.setOffsetY(4);
//         shadow.setColor(Color.rgb(0, 0, 0, 0.2));
//         card.setEffect(shadow);
//         return card;
//     }

//     private void showAlert(String title, String message) {
//         Alert alert = new Alert(Alert.AlertType.INFORMATION);
//         alert.setTitle(title);
//         alert.setHeaderText(null);
//         alert.setContentText(message);
//         alert.showAndWait();
//     }

//     public static void main(String[] args) {
//         launch(args);
//     }
// }
