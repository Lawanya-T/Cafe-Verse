package com.cafeverse.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FirebaseAuthController1 {
    private static final String API_KEY = "AIzaSyBk96ACK4cEtMY-OwGMoIkA3O9vnu19kCQ";

    public boolean signUpWithEmailAndPassword(String email, String password){

        try{

            URL url = new URL("https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=" + API_KEY);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type","application/json");
            conn.setDoOutput(true);
            String payload  = String.format(
                "{\"email\":\"%s\",\"password\":\"%s\",\"returnSecureToken\":true}",
                email, password
            );

            OutputStream os = null;
            os = conn.getOutputStream();
            os.write(payload.getBytes());
            int responseCode = conn.getResponseCode();
            if(responseCode == 200){
                return true;
            }
            else{
                try(BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream()))){
                    String line;
                    while((line = br.readLine()) != null){
                        System.out.println(line);
                    }
                }
            }
            return false;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
