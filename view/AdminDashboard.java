// package com.cafeverse.view;

// import javafx.application.Application;
// import javafx.beans.property.*;
// import javafx.collections.*;
// import javafx.geometry.Insets;
// import javafx.geometry.Rectangle2D;
// import javafx.scene.Scene;
// import javafx.scene.control.*;
// import javafx.scene.control.cell.PropertyValueFactory;
// import javafx.scene.layout.BorderPane;
// import javafx.scene.layout.VBox;
// import javafx.stage.Screen;
// import javafx.stage.Stage;

// public class AdminDashboard extends Application {
//     Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
//         double screenWidth = screenBounds.getWidth();
//         double screenHeight = screenBounds.getHeight();

//     Scene admiScene;
//     Stage adminStage;

//     // Shared order list
//     public static final ObservableList<Order> orders = FXCollections.observableArrayList();

//     public void setAdmiScene(Scene admiScene) {
//         this.admiScene = admiScene;
//     }

//     public void setAdminStage(Stage adminStage) {
//         this.adminStage = adminStage;
//     }

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

//     @Override
//     public void start(Stage primaryStage) {
//         this.adminStage = primaryStage;

//         VBox sidebar = new VBox(10);
//         sidebar.setPadding(new Insets(20));
//         sidebar.setStyle("-fx-background-color: #e6ccb2;");
//         sidebar.setPrefWidth(150);

//         Label title = new Label("Admin Panel");
//         title.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");

//         Button ordersBtn = new Button("Orders");
//         Button logoutBtn = new Button("Logout");
//         Button recipeBtn = new Button("Recipe");

//         sidebar.getChildren().addAll(title, ordersBtn, logoutBtn, recipeBtn);

//         BorderPane mainLayout = new BorderPane();
//         mainLayout.setLeft(sidebar);

//         VBox ordersView = createOrdersView(); 
//         mainLayout.setCenter(ordersView);

//         Scene scene = new Scene(mainLayout, screenWidth, screenHeight);
//         primaryStage.setScene(scene);
//         primaryStage.setTitle("Admin Dashboard - JavaFX");
//         primaryStage.show();

//         // Navigate to Recipe page when clicked
//         recipeBtn.setOnAction(e -> {
//             Recipe recipePage = new Recipe();
//             recipePage.start(primaryStage);  // Launch Recipe UI in the same stage
//         });
//     }

//     private VBox createOrdersView() {
//         VBox layout = new VBox(10);
//         layout.setPadding(new Insets(20));

//         Label title = new Label("Order Management");
//         title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

//         TableView<Order> tableView = new TableView<>(orders);

//         TableColumn<Order, String> dataCol = new TableColumn<>("Order");
//         dataCol.setCellValueFactory(new PropertyValueFactory<>("data"));

//         TableColumn<Order, Integer> tableCol = new TableColumn<>("Table #");
//         tableCol.setCellValueFactory(new PropertyValueFactory<>("tableNumber"));

//         TableColumn<Order, Void> actionCol = new TableColumn<>("Update Status");
//         actionCol.setCellFactory(col -> new TableCell<>() {
//             private final ComboBox<String> statusBox = new ComboBox<>(FXCollections.observableArrayList(
//                     "Received", "Preparing", "On the Way", "Complete"
//             ));

//             {
//                 statusBox.setOnAction(e -> {
//                     Order order = getTableView().getItems().get(getIndex());
//                     order.setStatus(statusBox.getValue());
//                 });
//             }

//             @Override
//             protected void updateItem(Void item, boolean empty) {
//                 super.updateItem(item, empty);
//                 if (empty) {
//                     setGraphic(null);
//                 } else {
//                     Order order = getTableView().getItems().get(getIndex());
//                     statusBox.setValue(order.getStatus());
//                     setGraphic(statusBox);
//                 }
//             }
//         });

//         tableView.getColumns().addAll(dataCol, tableCol, actionCol);
//         tableView.setPrefHeight(600);

//         layout.getChildren().addAll(title, tableView);
//         return layout;
//     }

//     public static void main(String[] args) {
//         launch();
//     }

//     // Add a show() so Recipe can navigate back
//     public void show(Stage stage) {
//         start(stage);
//     }
// }

package com.cafeverse.view;

import java.util.List;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class AdminDashboard extends Application {
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    double screenWidth = screenBounds.getWidth();
    double screenHeight = screenBounds.getHeight();

    Scene admiScene;
    Stage adminStage;

    public void setAdmiScene(Scene admiScene) {
        this.admiScene = admiScene;
    }

    public void setAdminStage(Stage adminStage) {
    this.adminStage = adminStage;  // Correct implementation
}

    // Shared order list
    public static final ObservableList<Order> orders = FXCollections.observableArrayList();

    // Shared status tracker page
    private static Stage trackerStage = null;
    private static status trackerPage = null;

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

    @Override
    public void start(Stage primaryStage) {
        

        this.adminStage = primaryStage;

        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #e6ccb2;");
        sidebar.setPrefWidth(200);

        Label title = new Label("Admin Panel");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");

        Button ordersBtn = new Button("Orders");
        Button logoutBtn = new Button("Logout");
        Button recipeBtn = new Button("Recipe");
        Button tableButton = new Button("Table Reservation");

        sidebar.getChildren().addAll(title, ordersBtn, tableButton, recipeBtn, logoutBtn);

        BorderPane mainLayout = new BorderPane();
        mainLayout.setLeft(sidebar);

        VBox ordersView = createOrdersView();
        mainLayout.setCenter(ordersView);

        Scene scene = new Scene(mainLayout, screenWidth, screenHeight);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Admin Dashboard");
        primaryStage.show();

        // Navigate to Recipe Page
        recipeBtn.setOnAction(e -> {
            Recipe recipePage = new Recipe();
            recipePage.start(primaryStage);
        });

        // Navigate to Table Reservation Page
        tableButton.setOnAction(e -> {
            AdminReservation reservationPage = new AdminReservation();
            try {
                reservationPage.start(primaryStage);  // Opens the reservation UI in the same window
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });ordersBtn.setOnAction(e->{
            fetchOrdersFromFirebase(); // Call this after UI setup
        });
        logoutBtn.setOnAction(e -> {
        primaryStage.close();  // Closes the current admin window
        });
    }

    private VBox createOrdersView() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        Label title = new Label("Order Management");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TableView<Order> tableView = new TableView<>(orders);

        TableColumn<Order, String> customerCol = new TableColumn<>("Customer");
        customerCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerCol.setPrefWidth(150);

        TableColumn<Order, String> dataCol = new TableColumn<>("Order");
        dataCol.setCellValueFactory(new PropertyValueFactory<>("data"));
        dataCol.setPrefWidth(300);

        TableColumn<Order, Integer> tableCol = new TableColumn<>("Table #");
        tableCol.setCellValueFactory(new PropertyValueFactory<>("tableNumber"));
        tableCol.setPrefWidth(100);

        TableColumn<Order, Void> statusCol = new TableColumn<>("Update Status");
        statusCol.setCellFactory(col -> new TableCell<>() {
            private final ComboBox<String> statusBox = new ComboBox<>(FXCollections.observableArrayList(
                    "Received", "Preparing", "On the Way", "Complete"
            ));

            {
                // statusBox.setOnAction(e -> {
                //     Order order = getTableView().getItems().get(getIndex());
                //     String newStatus = statusBox.getValue();
                //     order.setStatus(newStatus);

                //     // Update the live tracker UI
                //     openOrUpdateTracker(newStatus);
                // });
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

        tableView.getColumns().addAll(customerCol, dataCol, tableCol, statusCol);
        tableView.setPrefHeight(600);

        layout.getChildren().addAll(title, tableView);
        return layout;
    }

    private void fetchOrdersFromFirebase() {
    Firestore db = FirestoreClient.getFirestore();

    new Thread(() -> {
        try {
            ApiFuture<QuerySnapshot> future = db.collection("user").get();
            List<QueryDocumentSnapshot> userSnapshots = future.get().getDocuments();

            for (QueryDocumentSnapshot userDoc : userSnapshots) {
                String email = userDoc.getString("email");
                String name = userDoc.getString("name");

                CollectionReference cartRef = db.collection("user")
                        .document(userDoc.getId())
                        .collection("cart");

                List<QueryDocumentSnapshot> cartDocs = cartRef.get().get().getDocuments();

                for (QueryDocumentSnapshot cartDoc : cartDocs) {
                    String itemName = cartDoc.getString("itemName");
                    long price = cartDoc.getLong("price");
                    long quantity = cartDoc.getLong("quantity");

                    String orderData = itemName + " x" + quantity + " - ₹" + price;
                    // int tableNumber = (int) (Math.random() * 10) + 1; // Dummy table number

                    Order order = new Order(name, orderData, 5, "Received");
Platform.runLater(() -> orders.add(order));

                    // Update UI on JavaFX Application Thread
                    javafx.application.Platform.runLater(() -> orders.add(order));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }).start();
}


    // private void openOrUpdateTracker(String status) {
    //     if (trackerPage == null) {
    //         trackerPage = new status();
    //         trackerPage.setOrderStatus(status);
    //         trackerStage = new Stage();
    //         try {
    //             trackerPage.start(trackerStage);
    //         } catch (Exception e) {
    //             e.printStackTrace();
    //         }
    //     } else {
    //         trackerPage.updateStatus(status);
    //         if (trackerStage != null && !trackerStage.isShowing()) {
    //             trackerStage.show();
    //         }
    //     }
    // }

    public static void main(String[] args) {
        launch();
    }

    public void show(Stage stage) {
        start(stage);
    }

    // public void setAdminStage(Stage adminStage2) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'setAdminStage'");
    // }
}