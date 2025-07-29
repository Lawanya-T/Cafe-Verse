package com.cafeverse.view;

import com.cafeverse.controller.FirebaseAuthController1;
import com.cafeverse.dao.SignUpDao;
import com.cafeverse.model.UserSignUp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class signuppage {

    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    double screenWidth = screenBounds.getWidth();
    double screenHeight = screenBounds.getHeight();

    FirebaseAuthController1 authController1 = new FirebaseAuthController1();

    Scene signUpScene;
    Stage primaryStage;

    public void setSignUpPageSceneScene(Scene scene) {
        this.signUpScene = scene;
    }

    public void setP2Stage(Stage stage) {
        this.primaryStage = stage;
    }

    public StackPane createScene(Runnable onBack) {
        Image image = new Image("Assets\\Images\\logo3.png");
        ImageView iv = new ImageView(image);
        iv.setFitWidth(100);
        iv.setFitHeight(100);
        iv.setPreserveRatio(true);

        // User Input Fields
        TextField nameField = new TextField();
        nameField.setPromptText("Enter Name:");
        nameField.setMaxWidth(400);

        TextField genderField = new TextField();
        genderField.setPromptText("Enter Gender:");
        genderField.setMaxWidth(400);

        TextField mobileField = new TextField();
        mobileField.setPromptText("Enter Mobile Number:");
        mobileField.setMaxWidth(400);

        TextField emailField = new TextField();
        emailField.setPromptText("Enter Email ID:");
        emailField.setMaxWidth(400);

        TextField locField = new TextField();
        locField.setPromptText("Enter Location:");
        locField.setMaxWidth(400);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password:");
        passwordField.setMaxWidth(400);

        Label result = new Label();

        // Sign Up Button
        Button signUpBtn = new Button("Sign Up");
        signUpBtn.setFocusTraversable(false);
        signUpBtn.setAlignment(Pos.CENTER);
        signUpBtn.setOnAction(event -> {
            String name = nameField.getText();
            String gender = genderField.getText();
            String phone = mobileField.getText();
            String email = emailField.getText();
            String location = locField.getText();
            String password = passwordField.getText();

            // Authenticate using Firebase
            boolean success = authController1.signUpWithEmailAndPassword(email, password);

            if (success) {
                UserSignUp userSignUp = new UserSignUp();
                userSignUp.setEmail(email);
                userSignUp.setGender(gender);
                userSignUp.setLocation(location);
                userSignUp.setMobileNo(phone);
                userSignUp.setPassword(password);
                userSignUp.setName(name);
                SignUpDao.addUserDataToFirestore(userSignUp);
                result.setText("Signup Successful! Redirecting to Profile...");
                
                // Create user object
                // User newUser = new User(name, email, phone, location);

                // Redirect to Profile Page
                loginpage loginPage = new loginpage();
                try {
                    loginPage.start(primaryStage); // Launch login page after signup
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                result.setText("Signup Failed! Try again.");
            }
        });

        // Back Button
        Button backBtn = new Button("Back");
        backBtn.setFont(new Font(20));
        backBtn.setMaxWidth(80);
        backBtn.setMaxHeight(50);
        backBtn.setBackground(Background.EMPTY);
        backBtn.setOnAction(event -> onBack.run());

        VBox backContainer = new VBox(10, backBtn);
        backContainer.setAlignment(Pos.TOP_LEFT);
        backContainer.setPadding(new Insets(20));

        // Main Layout
        VBox formLayout = new VBox(30, backContainer, iv, nameField, genderField, mobileField,
                emailField, locField, passwordField, signUpBtn, result);
        formLayout.setStyle("""
            -fx-background-color: #ffffffcc;
            -fx-padding: 30;
            -fx-background-radius: 20;
            -fx-alignment: center;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0.3, 0, 4);
        """);
        formLayout.setMaxWidth(400);
        formLayout.setMaxHeight(280);

        VBox background = new VBox();
        background.setPrefWidth(screenWidth);
        background.setPrefHeight(screenHeight);
        background.setStyle("-fx-background-color: linear-gradient(to bottom right, #a55b3b, #fcebd5);");

        StackPane root = new StackPane(background, formLayout);
        root.setStyle("-fx-alignment: center;");

        return root;
    }
}
