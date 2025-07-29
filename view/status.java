// package com.project;

// import com.Home.Home;

// import javafx.animation.*;
// import javafx.application.Application;
// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.geometry.Rectangle2D;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.effect.DropShadow;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.*;
// import javafx.scene.paint.Color;
// import javafx.scene.paint.LinearGradient;
// import javafx.scene.paint.CycleMethod;
// import javafx.scene.paint.Stop;
// import javafx.scene.shape.Circle;
// import javafx.scene.shape.Line;
// import javafx.scene.text.Font;
// import javafx.scene.text.Text;
// import javafx.stage.Screen;
// import javafx.stage.Stage;
// import javafx.util.Duration;

// public class status extends Application {
//      Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
//         double screenWidth = screenBounds.getWidth();
//         double screenHeight = screenBounds.getHeight();

//     private Circle[] circles;
//     private Line[] progressLines;

//     @Override
//     public void start(Stage primaryStage) {
        

        
//         circles = new Circle[4];
//         progressLines = new Line[3];

//         // Glow effect for active step
//         DropShadow glowEffect = new DropShadow(20, Color.LIMEGREEN);
//         glowEffect.setSpread(0.5);

//         // Background image setup
//         Image backgroundImage = new Image("assets\\images\\order.png");
//         ImageView backgroundView = new ImageView(backgroundImage);
//         backgroundView.setPreserveRatio(false); // Fill entire window
//         backgroundView.setFitWidth(950);
//         backgroundView.setFitHeight(450);

//         // Initialize step circles
//         for (int i = 0; i < 4; i++) {
//             circles[i] = new Circle(25);
//             circles[i].setFill(Color.GRAY);
//         }

//         // Initialize connecting lines
//         for (int i = 0; i < 3; i++) {
//             progressLines[i] = new Line(0, 0, 120, 0);
//             progressLines[i].setStroke(Color.GRAY);
//             progressLines[i].setStrokeWidth(6);
//         }

//         // Labels for each step
//         Text[] labels = new Text[]{
//                 new Text("Order Placed"),
//                 new Text("In the Kitchen"),
//                 new Text("On the Way"),
//                 new Text("Delivered")
//         };
//         for (Text label : labels) {
//             label.setFont(Font.font("Arial", 18));
//         }

//         // Arrange the tracker horizontally
//         HBox statusBar = new HBox(15);
//         statusBar.setAlignment(Pos.CENTER);
//         for (int i = 0; i < 4; i++) {
//             VBox stepBox = new VBox(8);
//             stepBox.setAlignment(Pos.CENTER);
//             stepBox.getChildren().addAll(circles[i], labels[i]);
//             statusBar.getChildren().add(stepBox);
//             if (i < 3) {
//                 statusBar.getChildren().add(progressLines[i]);
//             }
//         }

//         // Buttons
//         Button viewOrderBtn = new Button("VIEW ORDER");
//         Button backButton = new Button("Back to Home");
//         //Button backBtn = new Button("← Back");

//         String buttonStyle = "-fx-font-size: 18px; -fx-font-weight: bold; "
//                 + "-fx-padding: 12px 25px; -fx-background-radius: 10; "
//                 + "-fx-cursor: hand;";
//         viewOrderBtn.setStyle(buttonStyle + "-fx-background-color: #ff4c4c; -fx-text-fill: white;");
//        backButton.setStyle(buttonStyle + "-fx-background-color: #333333; -fx-text-fill: white;");
//         //backBtn.setStyle(buttonStyle + "-fx-background-color: #888888; -fx-text-fill: white;");

//         // Hover Effects
//         viewOrderBtn.setOnMouseEntered(e -> viewOrderBtn.setStyle(buttonStyle + "-fx-background-color: #ff1c1c; -fx-text-fill: white;"));
//         viewOrderBtn.setOnMouseExited(e -> viewOrderBtn.setStyle(buttonStyle + "-fx-background-color: #ff4c4c; -fx-text-fill: white;"));

//        backButton.setOnMouseEntered(e ->backButton.setStyle(buttonStyle + "-fx-background-color: #333333; -fx-text-fill: white;"));

//         // backBtn.setOnMouseEntered(e -> backBtn.setStyle(buttonStyle + "-fx-background-color: #666666; -fx-text-fill: white;"));
//         //backBtn.setOnMouseExited(e -> backBtn.setStyle(buttonStyle + "-fx-background-color: #888888; -fx-text-fill: white;"));
//          // Back navigation to Home
//         backButton.setOnAction(e -> {
//             try {
//                 new Home().start(primaryStage);
//             } catch (Exception ex) {
//                 ex.printStackTrace();
//             }
//         });
//         HBox buttonBox = new HBox(25, viewOrderBtn, backButton);
//         buttonBox.setAlignment(Pos.CENTER);
//         buttonBox.setPadding(new Insets(20, 0, 0, 0));

//         // Combine all UI elements
//         VBox rootBox = new VBox(40, statusBar, buttonBox);
//         rootBox.setAlignment(Pos.CENTER);
//         rootBox.setPadding(new Insets(40));

//         // Stack background behind UI
//         StackPane root = new StackPane(backgroundView, rootBox);

//         // Make background scale with window
//         backgroundView.fitWidthProperty().bind(root.widthProperty());
//         backgroundView.fitHeightProperty().bind(root.heightProperty());

//         // Animation Timeline for step progression
//         Timeline timeline = new Timeline();
//         double stepTime = 4; // seconds per step

//         for (int i = 0; i < 4; i++) {
//             int index = i;

//             // Animate circle glow + pulse when active
//             KeyFrame circleFrame = new KeyFrame(Duration.seconds(stepTime * (i + 1)), e -> {
//                 circles[index].setFill(Color.GREEN);
//                 circles[index].setEffect(glowEffect);
//                 pulseCircle(circles[index]);
//             });
//             timeline.getKeyFrames().add(circleFrame);

//             // Animate gradient fill for connecting lines
//             if (i > 0) {
//                 Line line = progressLines[i - 1];
//                 KeyFrame lineFrame = new KeyFrame(Duration.seconds(stepTime * i + stepTime * 0.8), e -> {
//                     animateLineGradient(line);
//                 });
//                 timeline.getKeyFrames().add(lineFrame);
//             }
//         }
//         timeline.play();

//         // Create the scene and show stage
//         Scene scene = new Scene(root, screenWidth, screenHeight);
//         primaryStage.setTitle("Animated Order Tracker");
//         primaryStage.setScene(scene);
//         primaryStage.show();
//     }

//     // Pulse effect for active circle
//     private void pulseCircle(Circle circle) {
//         ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), circle);
//         scaleTransition.setFromX(1.0);
//         scaleTransition.setFromY(1.0);
//         scaleTransition.setToX(1.2);
//         scaleTransition.setToY(1.2);
//         scaleTransition.setAutoReverse(true);
//         scaleTransition.setCycleCount(Animation.INDEFINITE);
//         scaleTransition.play();
//     }

//     // Animate gradient fill for line
//     private void animateLineGradient(Line line) {
//         Stop[] stops = new Stop[]{
//                 new Stop(0, Color.LIMEGREEN),
//                 new Stop(1, Color.GREEN)
//         };
//         LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
//         line.setStroke(gradient);

//         FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), line);
//         fadeTransition.setFromValue(0.2);
//         fadeTransition.setToValue(1.0);
//         fadeTransition.setCycleCount(1);
//         fadeTransition.play();
//     }

//     public static void main(String[] args) {
//         launch(args);
//     }
// }

package com.cafeverse.view;

import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class status extends Application {
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    double screenWidth = screenBounds.getWidth();
    double screenHeight = screenBounds.getHeight();

    private Circle[] circles;
    private Line[] progressLines;

    private Timeline timeline;
    private int timeLeft = 1800;
    private Label timerLabel;
    private int extensionCount = 0;
    private int totalCharges = 0;

    @Override
    public void start(Stage primaryStage) {
        circles = new Circle[4];
        progressLines = new Line[3];
        DropShadow glowEffect = new DropShadow(20, Color.LIMEGREEN);
        glowEffect.setSpread(0.5);

        // Background image
        Image backgroundImage = new Image("assets/images/order.png");
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setPreserveRatio(false);
        backgroundView.setFitWidth(950);
        backgroundView.setFitHeight(450);

        // Status circles and lines
        for (int i = 0; i < 4; i++) {
            circles[i] = new Circle(25);
            circles[i].setFill(Color.GRAY);
        }

        for (int i = 0; i < 3; i++) {
            progressLines[i] = new Line(0, 0, 120, 0);
            progressLines[i].setStroke(Color.GRAY);
            progressLines[i].setStrokeWidth(6);
        }

        Text[] labels = new Text[]{
                new Text("Order Placed"),
                new Text("In the Kitchen"),
                new Text("On the Way"),
                new Text("Delivered")
        };
        for (Text label : labels) {
            label.setFont(Font.font("Arial", 18));
        }

        HBox statusBar = new HBox(15);
        statusBar.setAlignment(Pos.CENTER);
        for (int i = 0; i < 4; i++) {
            VBox stepBox = new VBox(8);
            stepBox.setAlignment(Pos.CENTER);
            stepBox.getChildren().addAll(circles[i], labels[i]);
            statusBar.getChildren().add(stepBox);
            if (i < 3) {
                statusBar.getChildren().add(progressLines[i]);
            }
        }

        Button viewOrderBtn = new Button("Review");
        viewOrderBtn.setOnAction(e ->{
            try {
                new review().start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        Button backButton = new Button("Back to Home");

        String buttonStyle = "-fx-font-size: 18px; -fx-font-weight: bold; "
                + "-fx-padding: 12px 25px; -fx-background-radius: 10; "
                + "-fx-cursor: hand;";
        viewOrderBtn.setStyle(buttonStyle + "-fx-background-color: #ff4c4c; -fx-text-fill: white;");
        backButton.setStyle(buttonStyle + "-fx-background-color: #333333; -fx-text-fill: white;");

        viewOrderBtn.setOnMouseEntered(e -> viewOrderBtn.setStyle(buttonStyle + "-fx-background-color: #ff1c1c; -fx-text-fill: white;"));
        viewOrderBtn.setOnMouseExited(e -> viewOrderBtn.setStyle(buttonStyle + "-fx-background-color: #ff4c4c; -fx-text-fill: white;"));
        backButton.setOnMouseEntered(e -> backButton.setStyle(buttonStyle + "-fx-background-color: #333333; -fx-text-fill: white;"));

        // backButton.setOnAction(e -> {
        //     try {
        //         new Home().start(primaryStage);
        //     } catch (Exception ex) {
        //         ex.printStackTrace();
        //     }
        // });

        HBox buttonBox = new HBox(25, viewOrderBtn, backButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(20, 0, 0, 0));

        VBox contentBox = new VBox(40, statusBar, buttonBox);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setPadding(new Insets(40));

        // TIMER below image, right-aligned
        timerLabel = new Label("Time Left: " + timeLeft + "s");
        timerLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: green;");

        // Button placeOrderBtn = new Button("Start Timer");
        // placeOrderBtn.setStyle("-fx-font-size: 14px;");
        // placeOrderBtn.setOnAction(e -> startTimer());

        Label noteLabel = new Label("//Note: As soon as you click on Start Timer button your timer will start");
        noteLabel.setTextFill(Color.RED);
        noteLabel.setStyle("-fx-font-size: 13px;");

        VBox timerBox = new VBox(10, timerLabel, noteLabel);
        timerBox.setAlignment(Pos.BOTTOM_RIGHT);
        timerBox.setPadding(new Insets(0, 30, 30, 0));

        // Top section with image and status
        StackPane topImageStack = new StackPane(backgroundView, contentBox);
        topImageStack.setPrefHeight(500);
        backgroundView.fitWidthProperty().bind(topImageStack.widthProperty());
        backgroundView.fitHeightProperty().bind(topImageStack.heightProperty());

        // Root layout
        BorderPane root = new BorderPane();
        root.setTop(topImageStack);
        root.setRight(timerBox);

        Scene scene = new Scene(root, screenWidth, screenHeight);
        primaryStage.setTitle("Order Tracker with Timer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
        // Tracker animation
//         Timeline trackerTimeline = new Timeline();
//         double stepTime = 4;
//         for (int i = 0; i < 4; i++) {
//             int index = i;
//             KeyFrame circleFrame = new KeyFrame(Duration.seconds(stepTime * (i + 1)), e -> {
//                 circles[index].setFill(Color.GREEN);
//                 Effect glowEffect;
//                 circles[index].setEffect(glowEffect);
//                 pulseCircle(circles[index]);
//             });
//             trackerTimeline.getKeyFrames().add(circleFrame);

//             if (i > 0) {
//                 Line line = progressLines[i - 1];
//                 KeyFrame lineFrame = new KeyFrame(Duration.seconds(stepTime * i + stepTime * 0.8), e -> {
//                     animateLineGradient(line);
//                 });
//                 trackerTimeline.getKeyFrames().add(lineFrame);
//             }
//         }
//         trackerTimeline.play();
// }



    private void pulseCircle(Circle circle) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), circle);
        scaleTransition.setFromX(1.0);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToX(1.2);
        scaleTransition.setToY(1.2);
        scaleTransition.setAutoReverse(true);
        scaleTransition.setCycleCount(Animation.INDEFINITE);
        scaleTransition.play();
    }

    private void animateLineGradient(Line line) {
        Stop[] stops = new Stop[]{
                new Stop(0, Color.LIMEGREEN),
                new Stop(1, Color.GREEN)
        };
        LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
        line.setStroke(gradient);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), line);
        fadeTransition.setFromValue(0.2);
        fadeTransition.setToValue(1.0);
        fadeTransition.setCycleCount(1);
        fadeTransition.play();
        
    }

    // private void startTimer() {
    //     if (timeline != null) {
    //         timeline.stop();
    //     }

    //     timeLeft = 10;
    //     timerLabel.setText("Time Left: " + timeLeft + "s");

    //     timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
    //         timeLeft--;
    //         timerLabel.setText("Time Left: " + timeLeft + "s");

    //         if (timeLeft == 5) {
    //             showAlert("⚠ Reminder", "Only 5 seconds left!");
    //         }

    //         if (timeLeft <= 0) {
    //             timeline.stop();
    //             showTimeUpPopup();
    //         }
    //     }));
    //     timeline.setCycleCount(Timeline.INDEFINITE);
    //     timeline.play();

    //     startTimer();
    // }

    // private void showTimeUpPopup() {
    //     Platform.runLater(() -> {
    //         ChoiceDialog<String> dialog = new ChoiceDialog<>("30 seconds - ₹10",
    //                 "30 seconds - ₹10",
    //                 "1 minute - ₹20",
    //                 "2 minutes - ₹30");

    //         dialog.setTitle("⏰ Time's Up!");
    //         dialog.setHeaderText("Your time has expired.");
    //         dialog.setContentText("Select extra time:");

    //         dialog.showAndWait().ifPresent(choice -> {
    //             switch (choice) {
    //                 case "30 seconds - ₹10":
    //                     timeLeft = 30;
    //                     totalCharges += 10;
    //                     break;
    //                 case "1 minute - ₹20":
    //                     timeLeft = 60;
    //                     totalCharges += 20;
    //                     break;
    //                 case "2 minutes - ₹30":
    //                     timeLeft = 120;
    //                     totalCharges += 30;
    //                     break;
    //                 default:
    //                     return;
    //             }

    //             extensionCount++;
    //             System.out.println("Extension Count: " + extensionCount);
    //             System.out.println("Total Extra Charges: ₹" + totalCharges);

    //             timerLabel.setText("Time Left: " + timeLeft + "s");
    //             timeline.playFromStart();
    //         });
    //     });
    // }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
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

// import javafx.animation.*;
// import javafx.application.Application;
// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.geometry.Rectangle2D;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.effect.DropShadow;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.*;
// import javafx.scene.paint.Color;
// import javafx.scene.paint.LinearGradient;
// import javafx.scene.paint.CycleMethod;
// import javafx.scene.paint.Stop;
// import javafx.scene.shape.Circle;
// import javafx.scene.shape.Line;
// import javafx.scene.text.Font;
// import javafx.scene.text.Text;
// import javafx.stage.Screen;
// import javafx.stage.Stage;
// import javafx.util.Duration;

// public class status extends Application {
//      Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
//         double screenWidth = screenBounds.getWidth();
//         double screenHeight = screenBounds.getHeight();

//     private Circle[] circles;
//     private Line[] progressLines;

//     @Override
//     public void start(Stage primaryStage) {
        

        
//         circles = new Circle[4];
//         progressLines = new Line[3];

//         // Glow effect for active step
//         DropShadow glowEffect = new DropShadow(20, Color.LIMEGREEN);
//         glowEffect.setSpread(0.5);

//         // Background image setup
//         Image backgroundImage = new Image("assets\\images\\order.png");
//         ImageView backgroundView = new ImageView(backgroundImage);
//         backgroundView.setPreserveRatio(false); // Fill entire window
//         backgroundView.setFitWidth(950);
//         backgroundView.setFitHeight(450);

//         // Initialize step circles
//         for (int i = 0; i < 4; i++) {
//             circles[i] = new Circle(25);
//             circles[i].setFill(Color.GRAY);
//         }

//         // Initialize connecting lines
//         for (int i = 0; i < 3; i++) {
//             progressLines[i] = new Line(0, 0, 120, 0);
//             progressLines[i].setStroke(Color.GRAY);
//             progressLines[i].setStrokeWidth(6);
//         }

//         // Labels for each step
//         Text[] labels = new Text[]{
//                 new Text("Order Placed"),
//                 new Text("In the Kitchen"),
//                 new Text("On the Way"),
//                 new Text("Delivered")
//         };
//         for (Text label : labels) {
//             label.setFont(Font.font("Arial", 18));
//         }

//         // Arrange the tracker horizontally
//         HBox statusBar = new HBox(15);
//         statusBar.setAlignment(Pos.CENTER);
//         for (int i = 0; i < 4; i++) {
//             VBox stepBox = new VBox(8);
//             stepBox.setAlignment(Pos.CENTER);
//             stepBox.getChildren().addAll(circles[i], labels[i]);
//             statusBar.getChildren().add(stepBox);
//             if (i < 3) {
//                 statusBar.getChildren().add(progressLines[i]);
//             }
//         }

//         // Buttons
//         Button viewOrderBtn = new Button("VIEW ORDER");
//         Button backButton = new Button("Back to Home");
//         //Button backBtn = new Button("← Back");

//         String buttonStyle = "-fx-font-size: 18px; -fx-font-weight: bold; "
//                 + "-fx-padding: 12px 25px; -fx-background-radius: 10; "
//                 + "-fx-cursor: hand;";
//         viewOrderBtn.setStyle(buttonStyle + "-fx-background-color: #ff4c4c; -fx-text-fill: white;");
//        backButton.setStyle(buttonStyle + "-fx-background-color: #333333; -fx-text-fill: white;");
//         //backBtn.setStyle(buttonStyle + "-fx-background-color: #888888; -fx-text-fill: white;");

//         // Hover Effects
//         viewOrderBtn.setOnMouseEntered(e -> viewOrderBtn.setStyle(buttonStyle + "-fx-background-color: #ff1c1c; -fx-text-fill: white;"));
//         viewOrderBtn.setOnMouseExited(e -> viewOrderBtn.setStyle(buttonStyle + "-fx-background-color: #ff4c4c; -fx-text-fill: white;"));

//        backButton.setOnMouseEntered(e ->backButton.setStyle(buttonStyle + "-fx-background-color: #333333; -fx-text-fill: white;"));

//         // backBtn.setOnMouseEntered(e -> backBtn.setStyle(buttonStyle + "-fx-background-color: #666666; -fx-text-fill: white;"));
//         //backBtn.setOnMouseExited(e -> backBtn.setStyle(buttonStyle + "-fx-background-color: #888888; -fx-text-fill: white;"));
//          // Back navigation to Home
//         backButton.setOnAction(e -> {
//             try {
//                 new Home().start(primaryStage);
//             } catch (Exception ex) {
//                 ex.printStackTrace();
//             }
//         });
//         HBox buttonBox = new HBox(25, viewOrderBtn, backButton);
//         buttonBox.setAlignment(Pos.CENTER);
//         buttonBox.setPadding(new Insets(20, 0, 0, 0));

//         // Combine all UI elements
//         VBox rootBox = new VBox(40, statusBar, buttonBox);
//         rootBox.setAlignment(Pos.CENTER);
//         rootBox.setPadding(new Insets(40));

//         // Stack background behind UI
//         StackPane root = new StackPane(backgroundView, rootBox);

//         // Make background scale with window
//         backgroundView.fitWidthProperty().bind(root.widthProperty());
//         backgroundView.fitHeightProperty().bind(root.heightProperty());

//         // Animation Timeline for step progression
//         Timeline timeline = new Timeline();
//         double stepTime = 4; // seconds per step

//         for (int i = 0; i < 4; i++) {
//             int index = i;

//             // Animate circle glow + pulse when active
//             KeyFrame circleFrame = new KeyFrame(Duration.seconds(stepTime * (i + 1)), e -> {
//                 circles[index].setFill(Color.GREEN);
//                 circles[index].setEffect(glowEffect);
//                 pulseCircle(circles[index]);
//             });
//             timeline.getKeyFrames().add(circleFrame);

//             // Animate gradient fill for connecting lines
//             if (i > 0) {
//                 Line line = progressLines[i - 1];
//                 KeyFrame lineFrame = new KeyFrame(Duration.seconds(stepTime * i + stepTime * 0.8), e -> {
//                     animateLineGradient(line);
//                 });
//                 timeline.getKeyFrames().add(lineFrame);
//             }
//         }
//         timeline.play();

//         // Create the scene and show stage
//         Scene scene = new Scene(root, screenWidth, screenHeight);
//         primaryStage.setTitle("Animated Order Tracker");
//         primaryStage.setScene(scene);
//         primaryStage.show();
//     }

//     // Pulse effect for active circle
//     private void pulseCircle(Circle circle) {
//         ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), circle);
//         scaleTransition.setFromX(1.0);
//         scaleTransition.setFromY(1.0);
//         scaleTransition.setToX(1.2);
//         scaleTransition.setToY(1.2);
//         scaleTransition.setAutoReverse(true);
//         scaleTransition.setCycleCount(Animation.INDEFINITE);
//         scaleTransition.play();
//     }

//     // Animate gradient fill for line
//     private void animateLineGradient(Line line) {
//         Stop[] stops = new Stop[]{
//                 new Stop(0, Color.LIMEGREEN),
//                 new Stop(1, Color.GREEN)
//         };
//         LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
//         line.setStroke(gradient);

//         FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), line);
//         fadeTransition.setFromValue(0.2);
//         fadeTransition.setToValue(1.0);
//         fadeTransition.setCycleCount(1);
//         fadeTransition.play();
//     }

//     public static void main(String[] args) {
//         launch(args);
//     }
// }
