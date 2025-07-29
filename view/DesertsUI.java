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

public class DesertsUI{

    public Scene createDessertScene(Stage stage) {

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();
        Label title = new Label("🍹 Welcome to Deserts🍸");
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
            {"Classic Cold Coffee", "₹99", "/assets/images/classiccoldcoffee.jpeg"},
            {"Iced Mocha", "₹129", "/assets/images/icedmocha.jpeg"},
            {"Oreo Cold Coffee", "₹149", "/assets/images/oreomilkshake.jpeg"},
            {"Chocolate Milkshake", "₹139", "/assets/images/choclatemilkshake.jpeg"},
            {"Strawberry Milkshake", "₹129", "/assets/images/strawmishake.jpeg"},
            {"Mango Smoothie", "₹119", "/assets/images/mangosmooth.jpeg"},
            {"Blue Lagoon Mocktail", "₹109", "/assets/images/bluelagoon.jpeg"},
            {"Lemon Iced Tea", "₹89", "/assets/images/lemonicedtea.jpeg"},
            {"Virgin Mojito", "₹99", "/assets/images/mojito.jpeg"},
            {"Watermelon Cooler", "₹89", "/assets/images/watermeloncooler.jpeg"},
            {"Masala Chai", "₹39", "/assets/images/masalachai.jpeg"},
            {"Elaichi Chai", "₹49", "/assets/images/elaichichai.jpeg"},
            {"Cappuccino", "₹109", "/assets/images/cappucino.jpeg"},
            {"Hot Chocolate", "₹129", "/assets/images/hotchoclate.jpeg"},
            {"Green Tea", "₹69", "/assets/images/greentea.jpeg"}
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

        // //Back & Cart Buttons
         Button backButton = new Button("← Back to Menu");
         backButton.setOnAction(e -> new CafeCategories().start(stage));
        backButton.setStyle("-fx-background-color: #e6ccb2; -fx-text-fill: #5c3d2e; -fx-font-weight: bold; -fx-font-size: 16px;");

         Button cartButton = new Button("🛒 Go to Cart");
         cartButton.setOnAction(e -> stage.setScene(new cartPage().createCartScene(stage)));
       cartButton.setStyle("-fx-background-color: #e6ccb2; -fx-text-fill: #5c3d2e; -fx-font-weight: bold; -fx-font-size: 16px;");

        // Button backBtn = new Button("⬅ Back to Menu");
        // backBtn.setAlignment(Pos.TOP_LEFT);
    //     backBtn.setStyle("-fx-background-color: #ffcc80; -fx-font-size: 16px; -fx-font-weight: bold;");
    //     backBtn.setOnAction(e -> {
    //         try {
    //             Home homePage = new Home();
    //             homePage.start(stage);
    //         } catch (Exception ex) {
    //             ex.printStackTrace();
    //         }
    //     });

    //     Button cartButton = new Button("🛒 Go to Cart");
    //     cartButton.setOnAction(e -> stage.setScene(new cartPage().createCartScene(stage)));
    //    cartButton.setAlignment(Pos.TOP_LEFT);
    //   cartButton.setStyle("-fx-background-color: #ffcc80; -fx-font-size: 16px; -fx-font-weight: bold;");
    //  cartButton.setOnAction(e -> {
    //         try {
    //             Home homePage = new Home();
    //             homePage.start(stage);
    //         } catch (Exception ex) {
    //             ex.printStackTrace();
    //         }
    //     });

        // Button cartButton = new Button("🛒 Go to Cart");
        // cartButton.setOnAction(e -> stage.setScene(new cartPage().createCartScene(stage)));
        // cartButton.setStyle("-fx-background-color: orangered; -fx-text-fill: white;" +
        //     "-fx-font-size: 14px; -fx-background-radius: 18; -fx-padding: 8px 18px;");

        // HBox topBar = new HBox(10, backBtn, cartButton);
        // topBar.setAlignment(Pos.TOP_RIGHT);
        // topBar.setPadding(new Insets(0, 20, 0, 0));
HBox topRightButtons = new HBox(10, backButton, cartButton);
topRightButtons.setAlignment(Pos.TOP_RIGHT);
topRightButtons.setPadding(new Insets(10, 30, 0, 0));

VBox root = new VBox(10, topRightButtons, title, mainContent);

        // VBox root = new VBox(20, cartButton, title, mainContent, backBtn);
        // root.setAlignment(Pos.TOP_CENTER);
        // root.setStyle("-fx-background-color: #fff8e1;");
        // VBox.setVgrow(mainContent, Priority.ALWAYS);

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


// package com.project;

// import com.Menu.CafeCategories;
// import com.Shared.cart;
// import com.Shared.cartPage;
// import javafx.animation.ScaleTransition;
// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.Alert;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.ScrollPane;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.*;
// import javafx.scene.paint.Color;
// import javafx.scene.text.Font;
// import javafx.stage.Stage;
// import javafx.util.Duration;

// public class BeveragesMenuUi {

//     public Scene createBeveragesScene(Stage stage) {
//         Label title = new Label("🍹 Welcome to Beverages & Coolers 🍸");
//         title.setFont(Font.font("Arial", 40));
//         title.setTextFill(Color.ORANGERED);
//         title.setPadding(new Insets(30, 0, 20, 0));
//         title.setAlignment(Pos.CENTER);

//         // Category Sidebar
//         VBox categoryBox = new VBox(20);
//         categoryBox.setPadding(new Insets(20));
//         categoryBox.setStyle("-fx-background-color: #fff3e0;");
//         categoryBox.setPrefWidth(240);
//         categoryBox.setAlignment(Pos.TOP_CENTER);

//         Label categoryHeading = new Label("Categories");
//         categoryHeading.setStyle(
//             "-fx-font-size: 30px;" +
//             "-fx-font-weight: bold;" +
//             "-fx-text-fill: darkorange;" +
//             "-fx-underline: true;"
//         );
//         categoryBox.getChildren().add(categoryHeading);

//         String[] categories = {
//             "Pizza", "Pasta", "Burger",
//             "Coffee", "Beverages",
//             "Desserts", "Fries", "Sandwich"
//         };

//         for (String cat : categories) {
//             Button catBtn = new Button(cat);
//             catBtn.setMaxWidth(Double.MAX_VALUE);
//             catBtn.setStyle(
//                 "-fx-background-color: #ffcc80;" +
//                 "-fx-text-fill: #333333;" +
//                 "-fx-font-size: 16px;" +
//                 "-fx-font-weight: bold;" +
//                 "-fx-background-radius: 20;"
//             );

//             ScaleTransition scaleIn = new ScaleTransition(Duration.millis(200), catBtn);
//             scaleIn.setToX(1.05);
//             scaleIn.setToY(1.05);
//             ScaleTransition scaleOut = new ScaleTransition(Duration.millis(200), catBtn);
//             scaleOut.setToX(1);
//             scaleOut.setToY(1);

//             catBtn.setOnMouseEntered(e -> scaleIn.playFromStart());
//             catBtn.setOnMouseExited(e -> scaleOut.playFromStart());

//             categoryBox.getChildren().add(catBtn);
//         }

//         // Menu Items
//         VBox menuContainer = new VBox(40);
//         menuContainer.setPadding(new Insets(50));
//         menuContainer.setStyle("-fx-background-color: #fff8e1;");
//         menuContainer.setAlignment(Pos.TOP_CENTER);

//         String[][] items = {
//             {"Classic Cold Coffee", "₹99", "/assets/images/classiccoldcoffee.jpeg"},
//             {"Iced Mocha", "₹129", "/assets/images/icedmocha.jpeg"},
//             {"Oreo Cold Coffee", "₹149", "/assets/images/oreomilkshake.jpeg"},
//             {"Chocolate Milkshake", "₹139", "/assets/images/choclatemilkshake.jpeg"},
//             {"Strawberry Milkshake", "₹129", "/assets/images/strawmishake.jpeg"},
//             {"Mango Smoothie", "₹119", "/assets/images/mangosmooth.jpeg"},
//             {"Blue Lagoon Mocktail", "₹109", "/assets/images/bluelagoon.jpeg"},
//             {"Lemon Iced Tea", "₹89", "/assets/images/lemonicedtea.jpeg"},
//             {"Virgin Mojito", "₹99", "/assets/images/mojito.jpeg"},
//             {"Watermelon Cooler", "₹89", "/assets/images/watermeloncooler.jpeg"},
//             {"Masala Chai", "₹39", "/assets/images/masalachai.jpeg"},
//             {"Elaichi Chai", "₹49", "/assets/images/elaichichai.jpeg"},
//             {"Cappuccino", "₹109", "/assets/images/cappucino.jpeg"},
//             {"Hot Chocolate", "₹129", "/assets/images/hotchoclate.jpeg"},
//             {"Green Tea", "₹69", "/assets/images/greentea.jpeg"}
//         };

//         for (int i = 0; i < items.length; i++) {
//             boolean imageLeft = i % 2 == 0;
//             menuContainer.getChildren().add(createCard(items[i][0], items[i][1], items[i][2], imageLeft));
//         }

//         ScrollPane scrollPane = new ScrollPane(menuContainer);
//         scrollPane.setFitToWidth(true);
//         scrollPane.setStyle("-fx-background:  #fff8e1;");
//         scrollPane.setPrefViewportHeight(700);

//         // Layout
//         HBox mainContent = new HBox(categoryBox, scrollPane);
//         mainContent.setSpacing(10);
//         HBox.setHgrow(scrollPane, Priority.ALWAYS);

//         // Back & Cart Buttons
//         Button backButton = new Button("← Back to Menu");
//         backButton.setOnAction(e -> new CafeCategories().start(stage));

//         Button cartButton = new Button("🛒 Go to Cart");
//         cartButton.setOnAction(e -> stage.setScene(new cartPage().createCartScene(stage)));

//         VBox root = new VBox(20, cartButton, title, mainContent, backButton);
//         root.setAlignment(Pos.TOP_CENTER);
//         root.setStyle("-fx-background-color: #fff8e1;");
//         VBox.setVgrow(mainContent, Priority.ALWAYS);

//         return new Scene(root, 1000, 800);
//     }

//     private VBox createCard(String name, String priceText, String imagePath, boolean imageOnLeft) {
//         VBox card = new VBox(12);
//         card.setPadding(new Insets(10));
//         card.setSpacing(10);
//         card.setMaxWidth(850);
//         card.setStyle("-fx-background-color: #ece087ff; -fx-background-radius: 20;" +
//             "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 10, 0.2, 0, 6);");

//         Image image;
//         try {
//             image = new Image(getClass().getResourceAsStream(imagePath));
//         } catch (Exception e) {
//             System.out.println("Error loading image: " + imagePath);
//             image = new Image("https://via.placeholder.com/200");
//         }

//         ImageView imageView = new ImageView(image);
//         imageView.setFitWidth(300);
//         imageView.setFitHeight(300);
//         imageView.setPreserveRatio(true);

//         ScaleTransition st = new ScaleTransition(Duration.millis(200), imageView);
//         imageView.setOnMouseEntered(e -> {
//             st.setToX(1.1);
//             st.setToY(1.1);
//             st.playFromStart();
//         });
//         imageView.setOnMouseExited(e -> {
//             st.setToX(1);
//             st.setToY(1);
//             st.playFromStart();
//         });

//         Label nameLabel = new Label(name);
//         nameLabel.setFont(Font.font("Arial", 22));
//         nameLabel.setTextFill(Color.web("#333"));

//         Label priceLabel = new Label(priceText);
//         priceLabel.setFont(Font.font("Arial", 18));
//         priceLabel.setTextFill(Color.FORESTGREEN);

//         // Parse price from text like "₹129"
//         int priceValue = Integer.parseInt(priceText.replaceAll("[^0-9]", ""));

//         Button addButton = new Button("Add Item");
//         addButton.setStyle("-fx-background-color: orangered; -fx-text-fill: white;" +
//             "-fx-font-size: 14px; -fx-background-radius: 18; -fx-padding: 8px 18px;");
//         addButton.setOnAction(e -> {
//             cart.addItem(new cart.Item(name, priceValue));
//             Alert alert = new Alert(Alert.AlertType.INFORMATION, name + " added to cart!");
//             alert.showAndWait();
//         });

//         VBox infoBox = new VBox(10, nameLabel, priceLabel, addButton);
//         infoBox.setAlignment(Pos.CENTER_LEFT);

//         HBox row = new HBox(40);
//         row.setPadding(new Insets(10));
//         row.setAlignment(Pos.CENTER);

//         if (imageOnLeft) {
//             row.getChildren().addAll(imageView, infoBox);
//         } else {
//             row.getChildren().addAll(infoBox, imageView);
//         }

//         card.getChildren().add(row);
//         return card;
//     }

//     public Scene createBeverageScene(Stage stage) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'createBeverageScene'");
//     }
// }


// package com.project;


// import com.Shared.cart;

// import javafx.animation.ScaleTransition;
// import javafx.application.Application;
// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.Alert;
// import javafx.scene.control.Alert.AlertType;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.ScrollPane;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.Priority;
// import javafx.scene.layout.VBox;
// import javafx.scene.paint.Color;
// import javafx.scene.text.Font;
// import javafx.stage.Stage;
// import javafx.util.Duration;

// public class DesertsUI extends Application {

//     public Scene createDessertScene(Stage stage) {
//         VBox root = new VBox();
//         Button backBtn = new Button("← Back");
//         backBtn.setOnAction(e -> {
//             com.Menu.CafeCategories cafe = new com.Menu.CafeCategories();
//             try {
//                 cafe.start(stage);
//             } catch (Exception ex) {
//                 ex.printStackTrace();
//             }
//         });
//         root.getChildren().add(backBtn);
//         return new Scene(root, 1000, 800);
//     }

//     public void start(Stage primaryStage) {
//         Label title = new Label("🍔 Welcome to Pizza Zone 🍟");
//         title.setFont(new Font("Arial", 46));
//         title.setTextFill(Color.ORANGERED);
//         title.setPadding(new Insets(30, 0, 20, 0));
//         title.setAlignment(Pos.CENTER);

//         VBox categoryBox = new VBox(40);
//         categoryBox.setPadding(new Insets(20));
//         categoryBox.setStyle("-fx-background-color: #fff3e0;");
//         categoryBox.setPrefWidth(240);
//         categoryBox.setAlignment(Pos.CENTER);

//         Label categoryHeading = new Label("Categories");
//         categoryHeading.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: darkorange; -fx-padding: 0 0 10 0; -fx-underline: true;");
//         categoryBox.getChildren().add(categoryHeading);

//         String[] categories = {
//             "Pizza", "Pasta", "Burger",
//             "Coffee", "Beverages",
//             "Deserts", "Fries", "Sandwich"
//         };

//         for (String cat : categories) {
//             Button catBtn = new Button(cat);
//             catBtn.setMaxWidth(Double.MAX_VALUE);
//             catBtn.setStyle("-fx-background-color: #ffcc80; -fx-text-fill: #333333; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-radius: 20; -fx-padding: 6x 10px;");
//             ScaleTransition scaleIn = new ScaleTransition(Duration.millis(200), catBtn);
//             scaleIn.setToX(1.05);
//             scaleIn.setToY(1.05);

//             ScaleTransition scaleOut = new ScaleTransition(Duration.millis(200), catBtn);
//             scaleOut.setToX(1);
//             scaleOut.setToY(1);

//             catBtn.setOnMouseEntered(e -> scaleIn.playFromStart());
//             catBtn.setOnMouseExited(e -> scaleOut.playFromStart());

//             categoryBox.getChildren().add(catBtn);
//         }

//         VBox menuContainer = new VBox(40);
//         menuContainer.setPadding(new Insets(50));
//         menuContainer.setStyle("-fx-background-color: #fff8e1;");
//         menuContainer.setAlignment(Pos.TOP_CENTER);

//         String[][] desserts = {
//             {"Chocolate Brownie (with ice cream)", "₹129", "/assets/images/brownie.jpeg"},
//             {"Choco Lava Cake", "₹119", "/assets/images/chocolavajpeg"},
//             {"Oreo Cheesecake Jar", "₹149", "/assets/images/oreock.jpeg"},
//             {"Red Velvet Pastry", "₹139", "/assets/images/redvelvet.jpeg"},
//             {"Classic Tiramisu", "₹149", "/assets/images/tiramisu.jpeg"},
//             {"Fruit Custard Bowl", "₹99", "/assets/images/fruitcustard.jpeg"},
//             {"Ice Cream Sundae", "₹129", "/assets/images/icesundae.jpeg"},
//             {"Strawberry Mousse", "₹109", "/assets/images/strawmouse.jpeg"}
//         };

//         for (int i = 0; i < desserts.length; i++) {
//             boolean imageLeft = i % 2 == 0;
//             menuContainer.getChildren().add(createCard(desserts[i][0], desserts[i][1], desserts[i][2], imageLeft));
//         }

//         ScrollPane scrollPane = new ScrollPane(menuContainer);
//         scrollPane.setFitToWidth(true);
//         scrollPane.setStyle("-fx-background:  #fff8e1;");
//         scrollPane.setPrefViewportHeight(700);

//         HBox mainContent = new HBox(categoryBox, scrollPane);
//         mainContent.setSpacing(10);
//         HBox.setHgrow(scrollPane, Priority.ALWAYS);

//         VBox root = new VBox(title, mainContent);
//         root.setAlignment(Pos.TOP_CENTER);
//         root.setStyle("-fx-background-color: #fff8e1;");
//         VBox.setVgrow(mainContent, Priority.ALWAYS);

//         Scene scene = new Scene(root, 1000, 800);
//         primaryStage.setTitle("Dessert Menu - Categories");
//         primaryStage.setScene(scene);
//         primaryStage.show();
//     }

//     private VBox createCard(String name, String price, String imagePath, boolean imageOnLeft) {
//         VBox card = new VBox(12);
//         card.setPadding(new Insets(10));
//         card.setSpacing(10);
//         card.setMaxWidth(850);
//         card.setStyle("-fx-background-color: #ece087ff; -fx-background-radius: 20; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 10, 0.2, 0, 6);");

//         Image image;
//         try {
//             image = new Image(getClass().getResourceAsStream(imagePath));
//         } catch (Exception e) {
//             System.out.println("Error loading image: " + imagePath);
//             image = new Image("https://via.placeholder.com/200");
//         }

//         ImageView imageView = new ImageView(image);
//         imageView.setFitWidth(300);
//         imageView.setFitHeight(300);
//         imageView.setPreserveRatio(true);

//         ScaleTransition st = new ScaleTransition(Duration.millis(200), imageView);
//         imageView.setOnMouseEntered(e -> {
//             st.setToX(1.1);
//             st.setToY(1.1);
//             st.playFromStart();
//         });
//         imageView.setOnMouseExited(e -> {
//             st.setToX(1);
//             st.setToY(1);
//             st.playFromStart();
//         });

//         Label nameLabel = new Label(name);
//         nameLabel.setFont(Font.font("Arial", 22));
//         nameLabel.setTextFill(Color.web("#333"));

//         Label priceLabel = new Label(price);
//         priceLabel.setFont(Font.font("Arial", 18));
//         priceLabel.setTextFill(Color.FORESTGREEN);

//         Button addButton = new Button("Add Item");
//         addButton.setStyle("-fx-background-color: orangered; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 18; -fx-padding: 8px 18px;");
//         addButton.setOnAction(e -> {
//             int priceInt = Integer.parseInt(price.replaceAll("[^0-9]", ""));
//             cart.addItem(new cart.Item(name, priceInt, priceInt));
//             Alert alert = new Alert(AlertType.INFORMATION, name + " added to cart!");
//             alert.showAndWait();
//         });

//         VBox infoBox = new VBox(10, nameLabel, priceLabel, addButton);
//         infoBox.setAlignment(Pos.CENTER_LEFT);

//         HBox row = new HBox(40);
//         row.setPadding(new Insets(10));
//         row.setAlignment(Pos.CENTER);

//         if (imageOnLeft) {
//             row.getChildren().addAll(imageView, infoBox);
//         } else {
//             row.getChildren().addAll(infoBox, imageView);
//         }

//         card.getChildren().add(row);
//         return card;
//     }

//     public static void main(String[] args) {
//         launch(args);
//     }
// }
