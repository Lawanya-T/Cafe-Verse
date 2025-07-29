package com.cafeverse.view;

import javax.sound.sampled.Line;
import javax.swing.text.html.ImageView;

import org.w3c.dom.Text;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Home extends Application {

    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    double screenWidth = screenBounds.getWidth();
    double screenHeight = screenBounds.getHeight();

    Scene homScene;
    Stage homStage;

    public void setHomScene(Scene homScene) {
        this.homScene = homScene;
    }

    public void setHomStage(Stage homStage) {
        this.homStage = homStage;
    }

    public ScrollPane createScene(Runnable myThread) {
        ImageView logoView = roundedImage("assets/images/logo3.png", 100, 50);
        Text tx1 = new Text("Home");
        tx1.setStyle("-fx-font-weight:bold");
        Text tx2 = new Text("About Us");
        tx2.setStyle("-fx-font-weight:bold");
        Text tx3 = new Text("Menu");
        tx3.setStyle("-fx-font-weight:bold");
        Text tx4 = new Text("Table Reservation");
        tx4.setStyle("-fx-font-weight:bold");
        Text tx5 = new Text("Decoration");
        tx5.setStyle("-fx-font-weight:bold");
        Text tx6 = new Text("Status");
        tx6.setStyle("-fx-font-weight:bold");
        Text tx7= new Text("Home Delivery");
        tx7.setStyle("-fx-font-weight:bold");
         ImageView logoView1 = roundedImage("assets/images/profilep.png", 100, 50);
         

        for (Text t : new Text[] { tx1, tx2, tx3, tx4, tx5, tx6 ,tx7}) {
            t.setFont(Font.font(18));

        //     ScaleTransition scaleIn = new ScaleTransition(Duration.millis(200), t);
        //     scaleIn.setToX(1.05);
        //     scaleIn.setToY(1.05);
        //     ScaleTransition scaleOut = new ScaleTransition(Duration.millis(200), t);
        //     scaleOut.setToX(1);
        //     scaleOut.setToY(1);

        //   t.setOnMouseEntered(e -> scaleIn.playFromStart());
        //   t.setOnMouseExited(e -> scaleOut.playFromStart());

            
        }


        // ✅ Navigate to CafeCategories when "Menu" is clicked
        logoView1.setOnMouseClicked(e -> {
    try {
        // Fetch user data dynamically (replace with your login/session data later)
        User currentUser = new User("John Doe", "john@example.com", "9876543210", "Pune, India");

        // Pass user to profile page
        profilepage.setUser(currentUser);

        profilepage profile = new profilepage(currentUser);
        Stage profileStage = new Stage();
        profile.start(profileStage);
        profileStage.setMaximized(true);  // Make it full screen
        homStage.close(); // Optional: close home page
    } catch (Exception ex) {
        ex.printStackTrace();
    }
});


       // logoView1.setOnMouseClicked(e-> new profilepage<>().start(homStage));
        tx3.setOnMouseClicked(e -> new CafeCategories().start(homStage));//menu
        tx6.setOnMouseClicked(e -> {//status
            try {
                new com.cafeverse.view.status().start(new Stage());
                homStage.close();
            
        } catch (Exception ex) {
            // TODO: handle exception
            ex.printStackTrace();
        }
        });

       tx5.setOnMouseClicked(e -> {//decoration
    try {
        new com.cafeverse.view.customize().start(homStage); // ✅ Correct
    } catch (Exception ex) {
        ex.printStackTrace();
    }
});
tx7.setOnMouseClicked(e->{
    try {
        new com.cafeverse.view.DeliveryMap().start(homStage);
    } catch (Exception ex) {
        // TODO: handle exception
        ex.printStackTrace();
    }
});
 tx4.setOnMouseClicked(e -> {
            try {
                new TableReservation().start(homStage); // ✅ Corrected to TableReservation
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
tx2.setOnMouseClicked(e -> {
    try {
        new com.cafeverse.view.about().start(homStage); // <-- This navigates to About page
    } catch (Exception ex) {
        ex.printStackTrace();
    }
});

        HBox navBar = new HBox(90, logoView, tx1, tx2, tx3, tx4, tx5, tx6,tx7,logoView1);
        navBar.setPadding(new Insets(15));
        navBar.setAlignment(Pos.CENTER_LEFT);
        navBar.setStyle("-fx-background-color: #e6ccb2;");

        ImageView welcomeImage = roundedImage("assets/images/coffee_home.png", 1490, 500);

        ImageView happyHoursImage = roundedImage("assets/images/happyHour.png", 800, 400);
        VBox vb7 = new VBox(20, happyHoursImage);
        vb7.setAlignment(Pos.CENTER_LEFT);

        Text happText = new Text("Happy Hour Special");
        happText.setFill(Color.BROWN);
        happText.setFont(new Font(50));
        happText.setStyle("-fx-font-weight: bold");

        Line line2 = new Line(0, 0, 130, 0);
        line2.setStroke(Color.BROWN);
        line2.setStrokeWidth(4);
        Text text2 = new Text("Enjoy delicious meals at half the price during our Happy Hour!\n"
                + "From 4 PM to 6 PM, indulge in mouthwatering dishes \nlike creamy pastas, sizzling skillet meals, \nand juicy burgers with crispy fries — all at 50% off.\n\n1🕓 Available daily | 🍝 Limited-time offer | 🍔 Dine-in & takeaway");
        text2.setFont(new Font("Arial", 20));
        VBox vb9 = new VBox(60, text2);
        vb9.setMaxHeight(140);
        vb9.setMaxWidth(150);
        VBox vb8 = new VBox(30, happText, line2, vb9);
        vb8.setAlignment(Pos.TOP_CENTER);
        HBox hb5 = new HBox(120, vb7, vb8);
        StackPane lStackPane = new StackPane(hb5);

        ImageView decorationImage = roundedImage("assets/images/decorate.jpeg", 700, 400);
        VBox vb10 = new VBox(20, decorationImage);
        vb10.setAlignment(Pos.CENTER_RIGHT);

        Text decText = new Text("Customize Your Own");
        decText.setFill(Color.BROWN);
        decText.setFont(new Font(50));
        decText.setStyle("-fx-font-weight: bold");

        Line line3 = new Line(0, 0, 130, 0);
        line3.setStroke(Color.BROWN);
        line3.setStrokeWidth(4);
        Text text3 = new Text("Planning something special? Make it truly yours with our \ncustomizable dining and event experience! \nFrom themed decorations and personalized seating to \ncurated menus and lighting — design every detail \nto match your style.\n"
                + "\n"
                + "Perfect for:\n"
                + "🎈 Birthday Parties | 🥂 Anniversaries | 🎊 Private Dinners | 🧁 Baby Showers");
        text3.setFont(new Font("Arial", 20));
        VBox vb11 = new VBox(30, decText, line3, text3);
        vb11.setAlignment(Pos.TOP_CENTER);
        HBox hb6 = new HBox(60, vb11, vb10);
        hb6.setAlignment(Pos.CENTER_RIGHT);
        StackPane rStackPane = new StackPane(hb6);

        Text whyText = new Text("Why Choose Us");
        whyText.setFill(Color.BROWN);
        whyText.setTextAlignment(TextAlignment.CENTER);
        whyText.setFont(new Font(50));
        whyText.setStyle("-fx-font-weight: bold;");

        Line line = new Line(0, 0, 130, 0);
        line.setStroke(Color.BROWN);
        line.setStrokeWidth(4);
        VBox vb1 = new VBox(5, whyText, line);
        vb1.setAlignment(Pos.CENTER);
        StackPane root = new StackPane(vb1);
        Text text = new Text("We're dedicated to creating exceptional experiences with every visit");
        text.setFont(new Font(20));

        VBox vb2 = createFeatureBox("Premium \nQuality", "Assets/Images/barista.png",
                "Directly sourced coffee beans from sustainable farms, roasted to perfection in small batches.");
        VBox vb3 = createFeatureBox("Handcrafted", "Assets/Images/heart_home.png",
                "Each drink is carefully prepared by our skilled baristas with attention to every detail.");
        VBox vb4 = createFeatureBox("Eco-Friendly", "Assets/Images/leaf.png",
                "Committed to sustainability with compostable packaging and zero-waste initiatives.");

        HBox hb1 = new HBox(100, vb2, vb3, vb4);
        hb1.setAlignment(Pos.CENTER);
        hb1.setPadding(new Insets(20));

        Text oText = new Text("Our Specialties");
        oText.setFill(Color.BROWN);
        oText.setTextAlignment(TextAlignment.CENTER);
        oText.setFont(new Font(50));
        oText.setStyle("-fx-font-weight: bold;");
        Line line1 = new Line(0, 0, 130, 0);
        line1.setStroke(Color.BROWN);
        line1.setStrokeWidth(4);
        VBox vb5 = new VBox(5, oText, line1);
        vb5.setAlignment(Pos.CENTER);
        StackPane root1 = new StackPane(vb5);
        Text text1 = new Text("Menu highlights that keep our customers coming back for more");
        text1.setFont(new Font(20));

        Button btn1 = styledButton("Coffees", true);
        Button btn2 = styledButton("Fries", false);
        Button btn3 = styledButton("Italian", false);
        Button btn4 = styledButton("Desserts", false);
        Button btn5 = styledButton("Beverages", false);

        HBox hb2 = new HBox(30, btn1, btn2, btn3, btn4, btn5);
        hb2.setAlignment(Pos.CENTER);
        hb2.setPadding(new Insets(20));
        hb2.setStyle("-fx-background-color: #FFF8F0;");

        VBox mainContent = new VBox(20, navBar, welcomeImage, lStackPane, rStackPane, root, text, hb1, root1, text1,
                hb2);
        mainContent.setPadding(new Insets(15));
        mainContent.setStyle("-fx-background-color: #fffaf0;");
        mainContent.setAlignment(Pos.CENTER);

        ScrollPane scrollPane = new ScrollPane(mainContent);
        scrollPane.setFitToWidth(true);

        return scrollPane;
    }

    private VBox createFeatureBox(String heading, String imagePath, String description) {
        ImageView icon = new ImageView(new Image(imagePath));
        icon.setFitWidth(80);
        icon.setFitHeight(80);

        Label headingLabel = new Label(heading);
        headingLabel.setFont(new Font("Arial", 24));
        headingLabel.setStyle("-fx-font-weight:bold;");
        headingLabel.setTextFill(Color.BROWN);

        Label descLabel = new Label(description);
        descLabel.setWrapText(true);
        descLabel.setFont(new Font(15));
        descLabel.setPadding(new Insets(20));

        VBox box = new VBox(10, icon, headingLabel, descLabel);
        box.setPrefWidth(150);
        box.setStyle("-fx-background-color: #ffefd5;");
        box.setAlignment(Pos.CENTER);
        return box;
    }

    private ImageView roundedImage(String path, double w, double h) {
        ImageView iv = new ImageView(new Image(path));
        iv.setFitWidth(w);
        iv.setFitHeight(h);

        Rectangle clip = new Rectangle(w, h);
        clip.setArcWidth(30);
        clip.setArcHeight(30);
        iv.setClip(clip);

        DropShadow ds = new DropShadow();
        ds.setRadius(20);
        ds.setOffsetY(4);
        ds.setColor(Color.rgb(0, 0, 0, 0.25));
        iv.setEffect(ds);

        return iv;
    }

    private Button styledButton(String text, boolean isSelected) {
        Button btn = new Button(text);
        btn.setFocusTraversable(false);
        btn.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-border-color: brown;" +
                        "-fx-border-width: 1px;" +
                        "-fx-border-radius: 8px;" +
                        "-fx-min-width: 50px;" +
                        "-fx-min-height: 20px;" +
                        (isSelected ? "-fx-background-color:brown;" : "")
        );
        btn.setTextFill(isSelected ? Color.WHITE : Color.BROWN);
        return btn;
    }

    @Override
    

 public void start(Stage primaryStage) throws Exception {
        setHomStage(primaryStage);
        ScrollPane pane = createScene(null);
        Scene scene = new Scene(pane, screenWidth, screenHeight);
        primaryStage.setTitle("CafeVerse - Home");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();


        // setHomStage(primaryStage);
        // ScrollPane pane = createScene(null);
        // Scene scene = new Scene(pane, screenWidth, screenHeight);
        // primaryStage.setTitle("CafeVerse - Home");
        // primaryStage.setScene(scene);
        // primaryStage.setMaximized(true);
        // primaryStage.show();
    }
}



    

