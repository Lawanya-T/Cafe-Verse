package com.cafeverse.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class review extends Application {
     Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();
    private int selectedStars = 0;
    private final Label[] starLabels = new Label[5];

    @Override
    public void start(Stage primaryStage) {
        Image backgroundImage = new Image(getClass().getResource("/assets/images/background_review.png").toExternalForm());
        BackgroundImage bgImage = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(1.0, 1.0, true, true, false, false)
        );

        StackPane root = new StackPane();
        root.setBackground(new Background(bgImage));

        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(20));
        root.getChildren().add(pane);

        Label title = new Label("Review");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        title.setTextFill(Color.DARKSLATEGRAY);
        BorderPane.setAlignment(title, Pos.CENTER);
        pane.setTop(title);

        VBox card = new VBox(15);
        card.setPadding(new Insets(30));
        card.setAlignment(Pos.CENTER);
        card.setMaxWidth(600);
        card.setStyle("-fx-background-color: rgba(255,255,255,0.8); -fx-background-radius: 20;");

        Image profileImg = new Image(getClass().getResource("/assets/images/profile.jpg").toExternalForm(), 80, 80, true, true);
        ImageView profilePic = new ImageView(profileImg);
        Circle clip = new Circle(40, 40, 40);
        profilePic.setClip(clip);

        HBox starsBox = new HBox(5);
        starsBox.setAlignment(Pos.CENTER);
        for (int i = 0; i < 5; i++) {
            Label star = new Label("☆");
            star.setFont(Font.font(28));
            int index = i;
            star.setOnMouseClicked(e -> updateStars(index));
            starLabels[i] = star;
            starsBox.getChildren().add(star);
        }

        TextArea reviewNotes = new TextArea();
        reviewNotes.setPromptText("Write your notes here...");
        reviewNotes.setWrapText(true);
        reviewNotes.setPrefRowCount(4);
        reviewNotes.setMaxHeight(100);
        reviewNotes.setMaxWidth(250);
        reviewNotes.setStyle("-fx-font-size:14px;");

        Button submitBtn = new Button("Submit Review");
        submitBtn.setStyle("-fx-background-color: #ff9a76; -fx-text-fill: white; -fx-font-weight: bold;");
        submitBtn.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thank You");
            alert.setHeaderText("Review Submitted");
            alert.setContentText("Thank you for visiting CafeVerse!!");
            alert.show();

            //Auto-close window after 3 seconds
        //     PauseTransition delay = new PauseTransition(Duration.seconds(3));
        //     delay.setOnFinished(event -> {
        //         alert.close();
        //         primaryStage.close();  // close the review window
        //     });
        //     delay.play();
        });

        Button logoutBtn = new Button("Back To Home");
        logoutBtn.setStyle("-fx-background-color: #cc0000; -fx-text-fill: white; -fx-font-weight: bold;");
        logoutBtn.setOnAction(e -> {
    
            try {
                new Home().start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        card.getChildren().addAll(profilePic, starsBox, reviewNotes, submitBtn, logoutBtn);
        pane.setCenter(card);

        Scene scene = new Scene(root,screenWidth,screenHeight);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Customer Review");
        primaryStage.show();
    }

    private void updateStars(int index) {
        selectedStars = index + 1;
        for (int i = 0; i < 5; i++) {
            starLabels[i].setText(i <= index ? "★" : "☆");
            starLabels[i].setTextFill(i <= index ? Color.GOLD : Color.GRAY);
        }
    }
}

// package com.cafeverse.view;

// import javafx.animation.PauseTransition;
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
// import javafx.scene.shape.Circle;
// import javafx.scene.text.Font;
// import javafx.scene.text.FontWeight;
// import javafx.stage.Screen;
// import javafx.stage.Stage;
// import javafx.util.Duration;

// public class review extends Application {
//      Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
//         double screenWidth = screenBounds.getWidth();
//         double screenHeight = screenBounds.getHeight();
//     private int selectedStars = 0;
//     private final Label[] starLabels = new Label[5];

//     @Override
//     public void start(Stage primaryStage) {
//         Image backgroundImage = new Image(getClass().getResource("/assets/images/background_review.png").toExternalForm());
//         BackgroundImage bgImage = new BackgroundImage(
//                 backgroundImage,
//                 BackgroundRepeat.NO_REPEAT,
//                 BackgroundRepeat.NO_REPEAT,
//                 BackgroundPosition.CENTER,
//                 new BackgroundSize(1.0, 1.0, true, true, false, false)
//         );

//         StackPane root = new StackPane();
//         root.setBackground(new Background(bgImage));

//         BorderPane pane = new BorderPane();
//         pane.setPadding(new Insets(20));
//         root.getChildren().add(pane);

//         Label title = new Label("Review");
//         title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
//         title.setTextFill(Color.DARKSLATEGRAY);
//         BorderPane.setAlignment(title, Pos.CENTER);
//         pane.setTop(title);

//         VBox card = new VBox(15);
//         card.setPadding(new Insets(30));
//         card.setAlignment(Pos.CENTER);
//         card.setMaxWidth(600);
//         card.setStyle("-fx-background-color: rgba(255,255,255,0.8); -fx-background-radius: 20;");

//         Image profileImg = new Image(getClass().getResource("/assets/images/profile.jpg").toExternalForm(), 80, 80, true, true);
//         ImageView profilePic = new ImageView(profileImg);
//         Circle clip = new Circle(40, 40, 40);
//         profilePic.setClip(clip);

//         HBox starsBox = new HBox(5);
//         starsBox.setAlignment(Pos.CENTER);
//         for (int i = 0; i < 5; i++) {
//             Label star = new Label("☆");
//             star.setFont(Font.font(28));
//             int index = i;
//             star.setOnMouseClicked(e -> updateStars(index));
//             starLabels[i] = star;
//             starsBox.getChildren().add(star);
//         }

//         TextArea reviewNotes = new TextArea();
//         reviewNotes.setPromptText("Write your notes here...");
//         reviewNotes.setWrapText(true);
//         reviewNotes.setPrefRowCount(4);
//         reviewNotes.setMaxHeight(100);
//         reviewNotes.setMaxWidth(250);
//         reviewNotes.setStyle("-fx-font-size:14px;");

//         Button submitBtn = new Button("Submit Review");
//         submitBtn.setStyle("-fx-background-color: #ff9a76; -fx-text-fill: white; -fx-font-weight: bold;");
//         submitBtn.setOnAction(e -> {
//             Alert alert = new Alert(Alert.AlertType.INFORMATION);
//             alert.setTitle("Thank You");
//             alert.setHeaderText("Review Submitted");
//             alert.setContentText("Thank you for visiting CafeVerse!!");
//             alert.show();

//             // Auto-close window after 3 seconds
//             PauseTransition delay = new PauseTransition(Duration.seconds(3));
//             delay.setOnFinished(event -> {
//                 alert.close();
//                 primaryStage.close();  // close the review window
//             });
//             delay.play();
//         });

//         Button logoutBtn = new Button("Logout");
//         logoutBtn.setStyle("-fx-background-color: #cc0000; -fx-text-fill: white; -fx-font-weight: bold;");
//         logoutBtn.setOnAction(e -> {
//             primaryStage.close();
//         });

//         card.getChildren().addAll(profilePic, starsBox, reviewNotes, submitBtn, logoutBtn);
//         pane.setCenter(card);

//         Scene scene = new Scene(root,screenWidth,screenHeight);
//         primaryStage.setScene(scene);
//         primaryStage.setTitle("Customer Review");
//         primaryStage.show();
//     }

//     private void updateStars(int index) {
//         selectedStars = index + 1;
//         for (int i = 0; i < 5; i++) {
//             starLabels[i].setText(i <= index ? "★" : "☆");
//             starLabels[i].setTextFill(i <= index ? Color.GOLD : Color.GRAY);
//         }
//     }

//     public static void main(String[] args) {
//         launch(args);
//     }
// }


// // package com.project;

// // import javafx.application.Application;
// // import javafx.geometry.Insets;
// // import javafx.geometry.Pos;
// // import javafx.scene.Scene;
// // import javafx.scene.control.Alert;
// // import javafx.scene.control.Button;
// // import javafx.scene.control.Label;
// // import javafx.scene.control.TextArea;
// // import javafx.scene.image.Image;
// // import javafx.scene.image.ImageView;
// // import javafx.scene.layout.*;
// // import javafx.scene.paint.Color;
// // import javafx.scene.shape.Circle;
// // import javafx.scene.text.Font;
// // import javafx.scene.text.FontWeight;
// // import javafx.stage.Stage;

// // public class review extends Application {
// //     private int selectedStars = 0;
// //     private final Label[] starLabels = new Label[5];

// //     @Override
// //     public void start(Stage primaryStage) {
// //         Image backgroundImage = new Image(getClass().getResource("/assets/images/backgroundimage.jpg").toExternalForm());
// //         BackgroundImage bgImage = new BackgroundImage(
// //                 backgroundImage,
// //                 BackgroundRepeat.NO_REPEAT,
// //                 BackgroundRepeat.NO_REPEAT,
// //                 BackgroundPosition.CENTER,
// //                 new BackgroundSize(1.0, 1.0, true, true, false, false)
// //         );

// //         StackPane root = new StackPane();
// //         root.setBackground(new Background(bgImage));

// //         BorderPane pane = new BorderPane();
// //         pane.setPadding(new Insets(20));
// //         root.getChildren().add(pane);

// //         Label title = new Label("Review");
// //         title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
// //         title.setTextFill(Color.DARKSLATEGRAY);
// //         BorderPane.setAlignment(title, Pos.CENTER);
// //         pane.setTop(title);

// //         VBox card = new VBox(15);
// //         card.setPadding(new Insets(30));
// //         card.setAlignment(Pos.CENTER);
// //         card.setMaxWidth(600);
// //         card.setStyle("-fx-background-color: rgba(255,255,255,0.8); -fx-background-radius: 20;");

// //         Image profileImg = new Image(getClass().getResource("/assets/images/profile.jpg").toExternalForm(), 80, 80, true, true);
// //         ImageView profilePic = new ImageView(profileImg);
// //         Circle clip = new Circle(40, 40, 40);
// //         profilePic.setClip(clip);

// //         HBox starsBox = new HBox(5);
// //         starsBox.setAlignment(Pos.CENTER);
// //         for (int i = 0; i < 5; i++) {
// //             Label star = new Label("☆");
// //             star.setFont(Font.font(28));
// //             int index = i;
// //             star.setOnMouseClicked(e -> updateStars(index));
// //             starLabels[i] = star;
// //             starsBox.getChildren().add(star);
// //         }

// //         TextArea reviewNotes = new TextArea();
// //         reviewNotes.setPromptText("Write your notes here...");
// //         reviewNotes.setWrapText(true);
// //         reviewNotes.setPrefRowCount(4);
// //         reviewNotes.setMaxHeight(100);
// //         reviewNotes.setMaxWidth(250);
// //         reviewNotes.setStyle("-fx-font-size:14px;");

// //         // ✅ Submit Review button with custom popup
// //         Button submitBtn = new Button("Submit Review");
// //         submitBtn.setStyle("-fx-background-color: #ff9a76; -fx-text-fill: white; -fx-font-weight: bold;");
// //         submitBtn.setOnAction(e -> {
// //             Alert alert = new Alert(Alert.AlertType.INFORMATION, "Thank you for visiting CafeVerse!!");
// //             alert.setHeaderText("Review Submitted");
// //             alert.showAndWait();
// //         });

// //         // ✅ Logout button
// //         Button logoutBtn = new Button("Logout");
// //         logoutBtn.setStyle("-fx-background-color: #cc0000; -fx-text-fill: white; -fx-font-weight: bold;");
// //         logoutBtn.setOnAction(e -> {
// //             primaryStage.close();
// //         });

// //         card.getChildren().addAll(profilePic, starsBox, reviewNotes, submitBtn, logoutBtn);
// //         pane.setCenter(card);

// //         Scene scene = new Scene(root, 600, 500);
// //         primaryStage.setScene(scene);
// //         primaryStage.setTitle("Customer Review");
// //         primaryStage.show();
// //     }

// //     private void updateStars(int index) {
// //         selectedStars = index + 1;
// //         for (int i = 0; i < 5; i++) {
// //             starLabels[i].setText(i <= index ? "★" : "☆");
// //             starLabels[i].setTextFill(i <= index ? Color.GOLD : Color.GRAY);
// //         }
// //     }

// //     public static void main(String[] args) {
// //         launch(args);
// //     }
// // }

// // // package com.project;

// // // import javafx.application.Application;
// // // import javafx.geometry.Insets;
// // // import javafx.geometry.Pos;
// // // import javafx.scene.Scene;
// // // import javafx.scene.control.Alert;
// // // import javafx.scene.control.Button;
// // // import javafx.scene.control.Label;
// // // import javafx.scene.control.TextArea;
// // // import javafx.scene.image.Image;
// // // import javafx.scene.image.ImageView;
// // // import javafx.scene.layout.*;
// // // import javafx.scene.paint.Color;
// // // import javafx.scene.shape.Circle;
// // // import javafx.scene.text.Font;
// // // import javafx.scene.text.FontWeight;
// // // import javafx.stage.Stage;

// // // public class review extends Application {
// // //     private int selectedStars = 0;
// // //     private final Label[] starLabels = new Label[5];

// // //     @Override
// // //     public void start(Stage primaryStage) {
// // //         // ✅ Use a valid image file name without spaces or parentheses
// // //         Image backgroundImage = new Image(getClass().getResource("/assets/images/background_review.png").toExternalForm());
// // //         BackgroundImage bgImage = new BackgroundImage(
// // //                 backgroundImage,
// // //                 BackgroundRepeat.NO_REPEAT,
// // //                 BackgroundRepeat.NO_REPEAT,
// // //                 BackgroundPosition.CENTER,
// // //                 new BackgroundSize(1.0, 1.0, true, true, false, false)
// // //         );

// // //         StackPane root = new StackPane();
// // //         root.setBackground(new Background(bgImage));

// // //         BorderPane pane = new BorderPane();
// // //         pane.setPadding(new Insets(20));
// // //         root.getChildren().add(pane);

// // //         Label title = new Label("Review");
// // //         title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
// // //         title.setTextFill(Color.DARKSLATEGRAY);
// // //         BorderPane.setAlignment(title, Pos.CENTER);
// // //         pane.setTop(title);

// // //         VBox card = new VBox(15);
// // //         card.setPadding(new Insets(30));
// // //         card.setAlignment(Pos.CENTER);
// // //         card.setMaxWidth(600);
// // //         card.setStyle("-fx-background-color: rgba(255,255,255,0.8); -fx-background-radius: 20;");

// // //         // ✅ Load profile image safely
// // //         Image profileImg = new Image(getClass().getResource("/assets/images/profile.jpg").toExternalForm(), 80, 80, true, true);
// // //         ImageView profilePic = new ImageView(profileImg);
// // //         Circle clip = new Circle(40, 40, 40);
// // //         profilePic.setClip(clip);

// // //         HBox starsBox = new HBox(5);
// // //         starsBox.setAlignment(Pos.CENTER);
// // //         for (int i = 0; i < 5; i++) {
// // //             Label star = new Label("☆");
// // //             star.setFont(Font.font(28));
// // //             int index = i;
// // //             star.setOnMouseClicked(e -> updateStars(index));
// // //             starLabels[i] = star;
// // //             starsBox.getChildren().add(star);
// // //         }

// // //         TextArea reviewNotes = new TextArea();
// // //         reviewNotes.setPromptText("Write your notes here...");
// // //         reviewNotes.setWrapText(true);
// // //         reviewNotes.setPrefRowCount(4);
// // //         reviewNotes.setMaxHeight(100);
// // //         reviewNotes.setMaxWidth(250);
// // //         reviewNotes.setStyle("-fx-font-size:14px;");

// // //         Button submitBtn = new Button("Submit Review");
// // //         submitBtn.setStyle("-fx-background-color: #ff9a76; -fx-text-fill: white; -fx-font-weight: bold;");
// // //         submitBtn.setOnAction(e -> {
// // //             Alert alert = new Alert(Alert.AlertType.INFORMATION, "Thank you for your response!");
// // //             alert.showAndWait();
// // //         });

// // //         card.getChildren().addAll(profilePic, starsBox, reviewNotes, submitBtn);
// // //         pane.setCenter(card);

// // //         Scene scene = new Scene(root, 600, 500);
// // //         primaryStage.setScene(scene);
// // //         primaryStage.setTitle("Customer Review");
// // //         primaryStage.show();
// // //     }

// // //     private void updateStars(int index) {
// // //         selectedStars = index + 1;
// // //         for (int i = 0; i < 5; i++) {
// // //             starLabels[i].setText(i <= index ? "★" : "☆");
// // //             starLabels[i].setTextFill(i <= index ? Color.GOLD : Color.GRAY);
// // //         }
// // //     }

// // //     public static void main(String[] args) {
// // //         launch(args);
// // //     }
// // // }

// // // // package com.project;

// // // // import javafx.application.Application;
// // // // import javafx.geometry.Insets;
// // // // import javafx.geometry.Pos;
// // // // import javafx.scene.Scene;
// // // // import javafx.scene.control.Alert;
// // // // import javafx.scene.control.Button;
// // // // import javafx.scene.control.Label;
// // // // import javafx.scene.control.TextArea;
// // // // import javafx.scene.image.Image;
// // // // import javafx.scene.image.ImageView;
// // // // import javafx.scene.layout.*;
// // // // import javafx.scene.paint.Color;
// // // // import javafx.scene.shape.Circle;
// // // // import javafx.scene.text.Font;
// // // // import javafx.scene.text.FontWeight;
// // // // import javafx.stage.Stage;

// // // // public class review extends Application {
// // // //     private int selectedStars = 0;
// // // //     private final Label[] starLabels = new Label[5];

// // // //     @Override
// // // //     public void start(Stage primaryStage) throws Exception {
// // // //         Image backgroundImage = new Image(getClass().getResource("/assets/images/Untitled design (1).png").toExternalForm());
// // // //         BackgroundImage bgImage = new BackgroundImage(
// // // //                 backgroundImage,
// // // //                 BackgroundRepeat.NO_REPEAT,
// // // //                 BackgroundRepeat.NO_REPEAT,
// // // //                 BackgroundPosition.CENTER,
// // // //                 new BackgroundSize(1.0,1.0,true,true,false,false)
// // // //         );

// // // //         StackPane root = new StackPane();
// // // //         root.setBackground(new Background(bgImage));

// // // //         BorderPane pane = new BorderPane();
// // // //         pane.setPadding(new Insets(20));
// // // //         root.getChildren().add(pane);

// // // //         Label title = new Label("Review");
// // // //         title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
// // // //         title.setTextFill(Color.DARKSLATEGRAY);
// // // //         BorderPane.setAlignment(title, Pos.CENTER);
// // // //         pane.setTop(title);

// // // //         VBox card = new VBox(15);
// // // //         card.setPadding(new Insets(30));
// // // //         card.setAlignment(Pos.CENTER);
// // // //         card.setMaxWidth(600);
// // // //         card.setStyle("-fx-background-color: rgba(255,255,255,0.8); -fx-background-radius: 20;");

// // // //         ImageView profilePic = new ImageView(new Image(
// // // //                 getClass().getResource("/assets/images/profile.jpg").toExternalForm(), 80, 80, true, true));
// // // //         Circle clip = new Circle(40, 40, 40);
// // // //         profilePic.setClip(clip);

// // // //         HBox starsBox = new HBox(5);
// // // //         starsBox.setAlignment(Pos.CENTER);
// // // //         for (int i = 0; i < 5; i++) {
// // // //             Label star = new Label("☆");
// // // //             star.setFont(Font.font(28));
// // // //             int index = i;
// // // //             star.setOnMouseClicked(e -> updateStars(index));
// // // //             starLabels[i] = star;
// // // //             starsBox.getChildren().add(star);
// // // //         }

// // // //         TextArea reviewNotes = new TextArea();
// // // //         reviewNotes.setPromptText("Write your notes here...");
// // // //         reviewNotes.setWrapText(true);
// // // //         reviewNotes.setPrefRowCount(4);
// // // //         reviewNotes.setMaxHeight(100);
// // // //         reviewNotes.setMaxWidth(250);
// // // //         reviewNotes.setStyle("-fx-font-size:14px;");

// // // //         Button submitBtn = new Button("Submit Review");
// // // //         submitBtn.setStyle("-fx-background-color: #ff9a76; -fx-text-fill: white; -fx-font-weight: bold;");
// // // //         submitBtn.setOnAction(e -> {
// // // //             Alert alert = new Alert(Alert.AlertType.INFORMATION, "Thank you for your response!");
// // // //             alert.showAndWait();
// // // //         });

// // // //         card.getChildren().addAll(profilePic, starsBox, reviewNotes, submitBtn);
// // // //         pane.setCenter(card);

// // // //         Scene scene = new Scene(root, 600, 500);
// // // //         primaryStage.setScene(scene);
// // // //         primaryStage.setTitle("Customer Review");
// // // //         primaryStage.show();
// // // //     }

// // // //     private void updateStars(int index) {
// // // //         selectedStars = index + 1;
// // // //         for (int i = 0; i < 5; i++) {
// // // //             starLabels[i].setText(i <= index ? "★" : "☆");
// // // //             starLabels[i].setTextFill(i <= index ? Color.GOLD : Color.GRAY);
// // // //         }
// // // //     }

// // // //     public static void main(String[] args) {
// // // //         launch(args);
// // // //     }
// // // // }
