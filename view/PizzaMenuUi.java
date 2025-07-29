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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PizzaMenuUi {

     Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

    public void start(Stage stage) {
        Label title = new Label("🍕 Welcome to Pizza Zone 🍕");
        title.setFont(Font.font("Arial", 40));
        title.setTextFill(Color.ORANGERED);
        title.setPadding(new Insets(30, 0, 20, 0));
        title.setAlignment(Pos.CENTER);

        VBox categoryBox = new VBox(30);
        categoryBox.setPadding(new Insets(20));
        categoryBox.setStyle("-fx-background-color: #fff3e0;");
        categoryBox.setPrefWidth(200);
        categoryBox.setAlignment(Pos.TOP_CENTER);

        Label categoryHeading = new Label("Categories");
        categoryHeading.setStyle(
            "-fx-font-size: 28px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: darkorange;" +
            "-fx-padding: 0 0 10 0;" +
            "-fx-underline: true;"
        );
        categoryBox.getChildren().add(categoryHeading);

        String[] categories = {
            "Pizza", "Pasta", "Burger", "Coffee",
            "Beverages", "Deserts", "Fries", "Sandwich"
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
            ScaleTransition in = new ScaleTransition(Duration.millis(200), catBtn);
            in.setToX(1.05); in.setToY(1.05);
            ScaleTransition out = new ScaleTransition(Duration.millis(200), catBtn);
            out.setToX(1); out.setToY(1);
            catBtn.setOnMouseEntered(e -> in.playFromStart());
            catBtn.setOnMouseExited(e -> out.playFromStart());
            categoryBox.getChildren().add(catBtn);
        }

        VBox menuContainer = new VBox(40);
        menuContainer.setPadding(new Insets(40));
        menuContainer.setStyle("-fx-background-color: #fff8e1;");
        menuContainer.setAlignment(Pos.TOP_CENTER);

        String[][] pizzas = {
            {"Tandoori Paneer Pizza", "₹279", "/assets/images/buger1.jpeg"},
            {"Mushroom Mania Pizza", "₹259", "/assets/images/burger2.jpeg"},
            {"Peri Peri Veggie", "₹289", "/assets/images/burger3.jpeg"},
            {"Four Cheese Blast Pizza", "₹309", "/assets/images/burger4.jpeg"},
            {"Capsicum & Onion Classic", "₹219", "/assets/images/chicken_cheese_burst.jpeg"},
            {"Tomato Basil Delight", "₹199", "/assets/images/Fiery_aloo_tikki.jpeg"},
            {"Chilli Cheese Garlic", "₹239", "/assets/images/crispy_paner_italia.jpeg"},
            {"Italian Garden Pizza", "₹269", "/assets/images/farm_spicy.jpeg"},
            {"Spicy Schezwan Pizza", "₹249", "/assets/images/cheese_masala_burger.jpeg"},
            {"Olive & Jalapeno Pizza", "₹259", "/assets/images/farm_spicy_supreme_burger.jpeg"}
        };

        for (int i = 0; i < pizzas.length; i++) {
            boolean imageLeft = i % 2 == 0;
            menuContainer.getChildren().add(createCard(pizzas[i][0], pizzas[i][1], pizzas[i][2], imageLeft));
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

        VBox root = new VBox(20, topRightButtons, cartButton, title);
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: #fff8e1;");
        VBox.setVgrow(mainContent, Priority.ALWAYS);

        Scene scene = new Scene(root, screenWidth, screenHeight);
        stage.setTitle("Pizza Menu");
        stage.setScene(scene);
        stage.show();
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
            System.out.println("Image not found: " + imagePath);
            image = new Image("https://via.placeholder.com/200");
        }

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(300);
        imageView.setFitHeight(300);
        imageView.setPreserveRatio(true);

        ScaleTransition st = new ScaleTransition(Duration.millis(200), imageView);
        imageView.setOnMouseEntered(e -> {
            st.setToX(1.1); st.setToY(1.1); st.playFromStart();
        });
        imageView.setOnMouseExited(e -> {
            st.setToX(1); st.setToY(1); st.playFromStart();
        });

        Label nameLabel = new Label(name);
        nameLabel.setFont(Font.font("Arial", 22));
        nameLabel.setTextFill(Color.web("#333"));

        Label priceLabel = new Label(price);
        priceLabel.setFont(Font.font("Arial", 18));
        priceLabel.setTextFill(Color.FORESTGREEN);

        Button addButton = new Button("Add Item");
        addButton.setStyle("-fx-background-color: orangered; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 18;");
        addButton.setOnAction(e -> {
            int priceInt = Integer.parseInt(price.replaceAll("[^0-9]", ""));
            cart.addItem(new cart.Item(name, priceInt, imagePath));
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

    public Scene createPizzaScene(Stage stage) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createPizzaScene'");
    }

    public void showPizzaMenu(Stage stage) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showPizzaMenu'");
    }
}




