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

public class BurgerMenuUi {

    public Scene createBurgerScene(Stage stage) {

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();
        
        Label title = new Label("🍔 Welcome to Burger Zone 🍟");
        title.setFont(new Font("Arial", 46));
        title.setTextFill(Color.ORANGERED);
        title.setPadding(new Insets(30, 0, 20, 0));
        title.setAlignment(Pos.CENTER);

        VBox categoryBox = new VBox(40);
        categoryBox.setPadding(new Insets(20));
        categoryBox.setStyle("-fx-background-color: #fff3e0;");
        categoryBox.setPrefWidth(240);
        categoryBox.setAlignment(Pos.CENTER);

        Label categoryHeading = new Label("Categories");
        categoryHeading.setStyle(
                "-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: darkorange; -fx-padding: 0 0 10 0; -fx-underline: true;");
        categoryBox.getChildren().add(categoryHeading);

        String[] categories = {
                "Pizza", "Pasta", "Burger", "Coffee",
                "Beverages", "Deserts", "Fries", "Sandwich"
        };

        for (String cat : categories) {
            Button catBtn = new Button(cat);
            catBtn.setMaxWidth(Double.MAX_VALUE);
            catBtn.setStyle(
                    "-fx-background-color: #ffcc80; -fx-text-fill: #333333; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-radius: 20; -fx-padding: 6px 10px;");

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

        VBox menuContainer = new VBox(40);
        menuContainer.setPadding(new Insets(50));
        menuContainer.setStyle("-fx-background-color: #fff8e1;");
        menuContainer.setAlignment(Pos.TOP_CENTER);

        String[][] items = {
                {"Spicy Paneer Burger", "₹169", "/assets/images/burger3.jpeg"},
                {"Double Patty Delight", "₹199", "/assets/images/burger4.jpeg"},
                {"Chicken Cheese Burst Burger", "₹279", "/assets/images/chicken_cheese_burst.jpeg"},
                {"Fiery aloo Tikki", "₹65", "/assets/images/Fiery_aloo_tikki.jpeg"},
                {"Crispy Paneer Italia", "₹209", "/assets/images/crispy_paner_italia.jpeg"},
                {"Farm Spicy Paneer", "₹159", "/assets/images/farm_spicy.jpeg"},
                {"Cheese Masala Burger", "₹89", "/assets/images/cheese_masala_burger.jpeg"},
                {"Farm Spicy Paneer Supreme", "₹189", "/assets/images/farm_spicy_supreme_burger.jpeg"},
                {"Double Grilled Chicken", "₹169", "/assets/images/double_grilled_chicken.jpeg"},
                {"Go Green", "₹109", "/assets/images/go_green.jpeg"},
                {"Veg Double Trouble", "₹159", "/assets/images/veg_double_trouble.jpeg"},
                {"Crispy Masala", "₹79", "/assets/images/crispy_masala.jpeg"},
                {"Farm Grilled Chicken", "₹149", "/assets/images/farm_grilled_chicken.jpeg"},
                {"Farm Aloo Tikki", "₹56", "/assets/images/farm_aloo_tikki.jpeg"},
                {"Farm Egg", "₹59", "/assets/images/farm_egg.jpeg"},
                {"Veg Tedha Teekha Burger", "₹75", "/assets/images/tedha.jpeg"}
        };

        for (int i = 0; i < items.length; i++) {
            boolean imageLeft = i % 2 == 0;
            menuContainer.getChildren().add(createCard(items[i][0], items[i][1], items[i][2], imageLeft));
        }

        ScrollPane scrollPane = new ScrollPane(menuContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #fff8e1;");
        scrollPane.setPrefViewportHeight(700);

        HBox mainContent = new HBox(categoryBox, scrollPane);
        mainContent.setSpacing(10);
        HBox.setHgrow(scrollPane, Priority.ALWAYS);

        Button backButton = new Button("← Back to Menu");
        backButton.setOnAction(e -> new CafeCategories().start(stage));
        backButton.setStyle("-fx-background-color: #e6ccb2; -fx-text-fill: #5c3d2e; -fx-font-weight: bold; -fx-font-size: 16px;");

        Button cartButton = new Button("🛒 Go to Cart");
        cartButton.setOnAction(e -> stage.setScene(new cartPage().createCartScene(stage)));
        cartButton.setStyle("-fx-background-color: #e6ccb2; -fx-text-fill: #5c3d2e; -fx-font-weight: bold; -fx-font-size: 16px;");

         HBox topRightButtons = new HBox(10, backButton, cartButton);
topRightButtons.setAlignment(Pos.TOP_RIGHT);
topRightButtons.setPadding(new Insets(10, 30, 0, 0));

         VBox root = new VBox(20, topRightButtons, title, mainContent);
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: #fff8e1;");
        VBox.setVgrow(mainContent, Priority.ALWAYS);


        return new Scene(root, screenWidth, screenHeight);
    }

    private VBox createCard(String name, String price, String imagePath, boolean imageOnLeft) {
        VBox card = new VBox(12);
        card.setPadding(new Insets(10));
        card.setSpacing(10);
        card.setMaxWidth(850);
        card.setStyle("-fx-background-color: #ece087ff; -fx-background-radius: 20; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 10, 0.2, 0, 6);");

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

        Label priceLabel = new Label(price);
        priceLabel.setFont(Font.font("Arial", 18));
        priceLabel.setTextFill(Color.FORESTGREEN);

         int priceValue = Integer.parseInt(price.replaceAll("[^0-9]", ""));

          Button addButton = new Button("Add Item");
        addButton.setStyle("-fx-background-color: orangered; -fx-text-fill: white;" +
            "-fx-font-size: 14px; -fx-background-radius: 18; -fx-padding: 8px 18px;");
        addButton.setOnAction(e -> {
           cart.addItem(name, priceValue, imagePath); /// ✅ CORRECTED LINE
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

    public void showBurgerMenu(Stage stage) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showBurgerMenu'");
    }
}

