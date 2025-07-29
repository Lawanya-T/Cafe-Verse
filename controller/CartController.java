package com.cafeverse.controller;

import com.cafeverse.model.cartModel;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

import java.util.HashMap;
import java.util.Map;

public class CartController {

    public static void addCartItemForUser(String username, String name, int quantity, double price) {
    
    cartModel.getUsername();
    cartModel.getName();
    cartModel.getQuantity();
    cartModel.getPrice();
    Firestore db = FirestoreClient.getFirestore();

    Map<String, Object> itemData = new HashMap<>();
    itemData.put("itemName", name);
    itemData.put("quantity", quantity);
    itemData.put("price", price);

    db.collection("user")
      .document(username)
      .collection("cart")
      .add(itemData);
}


    public static void fetchCartItems(String username) {
    try {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("users")
                                            .document(username)
                                            .collection("cart")
                                            .get();

        // Block and get results (synchronous)
        for (QueryDocumentSnapshot document : future.get().getDocuments()) {
            System.out.println(document.getId() + " => " + document.getData());
        }

    } catch (Exception e) {
        System.err.println("Error fetching cart items: " + e.getMessage());
        e.printStackTrace();
    }
  }
}
