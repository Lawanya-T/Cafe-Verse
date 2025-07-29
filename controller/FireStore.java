package com.cafeverse.controller;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import java.util.HashMap;
import java.util.Map;

public class FireStore {
    
    public static void addMenuItem(String name, int price, String category) {
        Firestore db = FirestoreClient.getFirestore();

        Map<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("price", price);
        data.put("category", category);

        db.collection("menu").add(data)
            .addListener(() -> System.out.println("Data added to Firestore"), Runnable::run);
    }
}
