package com.cafeverse.controller;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import javafx.scene.control.Label;

import java.util.HashMap;
import java.util.Map;

public class UserController {

    public static void saveUserProfile(String username, String address, String email, String phone) {
        Firestore db = FirestoreClient.getFirestore();

        Map<String, Object> profile = new HashMap<>();
        profile.put("name",username);
        profile.put("address", address);
        profile.put("email", email);
        profile.put("phone", phone);

        db.collection("users").document(username).set(profile);
    }

    public static String getUserAddress(String username) {
        try {
            Firestore db = FirestoreClient.getFirestore();
            return db.collection("users")
                    .document(username)
                    .get()
                    .get()
                    .getString("address");
        } catch (Exception e) {
            e.printStackTrace();
            return "Address not found";
        }
    }

    public static void saveUserProfile(Label username, Label email, Label phone, Label address) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveUserProfile'");
    }
}

