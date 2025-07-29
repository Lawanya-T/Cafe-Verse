package com.cafeverse.util;

import com.cafeverse.model.CartItem;
import com.cafeverse.model.UserSignUp;

public class SessionManager {
    private static String username;
    private static UserSignUp user;
    private static CartItem cart;
    
    public static void setUser(UserSignUp user1){
        user = user1;
    }
    public static UserSignUp getUser(){return user;}

    public static void setUsername(String user) {
        username = user;
    }

    public static String getUsername() {
        return username;
    }
    public static CartItem getCart() {
        return cart;
    }
    public static void setCart(CartItem cart) {
        SessionManager.cart = cart;
    }
}
