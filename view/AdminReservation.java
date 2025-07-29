package com.cafeverse.view;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;

public class AdminReservation extends Application {
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    double screenWidth = screenBounds.getWidth();
    double screenHeight = screenBounds.getHeight();

    private Pane layoutPane = new Pane();
    private ToggleGroup filterGroup = new ToggleGroup();
    private List<VBox> allTables = new ArrayList<>();
    private Map<String, VBox> tableMap = new HashMap<>();
    private ListView<String> tableListView = new ListView<>();
    private Label summaryLabel = new Label();

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        Label title = new Label("Table Layout");
        title.setFont(Font.font(32));

        Button backBtn = new Button("Back to Dashboard");
backBtn.setStyle("-fx-background-color: #8b5e3c; -fx-text-fill: white; -fx-font-size: 16px;");
backBtn.setOnAction(e -> {
    AdminDashboard dashboard = new AdminDashboard();
    try {
        dashboard.start(primaryStage);
    } catch (Exception ex) {
        ex.printStackTrace();
    }
});


        BorderPane topBar = new BorderPane();
        topBar.setPadding(new Insets(10));

        // Center title
        StackPane centerPane = new StackPane(title);
        topBar.setCenter(centerPane);

        // Right menu button
        topBar.setRight(backBtn);

        // Set top bar in root
        root.setTop(topBar);

        // ===== Left Panel =====
        VBox leftPanel = new VBox(20);
        leftPanel.setStyle("-fx-background-color: #f0d8c8;");
        leftPanel.setPadding(new Insets(20));
        leftPanel.setPrefWidth(250);

        Label filterTitle = new Label("Table Reservation");
        filterTitle.setFont(Font.font(26));

        RadioButton allBtn = new RadioButton("All");
        RadioButton reservedBtn = new RadioButton("Reserved");
        RadioButton availableBtn = new RadioButton("Available");

        allBtn.setToggleGroup(filterGroup);
        reservedBtn.setToggleGroup(filterGroup);
        availableBtn.setToggleGroup(filterGroup);
        allBtn.setSelected(true);

        VBox filterBox = new VBox(10, allBtn, reservedBtn, availableBtn);
        filterBox.setPadding(new Insets(10));

        filterGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> applyFilter());

        tableListView.setPrefHeight(300);
        tableListView.setMaxHeight(300);
        tableListView.setMouseTransparent(false);
        tableListView.setFocusTraversable(false);

        tableListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && tableMap.containsKey(newVal)) {
                VBox selectedTable = tableMap.get(newVal);
                layoutPane.getChildren().remove(selectedTable);
                layoutPane.getChildren().add(selectedTable);

                for (VBox table : allTables) {
                    table.setStyle("");
                }

                selectedTable.setStyle("-fx-border-color: blue; -fx-border-width: 3;");
            }
        });

        summaryLabel.setFont(Font.font(14));

        leftPanel.getChildren().addAll(filterTitle, filterBox, new Label("Tables:"), tableListView, summaryLabel);
        leftPanel.setAlignment(Pos.TOP_LEFT);
        leftPanel.setStyle("-fx-font-size: 16; -fx-background-color: #f5cfb3ff");

        // ===== Table Layout Pane (center) =====
        layoutPane.setStyle("-fx-background-color: #fff5ee;-fx-font-weight:bold");
        layoutPane.setPrefSize(1000, 800);
        addTables();

        // ===== Right Button Panel =====
        

        root.setLeft(leftPanel);
        root.setCenter(layoutPane);

        Scene scene = new Scene(root, screenWidth, screenHeight);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Table Layout");
        root.setStyle("-fx-background-color: #fff0e6;");
        primaryStage.show();
    }

    private void applyFilter() {
        String selected = ((RadioButton) filterGroup.getSelectedToggle()).getText();

        layoutPane.getChildren().clear();
        for (VBox table : allTables) {
            Button btn = (Button) ((HBox) table.getChildren().get(1)).getChildren().get(0);
            boolean isReserved = btn.getText().equals("Reserved");

            if (selected.equals("All")
                    || (selected.equals("Reserved") && isReserved)
                    || (selected.equals("Available") && !isReserved)) {
                layoutPane.getChildren().add(table);
            }
        }

        long reservedCount = allTables.stream()
                .map(t -> (Button) ((HBox) t.getChildren().get(1)).getChildren().get(0))
                .filter(b -> b.getText().equals("Reserved"))
                .count();

        summaryLabel.setText("Total Reserved Tables: " + reservedCount);
        summaryLabel.setAlignment(Pos.CENTER);
        summaryLabel.setStyle("-fx-font-size: 18; -fx-text-fill: #000000ff");

        updateTableList();
    }

    private void updateTableList() {
        List<String> reserved = new ArrayList<>();
        List<String> available = new ArrayList<>();

        for (Map.Entry<String, VBox> entry : tableMap.entrySet()) {
            Button btn = (Button) ((HBox) entry.getValue().getChildren().get(1)).getChildren().get(0);
            if (btn.getText().equals("Reserved")) {
                reserved.add(entry.getKey());
            } else {
                available.add(entry.getKey());
            }
        }

        reserved.sort(Comparator.naturalOrder());
        available.sort(Comparator.naturalOrder());

        tableListView.getItems().setAll(reserved);
        tableListView.getItems().addAll(available);
    }

    private void addTables() {
        int tableNum = 1;
        double tableWidth = 200;
        double hGap = 100;
        double vGap = 100;
        int cols = 3;
        int rows = 3;

        double totalWidth = cols * tableWidth + (cols - 1) * hGap;
        double totalHeight = rows * 150 + (rows - 1) * vGap;

        double startX = (layoutPane.getPrefWidth() - totalWidth) / 2;
        double startY = (layoutPane.getPrefHeight() - totalHeight) / 2;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int chairs = (tableNum % 3 == 0) ? 2 : (tableNum % 2 == 0 ? 4 : 6);
                VBox tableBlock = createTableBlock(tableNum, chairs);

                double x = startX + col * (tableWidth + hGap);
                double y = startY + row * (150 + vGap);

                tableBlock.setLayoutX(x);
                tableBlock.setLayoutY(y);

                layoutPane.getChildren().add(tableBlock);
                allTables.add(tableBlock);

                tableMap.put("Table " + tableNum, tableBlock);
                tableNum++;
            }
        }

        applyFilter();
    }

    private VBox createTableBlock(int tableNumber, int chairCount) {
        VBox vbox = new VBox(30);
        vbox.setAlignment(Pos.CENTER);

        Pane tablePane = new Pane();
        tablePane.setPrefSize(200, 100);
        tablePane.setStyle("-fx-background-color: #f4c6c6; -fx-background-radius: 15;");
        Text label = new Text("Table " + tableNumber);
        label.setTranslateX(70);
        label.setTranslateY(55);
        label.setFont(Font.font(20));
        tablePane.getChildren().add(label);

        makeDraggable(vbox);

        List<Rectangle> chairs = new ArrayList<>();
        for (int i = 0; i < chairCount; i++) {
            Rectangle chair = createChair(i, chairCount);
            makeDraggable(chair);
            tablePane.getChildren().add(chair);
            chairs.add(chair);
        }

        Button reserveBtn = new Button("Reserve");
        reserveBtn.setStyle("-fx-background-color: lightblue; -fx-text-fill: black; -fx-font-weight: bold; -fx-font-size:14;");
        reserveBtn.setOnAction(e -> {
            tablePane.setStyle("-fx-background-color: lightgreen; -fx-background-radius: 15;");
            reserveBtn.setText("Reserved");
            reserveBtn.setDisable(true);
            reserveBtn.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size:14;");
            applyFilter();
        });

        Button addChairBtn = new Button("Add Chair");
        addChairBtn.setOnAction(e -> {
            Rectangle newChair = createChair(chairs.size(), chairs.size() + 1);
            makeDraggable(newChair);
            chairs.add(newChair);
            tablePane.getChildren().add(newChair);
        });

        HBox buttonBox = new HBox(10, reserveBtn, addChairBtn);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));

        vbox.getChildren().addAll(tablePane, buttonBox);
        return vbox;
    }

    private Rectangle createChair(int index, int total) {
        Rectangle chair = new Rectangle(20, 20, Color.SALMON);

        double spacing = 30;
        double x, y;

        if (index < total / 2) {
            x = spacing + index * spacing;
            y = -25;
        } else {
            x = spacing + (index - total / 2) * spacing;
            y = 105;
        }

        chair.setTranslateX(x);
        chair.setTranslateY(y);
        return chair;
    }

    private void makeDraggable(javafx.scene.Node node) {
        node.setOnMousePressed(e -> {
            node.setUserData(new double[]{e.getSceneX(), e.getSceneY(), node.getLayoutX(), node.getLayoutY()});
            e.consume();
        });

        node.setOnMouseDragged(e -> {
            double[] data = (double[]) node.getUserData();
            double offsetX = e.getSceneX() - data[0];
            double offsetY = e.getSceneY() - data[1];
            node.setLayoutX(data[2] + offsetX);
            node.setLayoutY(data[3] + offsetY);
            e.consume();
        });

        node.setOnMouseReleased(e -> e.consume());
    }

    public static void main(String[] args) {
        launch(args);
    }
}