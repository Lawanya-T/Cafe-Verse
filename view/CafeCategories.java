package com.cafeverse.view;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class CafeCategories extends Application {

    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    double screenWidth = screenBounds.getWidth();
    double screenHeight = screenBounds.getHeight();

    private final String[] items = {"Pizza", "Pasta", "Burger", "Fries", "Sandwich", "Beverages", "Coffee", "Mocktails", "Desserts"};
    private final String[] imagePaths = {
            "assets/images/pizzal.jpg",
            "assets/images/pastal.jpg",
            "assets/images/burgerl.jpg",
            "assets/images/friesl.jpg",
            "assets/images/sandwichl.jpg",
            "assets/images/beverages.jpg",
            "assets/images/coffeel.jpg",
            "assets/images/mocktailsl.jpg",
            "assets/images/dessertsl.jpg"
    };

    private GridPane cardGrid = new GridPane();
    private VBox rightCategories;
    private List<VBox> allCards = new ArrayList<>();
    private TextField searchField;

    @Override
    public void start(Stage primaryStage) {
        VBox leftPane = new VBox(15);
        leftPane.setPadding(new Insets(40, 60, 40, 40));
        leftPane.setAlignment(Pos.TOP_LEFT);
        leftPane.setStyle("-fx-background-color:  #bc934cff;");

        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(2);
        dropShadow.setOffsetY(2);
        dropShadow.setColor(Color.BLACK);

        Label title1 = new Label("FOOD");
        title1.setFont(Font.font("Arial", FontWeight.BOLD, 60));
        title1.setTextFill(Color.WHITE);
        title1.setEffect(dropShadow);

        Label subtitle = new Label("is your good");
        subtitle.setFont(Font.font("Arial", FontWeight.NORMAL, 40));
        subtitle.setTextFill(Color.WHITE);
        subtitle.setEffect(dropShadow);

        Label title2 = new Label("MOOD");
        title2.setFont(Font.font("Arial", FontWeight.BOLD, 60));
        title2.setTextFill(Color.WHITE);
        title2.setEffect(dropShadow);

        ImageView imageView;
        try {
            Image image = new Image("assets/images/logo3l.png");
            imageView = new ImageView(image);
            imageView.setFitWidth(250);
            imageView.setPreserveRatio(true);
        } catch (Exception e) {
            imageView = new ImageView();
        }

        Label tagline = new Label("Made with Love,");
        tagline.setFont(Font.font("Cookie", FontWeight.BOLD, 35));
        tagline.setTextFill(Color.WHITE);
        tagline.setEffect(dropShadow);

        Label tagline2 = new Label("Served with Joy!");
        tagline2.setFont(Font.font("Cookie", FontWeight.BOLD, 35));
        tagline2.setTextFill(Color.WHITE);
        tagline2.setEffect(dropShadow);

        leftPane.getChildren().addAll(title1, subtitle, title2, imageView, tagline, tagline2);
        leftPane.setPrefWidth(400);

        rightCategories = new VBox(30);
        rightCategories.setPadding(new Insets(40));
        rightCategories.setAlignment(Pos.TOP_CENTER);
        rightCategories.setStyle("-fx-background-color: #fff3e0; -fx-border-color: #937018ff; -fx-border-width: 3; -fx-background-radius: 10;");

         MenuItem homeItem = new MenuItem("Home");
         homeItem.setOnAction(e -> {
            try {
                new Home().start(new Stage()); // <<== Navigate to CafeCategories
                primaryStage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        

        MenuItem menuItem = new MenuItem("Table Reservation");

         menuItem.setOnAction(e -> {
            try {
                new TableReservation().start(new Stage()); // <<== Navigate to CafeCategories
                primaryStage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        MenuButton menuButton = new MenuButton("☰ ", null, homeItem, menuItem);
        menuButton.setStyle("-fx-background-color: #e6ccb2; -fx-text-fill: #5c3d2e; -fx-font-weight: bold; -fx-font-size: 16px;");


        HBox menuBox = new HBox(menuButton);
        menuBox.setAlignment(Pos.TOP_RIGHT);
        menuBox.setPadding(new Insets(0, 10, 10, 0));
        rightCategories.getChildren().add(menuBox);


        Label categoryTitle = new Label("\"Crafted with Care, Served with Love.\"");
        categoryTitle.setFont(Font.font("Arial", FontWeight.BOLD, 38));
        categoryTitle.setStyle("-fx-text-fill:#EB5B00");

        Label categoryTitle2 = new Label("CafeVerse Menu");
        categoryTitle2.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        categoryTitle2.setStyle("-fx-text-fill:brown");

        HBox titleWrapper = new HBox(categoryTitle);
        titleWrapper.setAlignment(Pos.CENTER);

        cardGrid.setHgap(20);
        cardGrid.setVgap(20);
        cardGrid.setAlignment(Pos.CENTER);

        generateCards();

        // Node topRightBar = null;
        rightCategories.getChildren().addAll( categoryTitle, categoryTitle2, titleWrapper, cardGrid);

        Label comboTitle = new Label("Why Choose One? Have It All with Our Combos!");
        comboTitle.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        comboTitle.setStyle("-fx-text-fill:#B12C00");
        comboTitle.setPadding(new Insets(30, 0, 10, 0));

        HBox carouselContainer = new HBox(20);
        carouselContainer.setAlignment(Pos.CENTER_LEFT);
        carouselContainer.setPadding(new Insets(10));

        String[] comboImages = {
                "assets/images/Combo2l.jpg",
                "assets/images/Combo12l.png",
                "assets/images/combo3l.png"
        };

        String[] comboNames = {
                "Sizzler Delight Combo",
                "Beverage & Bite Combo",
                "Burger Feast Combo"
        };

        for (int i = 0; i < comboImages.length; i++) {
            VBox comboBox = new VBox(10);
            comboBox.setAlignment(Pos.CENTER);
            try {
                ImageView comboImage = new ImageView(new Image(comboImages[i]));
                comboImage.setFitWidth(700);
                comboImage.setFitHeight(350);
                comboImage.setPreserveRatio(false);
                comboImage.setSmooth(true);

                ScaleTransition st = new ScaleTransition(Duration.millis(200), comboImage);
                comboImage.setOnMouseEntered(e -> {
                    st.setToX(1.05);
                    st.setToY(1.05);
                    st.playFromStart();
                });
                comboImage.setOnMouseExited(e -> {
                    st.setToX(1.0);
                    st.setToY(1.0);
                    st.playFromStart();
                });

                Label comboLabel = new Label(comboNames[i]);
                comboLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));
                comboLabel.setTextFill(Color.web("#5C4033"));

                Button claimBtn = new Button("Click here to pay");
                claimBtn.setStyle("-fx-background-color: #e9573aff; -fx-text-fill: black; -fx-font-weight: bold;");
                int finalI = i;
                claimBtn.setOnAction(e -> {
                    try {
                        new payment().start(new Stage());
                        ((Stage) claimBtn.getScene().getWindow()).close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });

                comboBox.getChildren().addAll(comboImage, comboLabel, claimBtn);
                carouselContainer.getChildren().add(comboBox);
            } catch (Exception e) {
                System.out.println("Combo image not found: " + comboImages[i]);
            }
        }

        ScrollPane comboScrollPane = new ScrollPane(carouselContainer);
        comboScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        comboScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        comboScrollPane.setPannable(true);
        comboScrollPane.setPrefViewportHeight(320);
        comboScrollPane.setMinHeight(420);
        comboScrollPane.setMaxHeight(420);
        comboScrollPane.setFitToHeight(true);
        comboScrollPane.setFitToWidth(false);
        comboScrollPane.setPrefViewportWidth(340);
        comboScrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent;");

        rightCategories.getChildren().addAll(comboTitle, comboScrollPane);

        ScrollPane rightScroll = new ScrollPane();
        rightScroll.setContent(rightCategories);
        rightScroll.setFitToWidth(true);
        rightScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        rightScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        rightScroll.setStyle("-fx-background: #FFF0E0");

        HBox root = new HBox();
        root.getChildren().addAll(leftPane, rightScroll);
        HBox.setHgrow(rightScroll, Priority.ALWAYS);

        Scene scene = new Scene(root, screenWidth, screenHeight);
        primaryStage.setTitle("Café Mood UI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void generateCards() {
        allCards.clear();
        cardGrid.getChildren().clear();
        for (int i = 0; i < items.length; i++) {
            VBox card = createCard(i);
            allCards.add(card);
        }
        updateFilteredCards("");
    }

    private void updateFilteredCards(String filter) {
        cardGrid.getChildren().clear();
        int col = 0, row = 0;
        for (int i = 0; i < items.length; i++) {
            if (items[i].toLowerCase().contains(filter.toLowerCase())) {
                cardGrid.add(allCards.get(i), col, row);
                col++;
                if (col >= 3) {
                    col = 0;
                    row++;
                }
            }
        }
    }

    private VBox createCard(int index) {
        VBox card = new VBox(10);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(15));
        card.setPrefSize(200, 250);
        card.setStyle("-fx-background-color: #ffcc80; -fx-font-color: #8b5e54ff; -fx-background-radius: 10; -fx-cursor: hand; -fx-effect: dropshadow(gaussian, rgba(218, 120, 93, 1), 4, 0, 2, 2);");

        ScaleTransition st = new ScaleTransition(Duration.millis(200), card);
        card.setOnMouseEntered(e -> {
            st.setToX(1.05);
            st.setToY(1.05);
            st.playFromStart();
        });
        card.setOnMouseExited(e -> {
            st.setToX(1.0);
            st.setToY(1.0);
            st.playFromStart();
        });

        ImageView foodImage;
        try {
            foodImage = new ImageView(new Image(imagePaths[index]));
            foodImage.setFitWidth(120);
            foodImage.setFitHeight(120);
            foodImage.setPreserveRatio(true);
        } catch (Exception e) {
            foodImage = new ImageView();
        }

        Label name = new Label(items[index]);
        name.setTextFill(Color.RED);
        name.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        name.setWrapText(true);

        Button orderBtn = new Button("→");
        orderBtn.setStyle("-fx-background-color: #e9573aff; -fx-text-fill: white; -fx-font-weight: bold;");
        orderBtn.setOnAction(event -> {
            Stage stage = (Stage) orderBtn.getScene().getWindow();
            switch (items[index].toLowerCase()) {
                case "burger": stage.setScene(new BurgerMenuUi().createBurgerScene(stage)); break;
                case "beverages": stage.setScene(new BeveragesMenuUi().createBeveragesScene(stage)); break;
                case "mocktails": stage.setScene(new MocktailMenu().createMocktailScene(stage)); break;
                case "pasta": stage.setScene(new pastaUI().createPastaScene(stage)); break;
                case "pizza": stage.setScene(new PizzaMenuUi().createPizzaScene(stage)); break;
                case "desserts": stage.setScene(new DesertsUI().createDessertScene(stage)); break;
                case "fries": stage.setScene(new FriesMenuUi().createFriesScene(stage)); break;
                case "sandwich": stage.setScene(new SandwichMenuui().createSandwichScene(stage)); break;
                case "coffee": stage.setScene(new coffeeMenu().createCoffeeScene(stage)); break;
                default: System.out.println("Ordering " + items[index]);
            }
        });

        card.getChildren().addAll(foodImage, name, orderBtn);
        return card;
    }

    public Parent createMainScene(Stage stage) {
        throw new UnsupportedOperationException("Unimplemented method 'createMainScene'");
    }

    public Parent createScene(Stage homStage) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createScene'");
    }
}

// package com.cafeverse.view;

// import javafx.animation.ScaleTransition;
// import javafx.application.Application;
// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.geometry.Rectangle2D;
// import javafx.scene.Scene;
// import javafx.scene.control.*;
// import javafx.scene.effect.DropShadow;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.*;
// import javafx.scene.paint.Color;
// import javafx.scene.text.Font;
// import javafx.scene.text.FontWeight;
// import javafx.stage.Screen;
// import javafx.stage.Stage;

// import javafx.util.Duration;

// import java.util.ArrayList;
// import java.util.List;

// public class CafeCategories extends Application {

//     Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
//     double screenWidth = screenBounds.getWidth();
//     double screenHeight = screenBounds.getHeight();

//     private final String[] items = {"Pizza", "Pasta", "Burger", "Fries", "Sandwich", "Beverages", "Coffee", "Mocktails", "Desserts"};
//     private final String[] imagePaths = {
//             "assets/images/pizzal.jpg",
//             "assets/images/pastal.jpg",
//             "assets/images/burgerl.jpg",
//             "assets/images/friesl.jpg",
//             "assets/images/sandwichl.jpg",
//             "assets/images/beverages.jpg",
//             "assets/images/coffeel.jpg",
//             "assets/images/mocktailsl.jpg",
//             "assets/images/dessertsl.jpg"
//     };

//     private GridPane cardGrid = new GridPane();
//     private VBox rightCategories;
//     private List<VBox> allCards = new ArrayList<>();
//     private TextField searchField;

//     @Override
//     public void start(Stage primaryStage) {
//         VBox leftPane = new VBox(15);
//         leftPane.setPadding(new Insets(40, 60, 40, 40));
//         leftPane.setAlignment(Pos.TOP_LEFT);
//         leftPane.setStyle("-fx-background-color:  #bc934cff;");

//         DropShadow dropShadow = new DropShadow();
//         dropShadow.setOffsetX(2);
//         dropShadow.setOffsetY(2);
//         dropShadow.setColor(Color.BLACK);

//         Label title1 = new Label("FOOD");
//         title1.setFont(Font.font("Arial", FontWeight.BOLD, 60));
//         title1.setTextFill(Color.WHITE);
//         title1.setEffect(dropShadow);

//         Label subtitle = new Label("is your good");
//         subtitle.setFont(Font.font("Arial", FontWeight.NORMAL, 40));
//         subtitle.setTextFill(Color.WHITE);
//         subtitle.setEffect(dropShadow);

//         Label title2 = new Label("MOOD");
//         title2.setFont(Font.font("Arial", FontWeight.BOLD, 60));
//         title2.setTextFill(Color.WHITE);
//         title2.setEffect(dropShadow);

//         ImageView imageView;
//         try {
//             Image image = new Image("assets/images/logo3l.png");
//             imageView = new ImageView(image);
//             imageView.setFitWidth(250);
//             imageView.setPreserveRatio(true);
//         } catch (Exception e) {
//             imageView = new ImageView();
//         }

//         Label tagline = new Label("Made with Love,");
//         tagline.setFont(Font.font("Cookie", FontWeight.BOLD, 35));
//         tagline.setTextFill(Color.WHITE);
//         tagline.setEffect(dropShadow);

//         Label tagline2 = new Label("Served with Joy!");
//         tagline2.setFont(Font.font("Cookie", FontWeight.BOLD, 35));
//         tagline2.setTextFill(Color.WHITE);
//         tagline2.setEffect(dropShadow);

//         leftPane.getChildren().addAll(title1, subtitle, title2, imageView, tagline, tagline2);
//         leftPane.setPrefWidth(400);

//         rightCategories = new VBox(30);
//         rightCategories.setPadding(new Insets(40));
//         rightCategories.setAlignment(Pos.TOP_CENTER);
//         rightCategories.setStyle("-fx-background-color: #fff3e0; -fx-border-color: #937018ff; -fx-border-width: 3; -fx-background-radius: 10;");

//         MenuButton menuButton = new MenuButton("☰ Menu");
//         menuButton.setStyle("-fx-background-color: #e6ccb2; -fx-text-fill: #5c3d2e; -fx-font-weight: bold; -fx-font-size: 18px;");

//         MenuItem homeItem = new MenuItem("Home");
//         homeItem.setOnAction(e -> {
//             try {
//                 new Home().start(primaryStage);
//             } catch (Exception ex) {
//                 ex.printStackTrace();
//             }
//         });
//         MenuItem orderItem = new MenuItem("Table Reservation");
//         orderItem.setOnAction(e -> {
//             try {
//                 new UserReservation().start(primaryStage);
//             } catch (Exception ex) {
//                 ex.printStackTrace();
//             }
//         });

//         MenuItem profileItem = new MenuItem("Profile");
//         profileItem.setOnAction(e -> {
//             try {
//                 new profilepage(null).start(primaryStage);
//             } catch (Exception ex) {
//                 ex.printStackTrace();
//             }
//         });

//         menuButton.getItems().addAll(homeItem, orderItem, profileItem);

//         HBox menuBox = new HBox(menuButton);
//         menuBox.setAlignment(Pos.TOP_RIGHT);
//         menuBox.setPadding(new Insets(0, 10, 10, 0));

//         Label categoryTitle = new Label("\"Crafted with Care, Served with Love.\"");
//         categoryTitle.setFont(Font.font("Arial", FontWeight.BOLD, 38));
//         categoryTitle.setStyle("-fx-text-fill:#EB5B00");

//         Label categoryTitle2 = new Label("CafeVerse Menu");
//         categoryTitle2.setFont(Font.font("Arial", FontWeight.BOLD, 28));
//         categoryTitle2.setStyle("-fx-text-fill:brown");

//         HBox titleWrapper = new HBox(categoryTitle);
//         titleWrapper.setAlignment(Pos.CENTER);

//         cardGrid.setHgap(20);
//         cardGrid.setVgap(20);
//         cardGrid.setAlignment(Pos.CENTER);

//         generateCards();

//         // Node topRightBar = null;
//         rightCategories.getChildren().addAll( categoryTitle, categoryTitle2, titleWrapper, cardGrid);

//         Label comboTitle = new Label("Why Choose One? Have It All with Our Combos!");
//         comboTitle.setFont(Font.font("Arial", FontWeight.BOLD, 28));
//         comboTitle.setStyle("-fx-text-fill:#B12C00");
//         comboTitle.setPadding(new Insets(30, 0, 10, 0));

//         HBox carouselContainer = new HBox(20);
//         carouselContainer.setAlignment(Pos.CENTER_LEFT);
//         carouselContainer.setPadding(new Insets(10));

//         String[] comboImages = {
//                 "assets/images/Combo2l.jpg",
//                 "assets/images/Combo12l.png",
//                 "assets/images/combo3l.png"
//         };

//         String[] comboNames = {
//                 "Sizzler Delight Combo",
//                 "Beverage & Bite Combo",
//                 "Burger Feast Combo"
//         };

//         for (int i = 0; i < comboImages.length; i++) {
//             VBox comboBox = new VBox(10);
//             comboBox.setAlignment(Pos.CENTER);
//             try {
//                 ImageView comboImage = new ImageView(new Image(comboImages[i]));
//                 comboImage.setFitWidth(700);
//                 comboImage.setFitHeight(350);
//                 comboImage.setPreserveRatio(false);
//                 comboImage.setSmooth(true);

//                 ScaleTransition st = new ScaleTransition(Duration.millis(200), comboImage);
//                 comboImage.setOnMouseEntered(e -> {
//                     st.setToX(1.05);
//                     st.setToY(1.05);
//                     st.playFromStart();
//                 });
//                 comboImage.setOnMouseExited(e -> {
//                     st.setToX(1.0);
//                     st.setToY(1.0);
//                     st.playFromStart();
//                 });

//                 Label comboLabel = new Label(comboNames[i]);
//                 comboLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));
//                 comboLabel.setTextFill(Color.web("#5C4033"));

//                 Button claimBtn = new Button("Click here to pay");
//                 claimBtn.setStyle("-fx-background-color: #e9573aff; -fx-text-fill: black; -fx-font-weight: bold;");
//                 int finalI = i;
//                 claimBtn.setOnAction(e -> {
//                     try {
//                         new payment().start(new Stage());
//                         ((Stage) claimBtn.getScene().getWindow()).close();
//                     } catch (Exception ex) {
//                         ex.printStackTrace();
//                     }
//                 });

//                 comboBox.getChildren().addAll(comboImage, comboLabel, claimBtn);
//                 carouselContainer.getChildren().add(comboBox);
//             } catch (Exception e) {
//                 System.out.println("Combo image not found: " + comboImages[i]);
//             }
//         }

//         ScrollPane comboScrollPane = new ScrollPane(carouselContainer);
//         comboScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//         comboScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//         comboScrollPane.setPannable(true);
//         comboScrollPane.setPrefViewportHeight(320);
//         comboScrollPane.setMinHeight(420);
//         comboScrollPane.setMaxHeight(420);
//         comboScrollPane.setFitToHeight(true);
//         comboScrollPane.setFitToWidth(false);
//         comboScrollPane.setPrefViewportWidth(340);
//         comboScrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent;");

//         rightCategories.getChildren().addAll(comboTitle, comboScrollPane);

//         ScrollPane rightScroll = new ScrollPane();
//         rightScroll.setContent(rightCategories);
//         rightScroll.setFitToWidth(true);
//         rightScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//         rightScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
//         rightScroll.setStyle("-fx-background: #FFF0E0");

//         HBox root = new HBox();
//         root.getChildren().addAll(leftPane, rightScroll);
//         HBox.setHgrow(rightScroll, Priority.ALWAYS);

//         Scene scene = new Scene(root, screenWidth, screenHeight);
//         primaryStage.setTitle("Café Mood UI");
//         primaryStage.setScene(scene);
//         primaryStage.show();
//     }

//     private void generateCards() {
//         allCards.clear();
//         cardGrid.getChildren().clear();
//         for (int i = 0; i < items.length; i++) {
//             VBox card = createCard(i);
//             allCards.add(card);
//         }
//         updateFilteredCards("");
//     }

//     private void updateFilteredCards(String filter) {
//         cardGrid.getChildren().clear();
//         int col = 0, row = 0;
//         for (int i = 0; i < items.length; i++) {
//             if (items[i].toLowerCase().contains(filter.toLowerCase())) {
//                 cardGrid.add(allCards.get(i), col, row);
//                 col++;
//                 if (col >= 3) {
//                     col = 0;
//                     row++;
//                 }
//             }
//         }
//     }

//     private VBox createCard(int index) {
//         VBox card = new VBox(10);
//         card.setAlignment(Pos.CENTER);
//         card.setPadding(new Insets(15));
//         card.setPrefSize(200, 250);
//         card.setStyle("-fx-background-color: #ffcc80; -fx-font-color: #8b5e54ff; -fx-background-radius: 10; -fx-cursor: hand; -fx-effect: dropshadow(gaussian, rgba(218, 120, 93, 1), 4, 0, 2, 2);");

//         ScaleTransition st = new ScaleTransition(Duration.millis(200), card);
//         card.setOnMouseEntered(e -> {
//             st.setToX(1.05);
//             st.setToY(1.05);
//             st.playFromStart();
//         });
//         card.setOnMouseExited(e -> {
//             st.setToX(1.0);
//             st.setToY(1.0);
//             st.playFromStart();
//         });

//         ImageView foodImage;
//         try {
//             foodImage = new ImageView(new Image(imagePaths[index]));
//             foodImage.setFitWidth(120);
//             foodImage.setFitHeight(120);
//             foodImage.setPreserveRatio(true);
//         } catch (Exception e) {
//             foodImage = new ImageView();
//         }

//         Label name = new Label(items[index]);
//         name.setTextFill(Color.RED);
//         name.setFont(Font.font("Arial", FontWeight.BOLD, 22));
//         name.setWrapText(true);

//         Button orderBtn = new Button("→");
//         orderBtn.setStyle("-fx-background-color: #e9573aff; -fx-text-fill: white; -fx-font-weight: bold;");
//         orderBtn.setOnAction(event -> {
//             Stage stage = (Stage) orderBtn.getScene().getWindow();
//             switch (items[index].toLowerCase()) {
//                 case "burger": stage.setScene(new BurgerMenuUi().createBurgerScene(stage)); break;
//                 case "beverages": stage.setScene(new BeveragesMenuUi().createBeveragesScene(stage)); break;
//                 case "mocktails": stage.setScene(new MocktailMenu().createMocktailScene(stage)); break;
//                 case "pasta": stage.setScene(new pastaUI().createPastaScene(stage)); break;
//                 case "pizza": stage.setScene(new PizzaMenuUi().createPizzaScene(stage)); break;
//                 case "desserts": stage.setScene(new DesertsUI().createDessertScene(stage)); break;
//                 case "fries": stage.setScene(new FriesMenuUi().createFriesScene(stage)); break;
//                 case "sandwich": stage.setScene(new SandwichMenuui().createSandwichScene(stage)); break;
//                 case "coffee": stage.setScene(new coffeeMenu().createCoffeeScene(stage)); break;
//                 default: System.out.println("Ordering " + items[index]);
//             }
//         });

//         card.getChildren().addAll(foodImage, name, orderBtn);
//         return card;
//     }

//     public Scene createMainScene(Stage stage) {
//         throw new UnsupportedOperationException("Unimplemented method 'createMainScene'");
//     }
// }

// package com.cafeverse.view;

// import com.cafeverse.*;

// import javafx.animation.ScaleTransition;
// import javafx.application.Application;
// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.geometry.Rectangle2D;
// import javafx.scene.Scene;
// import javafx.scene.control.*;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.*;
// import javafx.scene.paint.Color;
// import javafx.scene.text.Font;
// import javafx.scene.text.FontWeight;
// import javafx.stage.Screen;
// import javafx.stage.Stage;

// import javafx.util.Duration;

// import java.util.ArrayList;
// import java.util.List;

// public class CafeCategories extends Application {

//     Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
//     double screenWidth = screenBounds.getWidth();
//     double screenHeight = screenBounds.getHeight();


//     private final String[] items = {"Pizza", "Pasta", "Burger", "Fries", "Sandwich", "Beverages", "Coffee", "Mocktails", "Desserts"};
//     private final String[] imagePaths = {
//             "assets/images/pizzal.jpg",
//             "assets/images/pastal.jpg",
//             "assets/images/burgerl.jpg",
//             "assets/images/friesl.jpg",
//             "assets/images/sandwichl.jpg",
//             "assets/images/beveragesl.jpg",
//             "assets/images/coffeel.jpg",
//             "assets/images/mocktailsl.jpg",
//             "assets/images/dessertsl.jpg"
//     };

//     private GridPane cardGrid = new GridPane();
//     private VBox rightCategories;
//     private List<VBox> allCards = new ArrayList<>();
//     private TextField searchField;

//     @Override
//     public void start(Stage primaryStage) {
//         VBox leftPane = new VBox(15);
//         leftPane.setPadding(new Insets(40, 60, 40, 40));
//         leftPane.setAlignment(Pos.TOP_LEFT);
//         leftPane.setStyle("-fx-background-color:  #bc934cff;");

//         Label title1 = new Label("FOOD");
//         title1.setFont(Font.font("Arial", FontWeight.BOLD, 60));
//         title1.setTextFill(Color.WHITE);

//         Label subtitle = new Label("is your good");
//         subtitle.setFont(Font.font("Arial", FontWeight.NORMAL, 40));
//         subtitle.setTextFill(Color.WHITE);

//         Label title2 = new Label("MOOD");
//         title2.setFont(Font.font("Arial", FontWeight.BOLD, 60));
//         title2.setTextFill(Color.WHITE);

//         ImageView imageView;
//         try {
//             Image image = new Image("Assets/image/logo.jpg");
//             imageView = new ImageView(image);
//             imageView.setFitWidth(350);
//             imageView.setPreserveRatio(true);
//         } catch (Exception e) {
//             imageView = new ImageView();
//         }

//         Button nextBtn = new Button("next →");
//         nextBtn.setStyle("-fx-background-color: #98776bff; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size:16px; -fx-background-radius: 15; -fx-border-radius: 15");
//         nextBtn.setPrefSize(75, 40);

//         leftPane.getChildren().addAll(title1, subtitle, title2, imageView, nextBtn);
//         leftPane.setPrefWidth(400);

//         rightCategories = new VBox(30);
//         rightCategories.setPadding(new Insets(40));
//         rightCategories.setAlignment(Pos.TOP_CENTER);
//         rightCategories.setStyle("-fx-background-color: #fff3e0; -fx-border-color: #937018ff; -fx-border-width: 3; -fx-background-radius: 10;");

//         MenuButton menuButton = new MenuButton("☰ Menu");
//         menuButton.setStyle("-fx-background-color: #e6ccb2; -fx-text-fill: #5c3d2e; -fx-font-weight: bold; -fx-font-size: 18px;");

//         MenuItem homeItem = new MenuItem("Home");
//         homeItem.setOnAction(e -> {
//             try {
//                 new Home().start(primaryStage); // ✅ Navigate back to Home
//             } catch (Exception ex) {
//                 ex.printStackTrace();
//             }
//         });
//         MenuItem orderItem = new MenuItem("My Orders");
//         MenuItem profileItem = new MenuItem("Profile");

//         // ✅ Back to Home
//         // homeItem.setOnAction(e -> {
//         //     try {
//         //         com.Home.Home home = new com.Home.Home();
//         //         home.start(primaryStage);
//         //     } catch (Exception ex) {
//         //         ex.printStackTrace();
//         //     }
//         // });

       

//         menuButton.getItems().addAll(homeItem, orderItem, profileItem);

//         HBox menuBox = new HBox(menuButton);
//         menuBox.setAlignment(Pos.TOP_RIGHT);
//         menuBox.setPadding(new Insets(0, 10, 10, 0));

//         Label categoryTitle = new Label("\"Crafted with Care, Served with Love.\"");
//         categoryTitle.setFont(Font.font("Arial", FontWeight.BOLD, 38));
//         categoryTitle.setStyle("-fx-text-fill:#EB5B00");

//         Label categoryTitle2 = new Label("CafeVerse Menu");
//         categoryTitle2.setFont(Font.font("Arial", FontWeight.BOLD, 28));
//         categoryTitle2.setStyle("-fx-text-fill:brown");

//         HBox titleWrapper = new HBox(categoryTitle);
//         titleWrapper.setAlignment(Pos.CENTER);

//         searchField = new TextField();
//         searchField.setPromptText("Search items...");
//         searchField.textProperty().addListener((obs, oldText, newText) -> updateFilteredCards(newText));

//         cardGrid.setHgap(20);
//         cardGrid.setVgap(20);
//         cardGrid.setAlignment(Pos.CENTER);

//         generateCards();

//         rightCategories.getChildren().addAll(menuBox, categoryTitle, categoryTitle2, titleWrapper);
//         rightCategories.getChildren().add(cardGrid);

//         HBox root = new HBox();
//         root.getChildren().addAll(leftPane, rightCategories);
//         HBox.setHgrow(rightCategories, Priority.ALWAYS);

//         ScrollPane scrollPane = new ScrollPane(root);
//         scrollPane.setFitToWidth(true);
//         scrollPane.setFitToHeight(true);
//         scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//         scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
//         scrollPane.setStyle("-fx-background: #FFF0E0");

//         Scene scene = new Scene(scrollPane, screenWidth, screenHeight);
//         primaryStage.setTitle("Café Mood UI");
//         primaryStage.setScene(scene);
//         primaryStage.show();
//     }

//     private void generateCards() {
//         allCards.clear();
//         cardGrid.getChildren().clear();
//         for (int i = 0; i < items.length; i++) {
//             VBox card = createCard(i);
//             allCards.add(card);
//         }
//         updateFilteredCards("");
//     }

//     private void updateFilteredCards(String filter) {
//         cardGrid.getChildren().clear();
//         int col = 0, row = 0;
//         for (int i = 0; i < items.length; i++) {
//             if (items[i].toLowerCase().contains(filter.toLowerCase())) {
//                 cardGrid.add(allCards.get(i), col, row);
//                 col++;
//                 if (col >= 3) {
//                     col = 0;
//                     row++;
//                 }
//             }
//         }
//     }

//     private VBox createCard(int index) {
//         VBox card = new VBox(10);
//         card.setAlignment(Pos.CENTER);
//         card.setPadding(new Insets(15));
//         card.setPrefSize(200, 250);
//         card.setStyle("-fx-background-color: #ffcc80; -fx-font-color: #8b5e54ff; -fx-background-radius: 10; -fx-cursor: hand; -fx-effect: dropshadow(gaussian, rgba(218, 120, 93, 1), 4, 0, 2, 2);");

//         ScaleTransition st = new ScaleTransition(Duration.millis(200), card);
//         card.setOnMouseEntered(e -> {
//             st.setToX(1.05);
//             st.setToY(1.05);
//             st.playFromStart();
//         });
//         card.setOnMouseExited(e -> {
//             st.setToX(1.0);
//             st.setToY(1.0);
//             st.playFromStart();
//         });

//         ImageView foodImage;
//         try {
//             foodImage = new ImageView(new Image(imagePaths[index]));
//             foodImage.setFitWidth(120);
//             foodImage.setFitHeight(120);
//             foodImage.setPreserveRatio(true);
//         } catch (Exception e) {
//             foodImage = new ImageView();
//         }

//         Label name = new Label(items[index]);
//         name.setTextFill(Color.RED);
//         name.setFont(Font.font("Arial", FontWeight.BOLD, 22));
//         name.setWrapText(true);

//         Button orderBtn = new Button("→");
//         orderBtn.setStyle("-fx-background-color: #e9573aff; -fx-text-fill: white; -fx-font-weight: bold;");
//         orderBtn.setOnAction(event -> {
//             Stage stage = (Stage) orderBtn.getScene().getWindow();
//             switch (items[index].toLowerCase()) {
//                 case "burger": stage.setScene(new BurgerMenuUi().createBurgerScene(stage)); break;
               
                
//                 case "beverages": stage.setScene(new BeveragesMenuUi().createBeveragesScene(stage)); break;
//                 case "mocktails": stage.setScene(new MocktailMenu().createMocktailScene(stage)); break;
//                 case "pasta": stage.setScene(new pastaUI().createPastaScene(stage)); break;
//                  case "pizza": stage.setScene(new PizzaMenuUi().createPizzaScene(stage)); break;
//                 case "desserts": stage.setScene(new DesertsUI().createDessertScene(stage)); break;
//                 case "fries": stage.setScene(new FriesMenuUi().createFriesScene(stage)); break;
//                 case "sandwich": stage.setScene(new SandwichMenuui().createSandwichScene(stage)); break;
//                 case "coffee": stage.setScene(new coffeeMenu().createCoffeeScene(stage));break;
//                 default: System.out.println("Ordering " + items[index]);
//             }
//         });

//         card.getChildren().addAll(foodImage, name, orderBtn);
//         return card;
//     }

//     public Scene createMainScene(Stage stage) {
//         throw new UnsupportedOperationException("Unimplemented method 'createMainScene'");
//     }
// }




