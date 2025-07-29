// File: cartPage.java
package com.cafeverse.view;

import com.cafeverse.controller.CartController;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class cartPage {

    CartController cartController = new CartController();
    private String username;
    private String name;
    private int quantity;
    private double price;

    cartPage(String username, String name, int quantity, double price){
        this.username = username;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public cartPage() {
        //TODO Auto-generated constructor stub
    }

    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    double screenWidth = screenBounds.getWidth();
    double screenHeight = screenBounds.getHeight();
    public Scene createCartScene(Stage stage) {
    VBox root = new VBox(20);
    root.setPadding(new Insets(30));

    Label title = new Label("🛒 Your Cart");
    title.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");

    VBox itemBox = new VBox(10);

    ScrollPane scrollPane = new ScrollPane(itemBox);
    scrollPane.setFitToWidth(true);
    scrollPane.setPrefHeight(350);
     scrollPane.setStyle("-fx-background-color: transparent;");

        Label totalLabel = new Label("Total: ₹" + cart.getTotal());
        totalLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: green; -fx-font-weight: bold;");

        updateCartItems(itemBox, totalLabel);

        Button checkoutBtn = new Button("Proceed to Checkout");
        checkoutBtn.setStyle("-fx-background-color: orange; -fx-text-fill: white; -fx-font-size: 16px; -fx-background-radius: 10;");
            checkoutBtn.setOnAction(e -> {
    System.out.println("✅ Checkout Button Clicked!");

    if (cart.getItems().length > 0) {
        try {
            new payment().start(stage); // only if payment class is ready
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        cartController.addCartItemForUser(username,name,quantity,price);
        System.out.println("username"+username);
        System.out.println("name"+name);
        System.out.println("quantity"+ quantity);
        System.out.println("price" + price);
    } else {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Cart is empty!");
        alert.showAndWait();
    }
});
            // if (cart.getItems().length > 0) {
            //     try {
            //         new payment().start(stage);
            //     } catch (Exception ex) {
            //         ex.printStackTrace();
            //     }
            // } else {
            //     Alert alert = new Alert(Alert.AlertType.WARNING, "Cart is empty!");
            //     alert.showAndWait();
            // }
            // cartController.addCartItemForUser(username,name,quantity,price);
        

        Button clearBtn = new Button("Clear All");
        clearBtn.setStyle("-fx-background-color: #cc0000; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 10;");
        clearBtn.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Clear entire cart?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    cart.clearCart();
                    updateCartItems(itemBox, totalLabel);
                }
            });
        });

        Button backButton = new Button("← Back to Menu");
        backButton.setOnAction(e -> new CafeCategories().start(stage));
      backButton.setStyle("-fx-background-color: #efce54ff; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 10;");

        HBox buttonRow = new HBox(20, clearBtn, checkoutBtn,backButton);
        buttonRow.setAlignment(Pos.CENTER_LEFT);

        root.getChildren().addAll(title, scrollPane, totalLabel, buttonRow);
        return new Scene(root,screenWidth, screenHeight);
    }
    private void updateCartItems(VBox itemBox, Label totalLabel) {
        itemBox.getChildren().clear();
        for (cart.Item item : cart.getItems()) {
            HBox row = new HBox(10);
            row.setAlignment(Pos.CENTER_LEFT);
            row.setPadding(new Insets(10));
            row.setStyle("-fx-background-color: #f5f5f5; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, gray, 5, 0, 0, 2);");

            ImageView imageView = new ImageView();
            try {
                imageView.setImage(new Image(item.imagePath));
            } catch (Exception e) {
                imageView.setImage(new Image("file:default.png")); // fallback image
            }
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);

            Label name = new Label(item.name);
            name.setPrefWidth(200);
            name.setStyle("-fx-font-size: 16px;");

            Label price = new Label("₹" + item.price);
            price.setPrefWidth(100);
            price.setStyle("-fx-font-size: 16px;");

            Button removeBtn = new Button("Remove");
            removeBtn.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-background-radius: 5;");
            removeBtn.setOnAction(e -> {
                cart.removeItem(item);
                updateCartItems(itemBox, totalLabel);
            });

            row.getChildren().addAll(imageView, name, price, removeBtn);
            itemBox.getChildren().add(row);
        }

        totalLabel.setText("Total: ₹" + cart.getTotal());
    }

    public Object start(Stage stage) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }
}

