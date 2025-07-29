package com.cafeverse;

import com.cafeverse.controller.CartController;
import com.cafeverse.controller.FirebaseInitializer;
import com.cafeverse.util.SessionManager;
import com.cafeverse.view.AdminDashboard;
import com.cafeverse.view.loginpage;
import com.cafeverse.view.payment;
import com.cafeverse.view.AdminDashboardPage;
import com.cafeverse.view.DecorationPage;
import com.cafeverse.view.FirebaseImage;
import com.cafeverse.view.Home;
import com.cafeverse.view.cart;
import com.cafeverse.view.cartPage;
import com.cafeverse.view.loginpage;
import com.cafeverse.view.profilepage;
import com.cafeverse.view.signuppage;
import com.cafeverse.view.profilepage;

import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        FirebaseInitializer.initialize(); 
        Application.launch(Home.class,args);

    }
}