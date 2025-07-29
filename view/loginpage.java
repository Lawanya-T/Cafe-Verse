package com.cafeverse.view;
import com.cafeverse.controller.CartController;
import com.cafeverse.controller.FirebaseAuthController;
import com.cafeverse.controller.FirebaseInitializer;
import com.cafeverse.controller.UserController;
import com.cafeverse.dao.SignUpDao;
import com.cafeverse.model.UserSignUp;
import com.cafeverse.util.SessionManager;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class loginpage extends Application{

    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    double screenWidth = screenBounds.getWidth();
    double screenHeight = screenBounds.getHeight();

    private final String ADMIN_EMAIL = "admin@example.com";
    private final String ADMIN_PASSWORD = "admin123";

    private Scene loginPageScene, SignUpPageSceneScene, admin, homScene;
    private Stage primaryStage, p2Stage, homStage;

    FirebaseAuthController authController = new FirebaseAuthController();    

    @Override
    public void start(Stage myStage) throws Exception {

        FirebaseInitializer.initialize();

        UserController userc = new UserController();
        userc.getUserAddress(ADMIN_EMAIL);

        CartController cart = new CartController();
        cart.fetchCartItems(ADMIN_EMAIL);
        
        Image image = new Image("assets\\images\\logo3.png");
        ImageView iv = new ImageView(image);
        iv.setFitWidth(100);
        iv.setFitWidth(100);
        iv.setPreserveRatio(true);
        
        TextField userField = new TextField();
        userField.setFocusTraversable(false);
        userField.setPromptText("Enter Email ID:");
        userField.setMaxWidth(400);

        PasswordField passwordField = new PasswordField();
        passwordField.setFocusTraversable(false);
        passwordField.setPromptText("Enter Password:");
        passwordField.setMaxWidth(400);
        Label result = new Label();

        Button loginBtn = new Button("Log In");
        loginBtn.setFocusTraversable(false);
        loginBtn.setAlignment(Pos.CENTER);
        loginBtn.setOnAction(event -> {
            // try {
            //     new com.cafeverse.view.AdminDashboard().start(myStage);
            // } catch (Exception e) {
            //     e.printStackTrace();
            // }
            String email = userField.getText();
            String password = passwordField.getText();

            // Admin login check
            if (email.equals(ADMIN_EMAIL) && password.equals(ADMIN_PASSWORD)) {
            result.setText("Login successful. Welcome, Admin!");
            

            // Open Admin Dashboard
            AdminDashboard adminDashboard = new AdminDashboard();
            
            Stage adminStage = new Stage();
            adminDashboard.setAdminStage(primaryStage);
            // adminDashboard.start(primaryStage); 

            try {
                adminDashboard.start(adminStage);
                primaryStage.close(); 
            } catch (Exception e) {
                e.printStackTrace();
                }

        } else if (authController.signInWithEmailAndPassword(email, password)) {
            UserSignUp user = SignUpDao.getUserData(email);
            result.setText("Login successful!");
            SessionManager.setUser(user);
            SessionManager.setUsername(email);
            initializeHomePage();
        } else {
            result.setText("Invalid credentials. Please try again.");
        }
                });


        Text user = new Text("Don't have an account?");
        user.setFill(Color.BLACK);
        user.setFont(new Font(20));
        Text signupText = new Text("Sign Up");
        signupText.setFill(Color.BLUE);
        signupText.setFocusTraversable(false);
        signupText.setFont(new Font(20));
        signupText.setFill(Color.BLACK);
        signupText.setOnMouseClicked(event -> { 
            signupText.setFill(Color.BLUE);
            initializePage2();
            primaryStage.setScene(SignUpPageSceneScene);
        });
        signupText.setOnMouseEntered(e -> signupText.setUnderline(true));
        signupText.setOnMouseExited(e -> signupText.setUnderline(false));

        HBox hb = new HBox(20,user,signupText);
        hb.setAlignment(Pos.CENTER);
        VBox vb = new VBox(20,iv,userField,passwordField,loginBtn,result,hb);
        // vb.setPadding(new Insets(50,50,50,50));
        // vb.setAlignment(Pos.CENTER);
        // vb.setStyle("-fx-background-color: #fffaf0;");
        // vb.setStyle("-fx-background-color: rgba(125, 8, 8, 0.75); -fx-background-radius: 15;");
        vb.setStyle("""
            -fx-background-color: #ffffffcc;
            -fx-padding: 30;
            -fx-background-radius: 20;
            -fx-alignment: center;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0.3, 0, 4);
        """);
        vb.setMaxWidth(300);
        vb.setMaxHeight(500);

        // Image backImage = new Image("assets\\images\\ambienece.jpg");
        // ImageView backView = new ImageView(backImage);
        // backView.setFitHeight(800);
        // backView.setFitWidth(1300);

        VBox vb1 = new VBox();
        vb1.setStyle("-fx-background-color: linear-gradient(to bottom right, #a55b3b, #fcebd5);");
        vb1.setPrefWidth(1300);
        vb1.setPrefHeight(800);
        
        StackPane root = new StackPane(vb1,vb);
        root.setStyle("-fx-alignment: full;");

        Scene myScene = new Scene(root,screenWidth,screenHeight);
        myStage.setScene(myScene);
        loginPageScene = myScene;
        primaryStage = myStage;
        SignUpPageSceneScene = myScene;
        p2Stage = myStage;
        myStage.setTitle("My Stage");
        myStage.setMaximized(true);
        myStage.show();
    }

    private void initializePage2() {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();
        signuppage page2 = new signuppage();
        page2.setP2Stage(primaryStage);
        SignUpPageSceneScene = new Scene(page2.createScene(this::handleLogout));
        page2.setSignUpPageSceneScene(SignUpPageSceneScene);
    }

    private void initializeHomePage(){
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();
        Home home = new Home();
        home.setHomStage(primaryStage);
        homScene = new Scene(home.createScene(this::handleLogout));
        home.setHomScene(homScene);
        primaryStage.setScene(homScene);
    }

    private void handleLogout() {
        primaryStage.setScene(loginPageScene);
    }
}

