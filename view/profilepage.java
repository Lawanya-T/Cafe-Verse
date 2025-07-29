package com.cafeverse.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class profilepage extends Application {

    private Label name;
    private Label email;
    private Label phone;
    private Label address;

    private User user;

    public profilepage(User newUser) {
       this.user = newUser;
    }

    public void profile(User user) {
        this.user = user;
    }

    @Override
    public void start(Stage primaryStage) {
        // ====== Profile Picture ======
        ImageView profilePic = new ImageView(new Image("https://via.placeholder.com/100"));
        profilePic.setFitWidth(100);
        profilePic.setFitHeight(100);

        // ====== User Info Labels (dynamic from user object) ======
        name = new Label(user.getName());
        name.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        email = new Label(user.getEmail());
        phone = new Label(user.getPhone());
        address = new Label(user.getLocation());

        VBox userInfo = new VBox(5, name, email, phone, address);
        userInfo.setAlignment(Pos.CENTER_LEFT);

        Button editProfile = new Button("Edit Profile");
        editProfile.setStyle(buttonStyle());
        editProfile.setOnAction(e -> openEditProfileDialog(primaryStage));

        HBox profileBox = new HBox(20, profilePic, userInfo, editProfile);
        profileBox.setAlignment(Pos.CENTER_LEFT);
        profileBox.setPadding(new Insets(15));
        profileBox.setStyle(cardStyle());

        // Loyalty Section
        Label points = new Label("Points: 320");
        Label tier = new Label("Tier: Gold Member");
        Button redeemBtn = new Button("Redeem Points");
        redeemBtn.setStyle(buttonStyle());

        VBox loyaltyBox = new VBox(10, points, tier, redeemBtn);
        loyaltyBox.setPadding(new Insets(10));
        loyaltyBox.setAlignment(Pos.CENTER_LEFT);
        loyaltyBox.setStyle("-fx-background-color: #fff3cd; -fx-border-color: #ffc107; "
                + "-fx-border-radius: 10; -fx-background-radius: 10;");

        TitledPane loyaltyPane = new TitledPane("Loyalty & Rewards", loyaltyBox);

        ListView<String> favorites = new ListView<>();
        favorites.getItems().addAll("☕ Cappuccino", "🥐 Croissant");
        favorites.setPrefHeight(80);
        TitledPane favPane = new TitledPane("Favorite Orders", favorites);

        ListView<String> history = new ListView<>();
        history.getItems().addAll("12 Jul - Cappuccino - Stage.50", "05 Jul - Latte - homStage.00");
        history.setPrefHeight(100);
        TitledPane historyPane = new TitledPane("Order History", history);

        Button settingsBtn = new Button("Settings");
        Button logoutBtn = new Button("Log Out");
        settingsBtn.setStyle(buttonStyle());
        logoutBtn.setStyle(buttonStyle());

        HBox bottomButtons = new HBox(10, settingsBtn, logoutBtn);
        bottomButtons.setAlignment(Pos.CENTER_RIGHT);
        bottomButtons.setPadding(new Insets(10));

        VBox root = new VBox(15, profileBox, loyaltyPane, favPane, historyPane, bottomButtons);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #f8f9fa, #e9ecef);");

        Scene scene = new Scene(root, 500, 600);
        primaryStage.setTitle("User Profile");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openEditProfileDialog(Stage parent) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(parent);
        dialog.setTitle("Edit Profile");

        TextField nameField = new TextField(name.getText());
        TextField emailField = new TextField(email.getText());
        TextField phoneField = new TextField(phone.getText());
        TextField addressField = new TextField(address.getText());

        Button saveBtn = new Button("Save");
        saveBtn.setStyle(buttonStyle());
        saveBtn.setOnAction(e -> {
            name.setText(nameField.getText());
            email.setText(emailField.getText());
            phone.setText(phoneField.getText());
            address.setText(addressField.getText());
            dialog.close();
        });

        VBox dialogBox = new VBox(10, new Label("Name:"), nameField, new Label("Email:"), emailField,
                new Label("Phone:"), phoneField, new Label("Address:"), addressField, saveBtn);
        dialogBox.setPadding(new Insets(15));
        dialogBox.setAlignment(Pos.CENTER);

        dialog.setScene(new Scene(dialogBox, 300, 300));
        dialog.showAndWait();
    }

    private String cardStyle() {
        return "-fx-background-color: #ffffff; -fx-background-radius: 15; -fx-border-radius: 15; "
                + "-fx-border-color: #dee2e6;";
    }

    private String buttonStyle() {
        return "-fx-background-color: #6f42c1; -fx-text-fill: white; -fx-background-radius: 8; "
                + "-fx-font-weight: bold;";
    }

    public static void setUser(User currentUser) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setUser'");
    }

    public Parent createScene(Stage homStage) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createScene'");
    }
}