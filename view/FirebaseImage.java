package com.cafeverse.view;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import com.google.cloud.storage.Bucket;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;

public class FirebaseImage {

    public static void initializeFirebase() throws Exception {
        FileInputStream serviceAccount = new FileInputStream("assets\\cafeverse-1b93e-firebase-adminsdk-fbsvc-8afa10c9fb.json");

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket("gs://cafeverse-1b93e.firebasestorage.app") 
                .build();

        FirebaseApp.initializeApp(options);
    }

    public static void uploadImages(String folderPath) throws Exception {
        Bucket bucket = StorageClient.getInstance().bucket();
        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files == null) {
            System.out.println("No files found in folder.");
            return;
        }

        for (File file : files) {
            if (file.isFile() && isImage(file)) {
                System.out.println("Uploading: " + file.getName());
                bucket.create("images/" + file.getName(), Files.readAllBytes(file.toPath()), "image/jpeg");
            }
        }
    }

    private static boolean isImage(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".gif");
    }

// import com.google.cloud.storage.Bucket;
// import com.google.firebase.cloud.StorageClient;

// import java.io.File;
// import java.nio.file.Files;

// public class FirebaseImage {

//     public static void uploadImages(String folderPath) throws Exception {
//         // ✅ Explicitly set the bucket ID (if not set in FirebaseOptions)
//         Bucket bucket = StorageClient.getInstance().bucket("gs://cafeverse-1b93e.firebasestorage.ap-");

//         File folder = new File(folderPath);
//         File[] files = folder.listFiles();

//         if (files == null) {
//             System.out.println("No files found in folder.");
//             return;
//         }

//         for (File file : files) {
//             if (file.isFile() && isImage(file)) {
//                 System.out.println("Uploading: " + file.getName());
//                 bucket.create("images/" + file.getName(), Files.readAllBytes(file.toPath()), "image/jpeg");
//             }
//         }

//         System.out.println("All images uploaded!");
//     }

//     private static boolean isImage(File file) {
//         String name = file.getName().toLowerCase();
//         return name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".gif");
//     }



    public static void main(String[] args) {
        try {
            // initialize();
            uploadImages("assets\\images"); // Change to actual path
            System.out.println("All images uploaded!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
