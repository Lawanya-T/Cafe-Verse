package com.cafeverse.dao;

import java.util.concurrent.ExecutionException;

import com.cafeverse.controller.FireStore;
import com.cafeverse.model.CartItem;
import com.cafeverse.model.UserSignUp;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

public class SignUpDao {
    public static void addUserDataToFirestore(UserSignUp user) {
        ApiFuture<WriteResult> writeResult = FirestoreClient.getFirestore().collection("user").document(user.getEmail())
                .set(user);
        try {
            System.out.println(writeResult.get().toString());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static UserSignUp getUserData(String email){
       try {
        DocumentSnapshot docSnap =  FirestoreClient.getFirestore().collection("user").document(email).get().get();
        UserSignUp user = docSnap.toObject(UserSignUp.class);
        System.out.println(user.getEmail());
        System.out.println(user.getName());
        System.out.println(user.getPassword());
       } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
       } catch (ExecutionException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
       }
        return null;
    }
}
