package com.cafeverse.controller;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.nio.file.Files;
import java.nio.file.Paths;

public class storage {
    public static void uploadFile(String localPath, String fileName) {
        Storage storage = StorageOptions.getDefaultInstance().getService();

        try {
            BlobInfo blobInfo = BlobInfo.newBuilder("cafeverse-1b93e", "images/" + fileName).build();
            storage.create(blobInfo, Files.readAllBytes(Paths.get(localPath)));
            System.out.println("File uploaded.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
