package com.cafeverse.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class DeliveryMap extends Application {
  Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Delivery Status");

        WebView mapView = new WebView();
        mapView.getEngine().loadContent(getMapHTML(), "text/html");
        mapView.setPrefHeight(400);

        Image profileImage = loadImage("Assets/image/logo3.png");
        Image phoneImage = loadImage("Assets/image/profile.png");

        ImageView courierPic = new ImageView(profileImage);
        courierPic.setFitWidth(50);
        courierPic.setFitHeight(50);
        courierPic.setClip(new Circle(25, 25, 25));

        Label courierName = new Label("Sadik Mukhtar");
        courierName.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white;");
        Label courierRole = new Label("food courier");
        courierRole.setStyle("-fx-font-size: 12px; -fx-text-fill: white;");

        VBox courierInfo = new VBox(3, courierName, courierRole);

        ImageView phoneIcon = new ImageView(phoneImage);
        phoneIcon.setFitWidth(25);
        phoneIcon.setFitHeight(25);

        HBox courierCard = new HBox(10, courierPic, courierInfo, phoneIcon);
        courierCard.setAlignment(Pos.CENTER_LEFT);
        courierCard.setPadding(new Insets(10));
        courierCard.setStyle("-fx-background-color: #f57c00; -fx-background-radius: 8;");

        Label kitchenLabel = new Label("Kitchen\n40 min. left");
        kitchenLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #444;");
        Label deliveryLabel = new Label("Delivery\n20 min. left");
        deliveryLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #444;");

        Circle kitchenCheck = new Circle(8, Color.ORANGE);
        Circle deliveryCheck = new Circle(8, Color.TRANSPARENT);
        deliveryCheck.setStroke(Color.ORANGE);
        deliveryCheck.setStrokeWidth(2);

        HBox kitchenBox = new HBox(10, kitchenCheck, kitchenLabel);
        HBox deliveryBox = new HBox(10, deliveryCheck, deliveryLabel);
        kitchenBox.setAlignment(Pos.CENTER_LEFT);
        deliveryBox.setAlignment(Pos.CENTER_LEFT);

        VBox timelineBox = new VBox(15, kitchenBox, deliveryBox);
        timelineBox.setPadding(new Insets(10, 0, 10, 0));

        Button checkoutBtn = new Button("Select Your Menu→");
        checkoutBtn.setPrefWidth(250);
        checkoutBtn.setPrefHeight(40);
        checkoutBtn.setStyle("-fx-background-color: #e9af5dff; -fx-text-fill: white; -fx-font-size: 16px; -fx-background-radius: 8;");

        checkoutBtn.setOnAction(e -> {
            CafeCategories menuPage = new CafeCategories();
            try {
                Stage menuStage = new Stage();
                menuPage.start(menuStage);
                primaryStage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox bottomBox = new VBox(15, courierCard, timelineBox, checkoutBtn);
        bottomBox.setPadding(new Insets(20));
        bottomBox.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setTop(mapView);
        root.setCenter(bottomBox);
        root.setStyle("-fx-background-color: white; -fx-padding: 10;");

        Scene scene = new Scene(root, screenWidth, screenHeight);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Image loadImage(String path) {
        try {
            return new Image(getClass().getResource(path).toExternalForm());
        } catch (Exception e) {
            System.out.println("Image not found: " + path);
            return new Image("https://via.placeholder.com/50");
        }
    }

    private String getMapHTML() {
        return """
            <!DOCTYPE html>
            <html>
            <head>
              <meta charset=\"utf-8\" />
              <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">
              <link rel=\"stylesheet\" href=\"https://unpkg.com/leaflet/dist/leaflet.css\" />
              <style>
                #map { height: 100vh; width: 100%; }
                html, body { margin: 0; padding: 0; }
              </style>
            </head>
            <body>
              <div id=\"map\"></div>
              <script src=\"https://unpkg.com/leaflet/dist/leaflet.js\"></script>
              <script>
                var map = L.map('map').setView([12.0022, 8.5919], 13);
                L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                  attribution: '© OpenStreetMap contributors'
                }).addTo(map);
                L.marker([12.05, 8.55]).addTo(map).bindPopup('Courier Location').openPopup();
                L.marker([12.00, 8.60]).addTo(map).bindPopup('Destination');
                L.polyline([[12.05, 8.55],[12.00, 8.60]], {color:'orange'}).addTo(map);
              </script>
            </body>
            </html>
        """;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Parent createScene(Stage homStage) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createScene'");
    }
}

// package com.cafeverse.view;

// import javafx.application.Application;
// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.*;
// import javafx.scene.paint.Color;
// import javafx.scene.shape.Circle;
// import javafx.scene.web.WebView;
// import javafx.stage.Stage;

// public class DeliveryMap extends Application {

//     @Override
//     public void start(Stage primaryStage) {
//         primaryStage.setTitle("Delivery Status");

//         // === MAP ===
//         WebView mapView = new WebView();
//         mapView.getEngine().loadContent(getMapHTML(), "text/html");
//         mapView.setPrefHeight(400);

//         // === Load Images Safely (from resources) ===
//         Image profileImage = loadImage("/assets/images/profile.jpg");
//         Image phoneImage = loadImage("/assets/images/phoneicon.jpeg");

//         ImageView courierPic = new ImageView(profileImage);
//         courierPic.setFitWidth(50);
//         courierPic.setFitHeight(50);
//         courierPic.setClip(new Circle(25, 25, 25)); // Circular profile

//         Label courierName = new Label("Sadik Mukhtar");
//         courierName.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white;");
//         Label courierRole = new Label("food courier");
//         courierRole.setStyle("-fx-font-size: 12px; -fx-text-fill: white;");

//         VBox courierInfo = new VBox(3, courierName, courierRole);

//         ImageView phoneIcon = new ImageView(phoneImage);
//         phoneIcon.setFitWidth(25);
//         phoneIcon.setFitHeight(25);

//         HBox courierCard = new HBox(10, courierPic, courierInfo, phoneIcon);
//         courierCard.setAlignment(Pos.CENTER_LEFT);
//         courierCard.setPadding(new Insets(10));
//         courierCard.setStyle("-fx-background-color: #f57c00; -fx-background-radius: 8;");

//         // === TIMELINE ===
//         Label kitchenLabel = new Label("Kitchen\n40 min. left");
//         kitchenLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #444;");
//         Label deliveryLabel = new Label("Delivery\n20 min. left");
//         deliveryLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #444;");

//         Circle kitchenCheck = new Circle(8, Color.ORANGE);
//         Circle deliveryCheck = new Circle(8, Color.TRANSPARENT);
//         deliveryCheck.setStroke(Color.ORANGE);
//         deliveryCheck.setStrokeWidth(2);

//         HBox kitchenBox = new HBox(10, kitchenCheck, kitchenLabel);
//         HBox deliveryBox = new HBox(10, deliveryCheck, deliveryLabel);
//         kitchenBox.setAlignment(Pos.CENTER_LEFT);
//         deliveryBox.setAlignment(Pos.CENTER_LEFT);

//         VBox timelineBox = new VBox(15, kitchenBox, deliveryBox);
//         timelineBox.setPadding(new Insets(10, 0, 10, 0));

//         // === CHECKOUT BUTTON ===
//         Button checkoutBtn = new Button("Checkout");
//         checkoutBtn.setPrefWidth(250);
//         checkoutBtn.setPrefHeight(40);
//         checkoutBtn.setStyle("-fx-background-color: #f57c00; -fx-text-fill: white; -fx-font-size: 16px; -fx-background-radius: 8;");

//         VBox bottomBox = new VBox(15, courierCard, timelineBox, checkoutBtn);
//         bottomBox.setPadding(new Insets(20));
//         bottomBox.setAlignment(Pos.CENTER);

//         BorderPane root = new BorderPane();
//         root.setTop(mapView);
//         root.setCenter(bottomBox);
//         root.setStyle("-fx-background-color: white; -fx-padding: 10;");

//         Scene scene = new Scene(root, 400, 700);
//         primaryStage.setScene(scene);
//         primaryStage.show();
//     }

//     // Helper method to load images safely from resources
//     private Image loadImage(String path) {
//         try {
//             return new Image(getClass().getResource(path).toExternalForm());
//         } catch (Exception e) {
//             System.out.println("Image not found: " + path);
//             return new Image("https://via.placeholder.com/50"); // Fallback placeholder
//         }
//     }

//     private String getMapHTML() {
//         return """
//             <!DOCTYPE html>
//             <html>
//             <head>
//               <meta charset="utf-8" />
//               <meta name="viewport" content="width=device-width, initial-scale=1.0">
//               <link rel="stylesheet" href="https://unpkg.com/leaflet/dist/leaflet.css" />
//               <style>
//                 #map { height: 100vh; width: 100%; }
//                 html, body { margin: 0; padding: 0; }
//               </style>
//             </head>
//             <body>
//               <div id="map"></div>
//               <script src="https://unpkg.com/leaflet/dist/leaflet.js"></script>
//               <script>
//                 var map = L.map('map').setView([12.0022, 8.5919], 13); // Kano, Nigeria
//                 L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
//                   attribution: '© OpenStreetMap contributors'
//                 }).addTo(map);
//                 // Add courier and destination markers
//                 L.marker([12.05, 8.55]).addTo(map).bindPopup('Courier Location').openPopup();
//                 L.marker([12.00, 8.60]).addTo(map).bindPopup('Destination');
//                 var route = L.polyline([[12.05, 8.55],[12.00, 8.60]], {color:'orange'}).addTo(map);
//               </script>
//             </body>
//             </html>
//         """;
//     }
// }
