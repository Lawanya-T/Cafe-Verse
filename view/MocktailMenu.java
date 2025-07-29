
package com.cafeverse.view;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MocktailMenu {

    public Scene createMocktailScene(Stage stage) {

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();
        
        Label title = new Label("🍹 Refreshing Mocktails 🍹");
        title.setFont(Font.font("Arial", 40));
        title.setTextFill(Color.ORANGERED);
        title.setPadding(new Insets(30, 0, 20, 0));
        title.setAlignment(Pos.CENTER);

        // Category Sidebar
        VBox categoryBox = new VBox(20);
        categoryBox.setPadding(new Insets(20));
        categoryBox.setStyle("-fx-background-color: #fff3e0;");
        categoryBox.setPrefWidth(240);
        categoryBox.setAlignment(Pos.TOP_CENTER);

        Label categoryHeading = new Label("Categories");
        categoryHeading.setStyle(
            "-fx-font-size: 30px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: darkorange;" +
            "-fx-underline: true;"
        );
        categoryBox.getChildren().add(categoryHeading);

        String[] categories = {
            "Pizza", "Pasta", "Burger",
            "Coffee", "Beverages",
            "Desserts", "Fries", "Sandwich"
        };

        for (String cat : categories) {
            Button catBtn = new Button(cat);
            catBtn.setMaxWidth(Double.MAX_VALUE);
            catBtn.setStyle(
                "-fx-background-color: #ffcc80;" +
                "-fx-text-fill: #333333;" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 20;"
            );

            ScaleTransition scaleIn = new ScaleTransition(Duration.millis(200), catBtn);
            scaleIn.setToX(1.05);
            scaleIn.setToY(1.05);
            ScaleTransition scaleOut = new ScaleTransition(Duration.millis(200), catBtn);
            scaleOut.setToX(1);
            scaleOut.setToY(1);

            catBtn.setOnMouseEntered(e -> scaleIn.playFromStart());
            catBtn.setOnMouseExited(e -> scaleOut.playFromStart());

            categoryBox.getChildren().add(catBtn);
        }

        // Menu Items
        VBox menuContainer = new VBox(40);
        menuContainer.setPadding(new Insets(50));
        menuContainer.setStyle("-fx-background-color: #fff8e1;");
        menuContainer.setAlignment(Pos.TOP_CENTER);

        String[][] items = {
                {"Virgin Mojito", "₹99", "/assets/images/virgin.jpeg"},
                {"Blue Lagoon", "₹109", "/assets/images/bluelagoon.jpeg"},
                {"Strawberry Cooler", "₹119", "/assets/images/cheesecornsandwich.jpeg"},
                {"Green Apple Mojito", "₹129", "/assets/images/greenapple.jpeg"},
                {"Mango Mule", "₹139", "/assets/images/mangomule.jpeg"},
                {"Cucumber Mint Cooler", "₹119", "/assets/images/cucumbermint.jpeg"},
                {"Kiwi Fizz", "₹129", "/assets/images/kiwifizz.jpeg"},
                {"Watermelon Breeze", "₹109", "/assets/images/watermelonbreeze.jpeg"},
                {"Pineapple Ginger Sparkle", "₹139", "/assets/images/pineappleginger.jpeg"}
        };

        for (int i = 0; i < items.length; i++) {
            boolean imageLeft = i % 2 == 0;
            menuContainer.getChildren().add(createCard(items[i][0], items[i][1], items[i][2], imageLeft));
        }

        ScrollPane scrollPane = new ScrollPane(menuContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background:  #fff8e1;");
        scrollPane.setPrefViewportHeight(700);

        // Layout
        HBox mainContent = new HBox(categoryBox, scrollPane);
        mainContent.setSpacing(10);
        HBox.setHgrow(scrollPane, Priority.ALWAYS);

        // Back & Cart Buttons
        Button backButton = new Button("← Back to Menu");
        backButton.setOnAction(e -> new CafeCategories().start(stage));
        backButton.setStyle("-fx-background-color: #e6ccb2; -fx-text-fill: #5c3d2e; -fx-font-weight: bold; -fx-font-size: 16px;");
        // backButton.setStyle("-fx-background-color:gray;-fx-text-fill:black");

        Button cartButton = new Button("🛒 Go to Cart");
        cartButton.setOnAction(e -> stage.setScene(new cartPage().createCartScene(stage)));

      cartButton.setStyle("-fx-background-color: #e6ccb2; -fx-text-fill: #5c3d2e; -fx-font-weight: bold; -fx-font-size: 16px;");
      // cartButton.setStyle("-fx-background-color:gray;-fx-text-fill:black");

         HBox topRightButtons = new HBox(10, backButton, cartButton);
topRightButtons.setAlignment(Pos.TOP_RIGHT);
topRightButtons.setPadding(new Insets(10, 30, 0, 0)); 

        VBox root = new VBox(20, topRightButtons, title, mainContent);
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: #fff8e1;");
        VBox.setVgrow(mainContent, Priority.ALWAYS);

        return new Scene(root, screenWidth, screenHeight);
    }

    private VBox createCard(String name, String priceText, String imagePath, boolean imageOnLeft) {
        VBox card = new VBox(12);
        card.setPadding(new Insets(10));
        card.setSpacing(10);
        card.setMaxWidth(850);
        card.setStyle("-fx-background-color: #ece087ff; -fx-background-radius: 20;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 10, 0.2, 0, 6);");

        Image image;
        try {
            image = new Image(getClass().getResourceAsStream(imagePath));
        } catch (Exception e) {
            System.out.println("Error loading image: " + imagePath);
            image = new Image("https://via.placeholder.com/200");
        }

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(300);
        imageView.setFitHeight(300);
        imageView.setPreserveRatio(true);

        ScaleTransition st = new ScaleTransition(Duration.millis(200), imageView);
        imageView.setOnMouseEntered(e -> {
            st.setToX(1.1);
            st.setToY(1.1);
            st.playFromStart();
        });
        imageView.setOnMouseExited(e -> {
            st.setToX(1);
            st.setToY(1);
            st.playFromStart();
        });

        Label nameLabel = new Label(name);
        nameLabel.setFont(Font.font("Arial", 22));
        nameLabel.setTextFill(Color.web("#333"));

        Label priceLabel = new Label(priceText);
        priceLabel.setFont(Font.font("Arial", 18));
        priceLabel.setTextFill(Color.FORESTGREEN);

        int priceValue = Integer.parseInt(priceText.replaceAll("[^0-9]", ""));

        Button addButton = new Button("Add Item");
        addButton.setStyle("-fx-background-color: orangered; -fx-text-fill: white;" +
            "-fx-font-size: 14px; -fx-background-radius: 18; -fx-padding: 8px 18px;");
        addButton.setOnAction(e -> {
            cart.addItem(name, priceValue,imagePath); // ✅ CORRECTED LINE
            Alert alert = new Alert(Alert.AlertType.INFORMATION, name + " added to cart!");
            alert.showAndWait();
        });

        VBox infoBox = new VBox(10, nameLabel, priceLabel, addButton);
        infoBox.setAlignment(Pos.CENTER_LEFT);

        HBox row = new HBox(40);
        row.setPadding(new Insets(10));
        row.setAlignment(Pos.CENTER);

        if (imageOnLeft) {
            row.getChildren().addAll(imageView, infoBox);
        } else {
            row.getChildren().addAll(infoBox, imageView);
        }

        card.getChildren().add(row);
        return card;
    }

    public void start(Stage primaryStage) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }
}

