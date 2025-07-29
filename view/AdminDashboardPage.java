// package com.cafeverse.view;
// import javafx.application.Application;
// import javafx.beans.property.*;
// import javafx.collections.*;
// import javafx.scene.Scene;
// import javafx.scene.control.*;
// import javafx.scene.control.cell.*;
// import javafx.scene.layout.VBox;
// import javafx.stage.Stage;

// public class AdminDashboard extends Application {

//     // Order class
//     public static class Order {
//         private final StringProperty customerName;
//         private final StringProperty data;
//         private final IntegerProperty tableNumber;
//         private final StringProperty status;

//         public Order(String customerName, String data, int tableNumber, String status) {
//             this.customerName = new SimpleStringProperty(customerName);
//             this.data = new SimpleStringProperty(data);
//             this.tableNumber = new SimpleIntegerProperty(tableNumber);
//             this.status = new SimpleStringProperty(status);
//         }

//         public String getCustomerName() { return customerName.get(); }
//         public StringProperty customerNameProperty() { return customerName; }

//         public String getData() { return data.get(); }
//         public StringProperty dataProperty() { return data; }

//         public int getTableNumber() { return tableNumber.get(); }
//         public IntegerProperty tableNumberProperty() { return tableNumber; }

//         public String getStatus() { return status.get(); }
//         public StringProperty statusProperty() { return status; }

//         public void setStatus(String newStatus) { this.status.set(newStatus); }
//     }

//     // Shared order list (simulates backend)
//     private final ObservableList<Order> orders = FXCollections.observableArrayList();

//     @Override
//     public void start(Stage primaryStage) {
//         // Sample orders
//         orders.addAll(
//             new Order("John", "Burger & Fries", 1, "Received"),
//             new Order("Emily", "Pizza", 2, "Preparing"),
//             new Order("Mike", "Sushi", 3, "On the Way")
//         );

//         TableView<Order> tableView = new TableView<>(orders);

//         // Columns
//         TableColumn<Order, String> nameCol = new TableColumn<>("Customer Name");
//         nameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));

//         TableColumn<Order, String> dataCol = new TableColumn<>("Order");
//         dataCol.setCellValueFactory(new PropertyValueFactory<>("data"));

//         TableColumn<Order, Integer> tableCol = new TableColumn<>("Table #");
//         tableCol.setCellValueFactory(new PropertyValueFactory<>("tableNumber"));

//         TableColumn<Order, String> statusCol = new TableColumn<>("Status");
//         statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

//         TableColumn<Order, Void> actionCol = new TableColumn<>("Update Status");
//         actionCol.setCellFactory(col -> new TableCell<>() {
//             private final ComboBox<String> statusBox = new ComboBox<>(FXCollections.observableArrayList(
//                     "Received", "Preparing", "On the Way", "Complete"
//             ));
//             {
//                 statusBox.setOnAction(e -> {
//                     Order order = getTableView().getItems().get(getIndex());
//                     String newStatus = statusBox.getValue();
//                     order.setStatus(newStatus);
//                     // You can also trigger update in the user window here
//                 });
//             }

//             @Override
//             protected void updateItem(Void item, boolean empty) {
//                 super.updateItem(item, empty);
//                 if (empty) {
//                     setGraphic(null);
//                 } else {
//                     statusBox.setValue(getTableView().getItems().get(getIndex()).getStatus());
//                     setGraphic(statusBox);
//                 }
//             }
//         });

//         tableView.getColumns().addAll(nameCol, dataCol, tableCol, statusCol, actionCol);

//         VBox layout = new VBox(tableView);
//         layout.setStyle("-fx-padding: 20;");
//         Scene scene = new Scene(layout, 700, 400);
//         primaryStage.setScene(scene);
//         primaryStage.setTitle("Admin Dashboard");
//         primaryStage.show();

//         // Simulate user side window
//         showUserWindow();
//     }

//     private void showUserWindow() {
//         Stage userStage = new Stage();
//         TableView<Order> userTable = new TableView<>(orders); // Same data

//         TableColumn<Order, String> nameCol = new TableColumn<>("Customer");
//         nameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));

//         TableColumn<Order, String> dataCol = new TableColumn<>("Order");
//         dataCol.setCellValueFactory(new PropertyValueFactory<>("data"));

//         TableColumn<Order, Integer> tableCol = new TableColumn<>("Table");
//         tableCol.setCellValueFactory(new PropertyValueFactory<>("tableNumber"));

//         TableColumn<Order, String> statusCol = new TableColumn<>("Status");
//         statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

//         userTable.getColumns().addAll(nameCol, dataCol, tableCol, statusCol);

//         VBox layout = new VBox(userTable);
//         layout.setStyle("-fx-padding: 20;");
//         Scene scene = new Scene(layout, 600, 300);
//         userStage.setScene(scene);
//         userStage.setTitle("User View (Live Status)");
//         userStage.show();
//     }

//     public static void main(String[] args) {
//         launch(args);
//     }
// }
package com.cafeverse.view;
import javax.swing.text.Element;
import javax.swing.text.html.ImageView;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.beans.property.*;

public class AdminDashboardPage extends Application {

    public static class Order {
        private final StringProperty customerName;
        private final StringProperty data;
        private final IntegerProperty tableNumber;
        private final StringProperty status;

        public Order(String customerName, String data, int tableNumber, String status) {
            this.customerName = new SimpleStringProperty(customerName);
            this.data = new SimpleStringProperty(data);
            this.tableNumber = new SimpleIntegerProperty(tableNumber);
            this.status = new SimpleStringProperty(status);
        }

        public String getCustomerName() { return customerName.get(); }
        public StringProperty customerNameProperty() { return customerName; }

        public String getData() { return data.get(); }
        public StringProperty dataProperty() { return data; }

        public int getTableNumber() { return tableNumber.get(); }
        public IntegerProperty tableNumberProperty() { return tableNumber; }

        public String getStatus() { return status.get(); }
        public StringProperty statusProperty() { return status; }

        public void setStatus(String newStatus) { this.status.set(newStatus); }
    }

    private final ObservableList<Order> orders = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {
       
        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #e6ccb2;");
        sidebar.setPrefWidth(150);

        Image img = new Image("Assets\\Images\\logo3.png");
        ImageView imgView = new ImageView((Element) img);
        imgView.setSize(100, 100);
        Label title = new Label("Admin Panel");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");

        Button ordersBtn = new Button("Orders");
        Button logoutBtn = new Button("Logout");

        sidebar.getChildren().addAll(title ,ordersBtn, logoutBtn);

        // === Main Content Area ===
        BorderPane mainLayout = new BorderPane();
        mainLayout.setLeft(sidebar);

        VBox ordersView = createOrdersView(); // default view
        mainLayout.setCenter(ordersView);

        // === Button Actions ===
        ordersBtn.setOnAction(e -> mainLayout.setCenter(createOrdersView()));
        logoutBtn.setOnAction(e -> System.out.println("Logged out!")); // or close the stage

        // === Scene ===
        Scene scene = new Scene(mainLayout, 900, 600);
        stage.setScene(scene);
        stage.setTitle("Admin Dashboard - JavaFX");
        stage.show();
    }

    private VBox createOrdersView() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        Label title = new Label("Order Management");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TableView<Order> tableView = new TableView<>(orders);

        TableColumn<Order, String> nameCol = new TableColumn<>("Customer");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));

        TableColumn<Order, String> dataCol = new TableColumn<>("Order");
        dataCol.setCellValueFactory(new PropertyValueFactory<>("data"));

        TableColumn<Order, Integer> tableCol = new TableColumn<>("Table #");
        tableCol.setCellValueFactory(new PropertyValueFactory<>("tableNumber"));

        TableColumn<Order, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<Order, Void> actionCol = new TableColumn<>("Update Status");
        actionCol.setCellFactory(col -> new TableCell<>() {
            private final ComboBox<String> statusBox = new ComboBox<>(FXCollections.observableArrayList(
                "Received", "Preparing", "On the Way", "Complete"
            ));

            {
                statusBox.setOnAction(e -> {
                    Order order = getTableView().getItems().get(getIndex());
                    order.setStatus(statusBox.getValue());
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    Order order = getTableView().getItems().get(getIndex());
                    statusBox.setValue(order.getStatus());
                    setGraphic(statusBox);
                }
            }
        });

        tableView.getColumns().addAll(nameCol, dataCol, tableCol, statusCol, actionCol);
        tableView.setPrefHeight(400);

        layout.getChildren().addAll(title, tableView);
        return layout;
    }

    public static void main(String[] args) {
        launch();
    }
}
